package kanti.k3m.serializer.parser.fragments

import kanti.k3m.data.MapperInfo

class PackageParser : FragmentParser<String> {

	override fun parse(mapperInfo: MapperInfo): String {
		return mapperInfo.packageName
	}
}