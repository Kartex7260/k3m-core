package kanti.k3m.serializer.parser

data class ParsedMapper(
	val packageName: String,
	val imports: Iterable<ImportInfo>,
	val sourceType: String,
	val destinationType: String,
	val dependencies: Iterable<DependencyInfo>,
	val parameters: Iterable<ParameterInfo>
)