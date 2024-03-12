package kanti.k3m.serializer.parser

data class ImportInfo(
	val packageName: String,
	val type: String
) {

	val fullName: String get() = "$packageName.$type"
}
