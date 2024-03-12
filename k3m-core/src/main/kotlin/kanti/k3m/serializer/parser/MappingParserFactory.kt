package kanti.k3m.serializer.parser

import kanti.k3m.data.MappingInfo

interface MappingParserFactory {

	fun create(mappingInfo: MappingInfo): MappingParser
}

class DefaultMappingParserFactory : MappingParserFactory {

	override fun create(mappingInfo: MappingInfo): MappingParser {
		return DefaultMappingParser(mappingInfo)
	}
}