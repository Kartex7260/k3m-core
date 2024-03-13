package kanti.k3m.serializer.fragments

import kanti.k3m.serializer.parser.ConverterType
import kanti.k3m.serializer.parser.ParsedMapper

class MapperSerializer : FragmentSerializer<String> {

	override fun serialize(parsedMapper: ParsedMapper): String {
		val stringBuilder = StringBuilder()
		parsedMapper.defineFunction(stringBuilder)
		parsedMapper.defineBlock(stringBuilder)
		return stringBuilder.toString()
	}

	private fun ParsedMapper.defineFunction(stringBuilder: StringBuilder) {
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
}