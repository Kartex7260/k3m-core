package kanti.k3m.data

data class ConverterInfo(
	val function: TypeInfo,
	val dependency: TypeInfo?,
	val dependencyParameterName: String?
)