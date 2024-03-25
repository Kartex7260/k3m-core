package kanti.k3m.symbol

data class ParameterLinkInfo(
	val sourceName: String,
	val destinationName: String,
	val sourceType: TypeInfo,
	val destinationType: TypeInfo,
	val isSourceCastToDestination: Boolean,
	val converter: ConverterInfo?
)