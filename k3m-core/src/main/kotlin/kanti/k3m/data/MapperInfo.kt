package kanti.k3m.data

data class MapperInfo(
	val packageName: String,
	val source: TypeInfo,
	val destination: TypeInfo,
	val parameters: Iterable<ParameterLinkInfo>
) {

	override fun toString(): String {
		return "MapperInfo(source=$source, destination=$destination)"
	}
}