package kanti.k3m.serializer.parser.fragments

import kanti.k3m.K3MConst
import kanti.k3m.K3MLogger
import kanti.k3m.data.ConverterInfo
import kanti.k3m.data.MapperInfo
import kanti.k3m.data.TypeInfo
import kanti.k3m.serializer.parser.ImportInfo

class ImportsParser(
	private val logger: K3MLogger = K3MLogger.NonLogger
) : FragmentParser<Iterable<ImportInfo>> {

	override fun parse(mapperInfo: MapperInfo): Iterable<ImportInfo> {
		logger.debug(LOG_TAG, "parse(mapperInfo = $mapperInfo)")
		val mImports = mutableListOf<ImportInfo>()
		mapperInfo.parseImports(mapperInfo.packageName, mImports)
		return mImports
	}

	private fun MapperInfo.parseImports(mainPkg: String, imports: MutableList<ImportInfo>) {
		logger.debug(LOG_TAG, "parseImports(mainPkg = $mainPkg, imports = $imports)")
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
		logger.debug(LOG_TAG, "addIfNotKotlin(mainPkg = $mainPkg, imports = $imports)")
		val importInfo = ImportInfo(packageName = packageName, type = type)
		if (packageName != "kotlin" && !imports.contains(importInfo) && packageName != mainPkg) {
			imports.add(importInfo)
		}
	}

	private fun ConverterInfo?.addIfNotKotlin(mainPkg: String, imports: MutableList<ImportInfo>) {
		logger.debug(LOG_TAG, "addIfNotKotlin(mainPkg = $mainPkg, imports = $imports)")
		when (this) {
			is ConverterInfo.ClassFunc -> {
				type.addIfNotKotlin(mainPkg, imports)
			}

			is ConverterInfo.GlobalFunc -> {
				val import = ImportInfo(packageName = packageName, type = funcName)
				if (packageName != "kotlin" && !imports.contains(import) && packageName != mainPkg) {
					imports.add(import)
				}
			}

			is ConverterInfo.ClassFuncStatic -> {
				type.addIfNotKotlin(mainPkg, imports)
			}

			is ConverterInfo.SourceFuncExtension -> {
				val import = ImportInfo(packageName = packageName, type = function)
				if (packageName != "kotlin" && !imports.contains(import) && packageName != mainPkg) {
					imports.add(import)
				}
			}

			else -> {}
		}
	}

	companion object {

		private const val LOG_TAG = "${K3MConst.LOG_TAG} ImportsParser"
	}
}