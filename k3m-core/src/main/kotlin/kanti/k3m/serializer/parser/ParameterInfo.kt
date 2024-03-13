package kanti.k3m.serializer.parser

data class ParameterInfo(
	val destination: String,
	val source: String,
	val converter: String?,
	val converterType: ConverterType
)

enum class ConverterType {
	ForSource, FromSource
}
