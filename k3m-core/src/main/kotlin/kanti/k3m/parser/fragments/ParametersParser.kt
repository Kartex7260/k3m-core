package kanti.k3m.parser.fragments

import kanti.k3m.Const
import kanti.k3m.logger.K3MLogger
import kanti.k3m.symbol.ConverterInfo
import kanti.k3m.symbol.MapperInfo
import kanti.k3m.symbol.ParameterLinkInfo
import kanti.k3m.parser.ConverterType
import kanti.k3m.parser.ParsedParameter

class ParametersParser(
	private val logger: K3MLogger = K3MLogger.NonLogger
) : FragmentParser<Sequence<ParsedParameter>> {

	override fun parse(mapperInfo: MapperInfo): Sequence<ParsedParameter> {
		logger.debug(LOG_TAG, "Parsing parameters from the \"$mapperInfo\" mapper")
		return mapperInfo.parameters.parseParameters(mapperInfo.isSourceCastToDestination)
	}

	private fun Sequence<ParameterLinkInfo>.parseParameters(
		isSourceCastToDestination: Boolean
	): Sequence<ParsedParameter> {
		return map { parameter ->
			if (parameter.converter != null) {
				return@map parameter.toParsedParameter()
			}

			if (parameter.sourceType == parameter.destinationType || isSourceCastToDestination) {
				return@map ParsedParameter(
					destination = parameter.destinationName,
					source = parameter.sourceName,
					converter = null,
					converterType = ConverterType.ForSource
				)
			}

			throw IllegalStateException(
				"Parsing: Parameters link(" +
						"${parameter.sourceName}: ${parameter.sourceType.fullName}, " +
						"${parameter.destinationName}: ${parameter.destinationType.fullName}" +
						"): does not have a converter"
			)
		}
	}

	private fun ParameterLinkInfo.toParsedParameter(): ParsedParameter {
		return when (converter) {
			is ConverterInfo.GlobalFunc -> {
				ParsedParameter(
					destination = destinationName,
					source = sourceName,
					converter = converter.funcName,
					converterType = ConverterType.ForSource
				)
			}
			is ConverterInfo.ClassFunc -> {
				ParsedParameter(
					destination = destinationName,
					source = sourceName,
					converter = "${converter.paramName}.${converter.function}",
					converterType = ConverterType.ForSource
				)
			}
			is ConverterInfo.ClassFuncStatic -> {
				ParsedParameter(
					destination = destinationName,
					source = sourceName,
					converter = "${converter.type.type}.${converter.function}",
					converterType = ConverterType.ForSource
				)
			}
			is ConverterInfo.SourceFunc -> {
				ParsedParameter(
					destination = destinationName,
					source = sourceName,
					converter = converter.function,
					converterType = ConverterType.FromSource
				)
			}

			is ConverterInfo.SourceFuncExtension -> {
				ParsedParameter(
					destination = destinationName,
					source = sourceName,
					converter = converter.function,
					converterType = ConverterType.FromSource
				)
			}

			else -> throw notHaveConverter()
		}
	}

	private fun ParameterLinkInfo.notHaveConverter(): Exception {
		return IllegalStateException(
			"Parsing: Parameters link(" +
					"${sourceName}: ${sourceType.fullName}, " +
					"${destinationName}: ${destinationType.fullName}" +
					"): does not have a converter"
		)
	}

	companion object {

		private const val LOG_TAG = "${Const.LOG_TAG} ParametersParser"
	}
}