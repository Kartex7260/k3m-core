package kanti.k3m.parser.fragments

import kanti.k3m.Const
import kanti.k3m.logger.K3MLogger
import kanti.k3m.symbol.ConverterInfo
import kanti.k3m.symbol.MapperInfo
import kanti.k3m.symbol.TypeInfo
import kanti.k3m.parser.ParsedImport

class ImportsParser(
	private val logger: K3MLogger = K3MLogger.NonLogger
) : FragmentParser<Sequence<ParsedImport>> {

	override fun parse(mapperInfo: MapperInfo): Sequence<ParsedImport> {
		logger.debug(LOG_TAG, "Parsing imports from \"$mapperInfo\" mapper")
		val mImports = mutableListOf<ParsedImport>()
		mapperInfo.parseImports(mapperInfo.packageName, mImports)
		return mImports.asSequence()
	}

	private fun MapperInfo.parseImports(mainPkg: String, imports: MutableList<ParsedImport>) {
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

	private fun TypeInfo.addIfNotKotlin(mainPkg: String, imports: MutableList<ParsedImport>) {
		val parsedImport = ParsedImport(packageName = packageName, type = type)
		if (packageName != "kotlin" && !imports.contains(parsedImport) && packageName != mainPkg) {
			imports.add(parsedImport)
		}
	}

	private fun ConverterInfo?.addIfNotKotlin(mainPkg: String, imports: MutableList<ParsedImport>) {
		when (this) {
			is ConverterInfo.ClassFunc -> {
				type.addIfNotKotlin(mainPkg, imports)
			}

			is ConverterInfo.GlobalFunc -> {
				val import = ParsedImport(packageName = packageName, type = funcName)
				if (packageName != "kotlin" && !imports.contains(import) && packageName != mainPkg) {
					imports.add(import)
				}
			}

			is ConverterInfo.ClassFuncStatic -> {
				type.addIfNotKotlin(mainPkg, imports)
			}

			is ConverterInfo.SourceFuncExtension -> {
				val import = ParsedImport(packageName = packageName, type = function)
				if (packageName != "kotlin" && !imports.contains(import) && packageName != mainPkg) {
					imports.add(import)
				}
			}

			else -> {}
		}
	}

	companion object {

		private const val LOG_TAG = "${Const.LOG_TAG} ImportsParser"
	}
}