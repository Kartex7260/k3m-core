package kanti.k3m.serializer.parser.fragments

import kanti.k3m.data.MappingInfo

class SourceTypeParser : FragmentParser<String> {

	override fun parse(mappingInfo: MappingInfo): String {
		return mappingInfo.source.type
	}
}