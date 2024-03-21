package kanti.k3m.parser

data class ParsedMapper(
	val packageName: String,
	val imports: Sequence<ParsedImport>,
	val sourceType: String,
	val destinationType: String,
	val dependencies: Sequence<ParsedDependency>,
	val parameters: Sequence<ParsedParameter>
) {

	override fun toString(): String {
		return "ParsedMapper(packageName=$packageName, sourceType = $sourceType)"
	}
}