package kanti.k3m.data

data class MappingInfo(
	val packageName: String,
	val source: TypeInfo,
	val destination: TypeInfo,
	val parameters: Iterable<ParameterLinkInfo>
)