package kanti.k3m.serializer.parser.fragments

import kanti.k3m.data.ConverterInfo
import kanti.k3m.data.MappingInfo
import kanti.k3m.data.TypeInfo
import kanti.k3m.serializer.parser.ImportInfo

class ImportsParser : FragmentParser<Iterable<ImportInfo>> {

	override fun parse(mappingInfo: MappingInfo): Iterable<ImportInfo> {
		val mImports = mutableListOf<ImportInfo>()
		mappingInfo.parseImports(mappingInfo.packageName, mImports)
		return mImports
	}

	private fun MappingInfo.parseImports(mainPkg: String, imports: MutableList<ImportInfo>) {
		source.addIfNotKotlin(mainPkg, imports)
		destination.addIfNotKotlin(mainPkg, imports)

		for (parameter in parameters) {
//			parameter.apply {
//				sourceType.addIfNotKotlin(mainPkg, imports)
//				destinationType.addIfNotKotlin(mainPkg, imports)
//			}
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
}