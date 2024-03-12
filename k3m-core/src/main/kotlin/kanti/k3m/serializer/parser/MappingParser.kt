package kanti.k3m.serializer.parser

import kanti.k3m.data.*

interface MappingParser {

	val packageName: String

	val imports: Iterable<ImportInfo>

	val sourceType: String
	val destinationType: String

	val dependencies: Iterable<DependencyInfo>

	val parameters: Iterable<ParameterInfo>
}

class DefaultMappingParser(
	mappingInfo: MappingInfo
) : MappingParser {

	override val packageName: String = mappingInfo.packageName

	override val imports: Iterable<ImportInfo>

	override val sourceType: String = mappingInfo.source.type
	override val destinationType: String = mappingInfo.destination.type

	override val dependencies: Iterable<DependencyInfo>

	override val parameters: Iterable<ParameterInfo>

	init {
		val mImports = mutableListOf<ImportInfo>()
		mappingInfo.parseImports(mappingInfo.packageName, mImports)
		mImports.sortBy { it.fullName }
		imports = mImports

		val mDependencies = mutableListOf<DependencyInfo>()
		mappingInfo.parseDependencies(mDependencies)
		dependencies = mDependencies

		val mParameters = mutableListOf<ParameterInfo>()
		mappingInfo.parseParameters(mParameters)
		parameters = mParameters
	}

	private fun MappingInfo.parseImports(mainPkg: String, imports: MutableList<ImportInfo>) {
		source.addIfNotKotlin(mainPkg, imports)
		destination.addIfNotKotlin(mainPkg, imports)

		for (parameter in parameters) {
			parameter.apply {
				sourceType.addIfNotKotlin(mainPkg, imports)
				destinationType.addIfNotKotlin(mainPkg, imports)
			}
			parameter.converter.addIfNotKotlin(mainPkg, imports)
		}
	}

	private fun TypeInfo.addIfNotKotlin(mainPkg: String, imports: MutableList<ImportInfo>) {
		val importInfo = ImportInfo(packageName = packageName, type = type)
		if (packageName != "kotlin" && !imports.contains(importInfo) && packageName != mainPkg) {
			imports.add(importInfo)
		}
	}

	private fun ConverterInfo?.addIfNotKotlin(mainPkg: String, imports: MutableList<ImportInfo>) {
		when (this) {
			is ConverterInfo.ClassFunc -> {
				type.addIfNotKotlin(mainPkg, imports)
			}

			is ConverterInfo.GlobalFunc -> {
				val import = ImportInfo(packageName = packageName, type = funcName)
				if (packageName != "kotlin" && !imports.contains(import)) {
					imports.add(import)
				}
			}

			else -> {}
		}
	}

	private fun MappingInfo.parseDependencies(dependencies: MutableList<DependencyInfo>) {
		for (parameter in parameters) {
			parameter.converter?.addIfClassFunc(dependencies)
		}
	}

	private fun ConverterInfo.addIfClassFunc(dependencies: MutableList<DependencyInfo>) {
		when (this) {
			is ConverterInfo.ClassFunc -> {
				val paramName = paramName
				dependencies.add(
					DependencyInfo(
						parameter = paramName,
						type = type.type
					)
				)
			}

			else -> {}
		}
	}

	private fun MappingInfo.parseParameters(parameters: MutableList<ParameterInfo>) {
		for (parameter in this.parameters) {
			if (parameter.converter != null) {
				parameter.addWithConverter(parameters)
			}

			if (parameter.sourceType == parameter.destinationType) {
				parameters.add(
					ParameterInfo(
						destination = parameter.destinationName,
						source = parameter.sourceName,
						converter = null
					)
				)
				continue
			}

			throw IllegalStateException(
				"Parsing: Parameters link(" +
						"${parameter.sourceName}: ${parameter.sourceType.fullName}, " +
						"${parameter.destinationName}: ${parameter.destinationType.fullName}" +
						"): does not have a converter"
			)
		}
	}

	private fun ParameterLinkInfo.addWithConverter(parameters: MutableList<ParameterInfo>) {
		when (converter) {
			is ConverterInfo.GlobalFunc -> {
				parameters.add(
					ParameterInfo(
						destination = destinationName,
						source = sourceName,
						converter = converter.funcName
					)
				)
			}
			is ConverterInfo.ClassFunc -> {
				parameters.add(
					ParameterInfo(
						destination = destinationName,
						source = sourceName,
						converter = "${converter.paramName}.${converter.function}"
					)
				)
			}

			else -> {}
		}
	}
}