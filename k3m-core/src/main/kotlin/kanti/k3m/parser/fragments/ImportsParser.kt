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
		return sequence {
			addIfNotKotlin(
				type = mapperInfo.source,
				mainPkg = mapperInfo.packageName
			)
			addIfNotKotlin(
				type = mapperInfo.destination,
				mainPkg = mapperInfo.packageName
			)

			for (parameter in mapperInfo.parameters) {
				addIfNotKotlin(parameter.converter, mapperInfo.packageName)
			}
		}.distinct()
	}

	private suspend fun SequenceScope<ParsedImport>.addIfNotKotlin(
		type: TypeInfo,
		mainPkg: String
	) {
		val parsedImport = ParsedImport(packageName = type.packageName, type = type.type)
		if (type.packageName != "kotlin" && type.packageName != mainPkg) {
			yield(parsedImport)
		}
	}

	private suspend fun SequenceScope<ParsedImport>.addIfNotKotlin(
		converter: ConverterInfo?,
		mainPkg: String
	) {
		when (converter) {
			is ConverterInfo.ClassFunc -> {
				addIfNotKotlin(converter.type, mainPkg)
			}

			is ConverterInfo.GlobalFunc -> {
				val import = ParsedImport(packageName = converter.packageName, type = converter.funcName)
				if (converter.packageName != "kotlin" && converter.packageName != mainPkg) {
					yield(import)
				}
			}

			is ConverterInfo.ClassFuncStatic -> {
				addIfNotKotlin(converter.type, mainPkg)
			}

			is ConverterInfo.SourceFuncExtension -> {
				val import = ParsedImport(packageName = converter.packageName, type = converter.function)
				if (converter.packageName != "kotlin" && converter.packageName != mainPkg) {
					yield(import)
				}
			}

			else -> {}
		}
	}

	companion object {

		private const val LOG_TAG = "${Const.LOG_TAG} ImportsParser"
	}
}