package kanti.k3m.parser

data class ParsedParameter(
	val destination: String,
	val source: String,
	val converter: String?,
	val converterType: ConverterType
)

enum class ConverterType {
	ForSource, FromSource
}
