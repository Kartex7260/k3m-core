package kanti.k3m.data

data class ParameterLinkInfo(
	val sourceName: String,
	val destinationName: String,
	val sourceType: TypeInfo,
	val destinationType: TypeInfo,
	val converter: ConverterInfo?
)