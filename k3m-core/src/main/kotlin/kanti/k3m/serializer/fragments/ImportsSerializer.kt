package kanti.k3m.serializer.fragments

import kanti.k3m.serializer.parser.ParsedMapper

class ImportsSerializer : FragmentSerializer<Iterable<String>> {

	override fun serialize(parsedMapper: ParsedMapper): Iterable<String> {
		val imports = mutableListOf<String>()
		for (import in parsedMapper.imports) {
			imports.add("import ${import.fullName}")
		}
		return imports
	}
}