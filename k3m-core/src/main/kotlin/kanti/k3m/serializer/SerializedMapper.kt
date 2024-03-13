package kanti.k3m.serializer

data class SerializedMapper(
	val packageName: String,
	val imports: Iterable<String>,
	val mapper: String
)
