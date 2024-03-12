package kanti.k3m.serializer

import kanti.k3m.serializer.parser.MappingParser

interface K3MSerializer {

	fun serialize(parser: MappingParser): String
}

class DefaultK3MSerializer : K3MSerializer {

	override fun serialize(parser: MappingParser): String {
		val stringBuilder = StringBuilder()
		parser.addPackageWithImports(stringBuilder)
		parser.defineFunction(stringBuilder)
		parser.defineBlock(stringBuilder)
		return stringBuilder.toString()
	}

	private fun MappingParser.addPackageWithImports(stringBuilder: StringBuilder) {
		stringBuilder.append("package ")
			.appendLine(packageName).append("\n")

		for (import in imports) {
			stringBuilder.append("import ")
				.appendLine(import.fullName)
		}
		stringBuilder.appendLine()
	}

	private fun MappingParser.defineFunction(stringBuilder: StringBuilder) {
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

	private fun MappingParser.defineBlock(stringBuilder: StringBuilder) {
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
				stringBuilder.append(parameter.converter)
					.append('(').append(parameter.source).append(')')
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