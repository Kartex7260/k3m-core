package kanti.k3m.parser

data class ParsedImport(
	val packageName: String,
	val type: String
) {

	val fullName: String get() = "$packageName.$type"
}
