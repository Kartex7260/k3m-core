package kanti.k3m.serializer.fragments

import kanti.k3m.serializer.parser.ParsedMapper

class PackageSerializer : FragmentSerializer<String> {

	override fun serialize(parsedMapper: ParsedMapper): String {
		return "package ${parsedMapper.packageName}"
	}
}