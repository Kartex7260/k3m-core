package kanti.k3m.serializer

import kanti.k3m.data.MappingInfo

interface K3MSerializer {

	fun serialize(mappingInfo: MappingInfo): String
}

private class K3MSerializerImpl : K3MSerializer {

	override fun serialize(mappingInfo: MappingInfo): String {
		TODO("Not yet implemented")
	}
}