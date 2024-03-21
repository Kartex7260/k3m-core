package kanti.k3m.serializer.fragments

import kanti.k3m.K3MConst
import kanti.k3m.K3MLogger
import kanti.k3m.parser.ConverterType
import kanti.k3m.parser.ParsedMapper

class MapperSerializer(
	private val logger: K3MLogger = K3MLogger.NonLogger
) : FragmentSerializer<String> {

	override fun serialize(parsedMapper: ParsedMapper): String {
		logger.debug(LOG_TAG, "serialize(parsedMapper = $parsedMapper)")
		val stringBuilder = StringBuilder()
		parsedMapper.defineFunction(stringBuilder)
		parsedMapper.defineBlock(stringBuilder)
		return stringBuilder.toString()
	}

	private fun ParsedMapper.defineFunction(stringBuilder: StringBuilder) {
		logger.debug(LOG_TAG, "defineFunction(stringBuilder = $stringBuilder)")
		stringBuilder.append("fun ").append(sourceType).append('.')
			.append("to").append(destinationType).append('(')

		val dependencies = this.dependencies.toList()
		for (index in dependencies.indices) {
			val dependency = dependencies[index]
			stringBuilder.append(dependency.parameter).append(": ")
				.append(dependency.type)
			if (index < dependencies.size - 1)
				stringBuilder.append(", ")
		}

		stringBuilder.append("): ").append(destinationType)
	}

	private fun ParsedMapper.defineBlock(stringBuilder: StringBuilder) {
		logger.debug(LOG_TAG, "defineBlock(stringBuilder = $stringBuilder)")
		stringBuilder.appendLine(" {")
			.appendLine("\treturn $destinationType(")

		val parameters = this.parameters.toList()
		for (index in parameters.indices) {
			val parameter = parameters[index]
			stringBuilder.append("\t\t").append(parameter.destination)
				.append(" = ")

			if (parameter.converter == null) {
				stringBuilder.append(parameter.source)
			} else {
				when (parameter.converterType) {
					ConverterType.ForSource -> {
						stringBuilder.append(parameter.converter)
							.append('(').append(parameter.source).append(')')
					}
					ConverterType.FromSource -> {
						stringBuilder.append(parameter.source)
							.append('.').append(parameter.converter).append("()")
					}
				}
			}

			if (index < parameters.size - 1)
				stringBuilder.appendLine(',')
			else
				stringBuilder.appendLine()
		}

		stringBuilder.appendLine("\t)")
			.append("}")
	}

	companion object {

		private const val LOG_TAG = "${K3MConst.LOG_TAG} MapperSerializer"
	}
}