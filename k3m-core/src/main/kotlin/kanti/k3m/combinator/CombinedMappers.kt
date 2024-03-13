package kanti.k3m.combinator

data class CombinedMappers(
	val packageName: String,
	val sourceType: String,
	val imports: Iterable<String>,
	val mappers: Iterable<String>
) {

	fun combine(): String {
		val stringBuilder = StringBuilder()

		stringBuilder.appendLine(packageName).appendLine()

		for (import in imports) {
			stringBuilder.append(import)
		}
		stringBuilder.appendLine()

		for (mapper in mappers) {
			stringBuilder.appendLine(mapper).appendLine()
		}

		return stringBuilder.toString()
	}
}
