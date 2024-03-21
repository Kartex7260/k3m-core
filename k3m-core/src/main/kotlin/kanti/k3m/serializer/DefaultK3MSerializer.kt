package kanti.k3m.serializer

import kanti.k3m.K3MConst
import kanti.k3m.logger.K3MLogger
import kanti.k3m.symbol.MapperInfo
import kanti.k3m.serializer.fragments.FragmentSerializer
import kanti.k3m.serializer.fragments.ImportsSerializer
import kanti.k3m.serializer.fragments.MapperSerializer
import kanti.k3m.parser.DefaultMappingParser
import kanti.k3m.parser.MappingParser

class DefaultK3MSerializer(
	private val logger: K3MLogger = K3MLogger.NonLogger,
	mappingParserFactory: MappingParser.MappingParserFactory = DefaultMappingParser
		.DefaultMappingParserFactory(logger),
	private val importsSerializer: FragmentSerializer<Iterable<String>> = ImportsSerializer(logger),
	private val mapperSerializer: FragmentSerializer<String> = MapperSerializer(logger)
) : K3MSerializer {

	init {
		logger.debug(LOG_TAG, "create MappingParser")
	}

	private val parser = mappingParserFactory.create()

	override fun serialize(mapperInfo: MapperInfo): SerializedMapper {
		logger.debug(LOG_TAG, "serialize(mapperInfo = $mapperInfo)")
		val parsedMapper = parser.parse(mapperInfo)

		return SerializedMapper(
			imports = importsSerializer.serialize(parsedMapper),
			mapper = mapperSerializer.serialize(parsedMapper)
		)
	}

	companion object {

		private const val LOG_TAG = "${K3MConst.LOG_TAG} DefaultK3MSerializer"
	}
}