package kanti.k3m.serializer.parser.fragments

import kanti.k3m.data.ConverterInfo
import kanti.k3m.data.MappingInfo
import kanti.k3m.data.ParameterLinkInfo
import kanti.k3m.data.fullName
import kanti.k3m.serializer.parser.ParameterInfo

class ParametersParser : FragmentParser<Iterable<ParameterInfo>> {

	override fun parse(mappingInfo: MappingInfo): Iterable<ParameterInfo> {
		val parameters = mutableListOf<ParameterInfo>()
		mappingInfo.parseParameters(parameters)
		return parameters
	}

	private fun MappingInfo.parseParameters(parameters: MutableList<ParameterInfo>) {
		for (parameter in this.parameters) {
			if (parameter.converter != null) {
				parameter.addWithConverter(parameters)
				continue
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