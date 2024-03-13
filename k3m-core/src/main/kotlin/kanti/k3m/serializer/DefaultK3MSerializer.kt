package kanti.k3m.serializer

import kanti.k3m.data.MapperInfo
import kanti.k3m.serializer.fragments.FragmentSerializer
import kanti.k3m.serializer.fragments.ImportsSerializer
import kanti.k3m.serializer.fragments.MapperSerializer
import kanti.k3m.serializer.fragments.PackageSerializer
import kanti.k3m.serializer.parser.DefaultMappingParser
import kanti.k3m.serializer.parser.MappingParser

class DefaultK3MSerializer(
	mappingParserFactory: MappingParser.MappingParserFactory = DefaultMappingParser
		.DefaultMappingParserFactory(),
	private val packageSerializer: FragmentSerializer<String> = PackageSerializer(),
	private val importsSerializer: FragmentSerializer<Iterable<String>> = ImportsSerializer(),
	private val mapperSerializer: FragmentSerializer<String> = MapperSerializer()
) : K3MSerializer {

	private val parser = mappingParserFactory.create()

	override fun serialize(mapperInfo: MapperInfo): SerializedMapper {
		val parsedMapper = parser.parse(mapperInfo)

		return SerializedMapper(
			packageName = packageSerializer.serialize(parsedMapper),
			imports = importsSerializer.serialize(parsedMapper),
			mapper = mapperSerializer.serialize(parsedMapper)
		)
	}
}