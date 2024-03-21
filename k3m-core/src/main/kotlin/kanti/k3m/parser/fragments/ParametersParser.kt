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
		logger.debug(LOG_TAG, "parse(mapperInfo = $mapperInfo)")
		val parameters = mutableListOf<ParsedParameter>()
		mapperInfo.parseParameters(parameters)
		return parameters.asSequence()
	}

	private fun MapperInfo.parseParameters(parameters: MutableList<ParsedParameter>) {
		logger.debug(LOG_TAG, "parseParameters(parameters = $parameters)")
		for (parameter in this.parameters) {
			if (parameter.converter != null) {
				parameter.addWithConverter(parameters)
				continue
			}

			if (parameter.sourceType == parameter.destinationType) {
				parameters.add(
					ParsedParameter(
						destination = parameter.destinationName,
						source = parameter.sourceName,
						converter = null,
						converterType = ConverterType.ForSource
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

	private fun ParameterLinkInfo.addWithConverter(parameters: MutableList<ParsedParameter>) {
		logger.debug(LOG_TAG, "addWithConverter(parameters = $parameters)")
		when (converter) {
			is ConverterInfo.GlobalFunc -> {
				parameters.add(
					ParsedParameter(
						destination = destinationName,
						source = sourceName,
						converter = converter.funcName,
						converterType = ConverterType.ForSource
					)
				)
			}
			is ConverterInfo.ClassFunc -> {
				parameters.add(
					ParsedParameter(
						destination = destinationName,
						source = sourceName,
						converter = "${converter.paramName}.${converter.function}",
						converterType = ConverterType.ForSource
					)
				)
			}
			is ConverterInfo.ClassFuncStatic -> {
				parameters.add(
					ParsedParameter(
						destination = destinationName,
						source = sourceName,
						converter = "${converter.type.type}.${converter.function}",
						converterType = ConverterType.ForSource
					)
				)
			}
			is ConverterInfo.SourceFunc -> {
				parameters.add(
					ParsedParameter(
						destination = destinationName,
						source = sourceName,
						converter = converter.function,
						converterType = ConverterType.FromSource
					)
				)
			}

			is ConverterInfo.SourceFuncExtension -> {
				parameters.add(
					ParsedParameter(
						destination = destinationName,
						source = sourceName,
						converter = converter.function,
						converterType = ConverterType.FromSource
					)
				)
			}

			else -> {}
		}
	}

	companion object {

		private const val LOG_TAG = "${Const.LOG_TAG} ParametersParser"
	}
}