package kanti.k3m.serializer

import kanti.k3m.symbol.MapperInfo

interface K3MSerializer {

	fun serialize(mapperInfo: MapperInfo): SerializedMapper
}