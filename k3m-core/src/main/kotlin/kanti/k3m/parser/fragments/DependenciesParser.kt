package kanti.k3m.parser.fragments

import kanti.k3m.Const
import kanti.k3m.logger.K3MLogger
import kanti.k3m.symbol.ConverterInfo
import kanti.k3m.symbol.MapperInfo
import kanti.k3m.parser.ParsedDependency

class DependenciesParser(
	private val logger: K3MLogger = K3MLogger.NonLogger
) : FragmentParser<Sequence<ParsedDependency>> {

	override fun parse(mapperInfo: MapperInfo): Sequence<ParsedDependency> {
		logger.debug(LOG_TAG, "Parsing dependencies from the \"$mapperInfo\" mapper")
		return sequence {
			for (parameter in mapperInfo.parameters) {
				addIfClassFunc(parameter.converter)
			}
		}
	}

	private suspend fun SequenceScope<ParsedDependency>.addIfClassFunc(converter: ConverterInfo?) {
		when (converter) {
			is ConverterInfo.ClassFunc -> {
				val paramName = converter.paramName
				yield(
					ParsedDependency(
						parameter = paramName,
						type = converter.type.type
					)
				)
			}

			else -> {}
		}
	}

	companion object {

		private const val LOG_TAG = "${Const.LOG_TAG} DependenciesParser"
	}
}