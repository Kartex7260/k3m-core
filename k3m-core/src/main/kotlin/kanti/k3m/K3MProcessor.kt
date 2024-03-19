package kanti.k3m

import kanti.k3m.combinator.DefaultMapperCombinator
import kanti.k3m.combinator.MapperCombinator
import kanti.k3m.data.MapperInfo
import kanti.k3m.serializer.DefaultK3MSerializer
import kanti.k3m.serializer.K3MSerializer

abstract class K3MProcessor(
	private val logger: K3MLogger,
	private val mapperGenerator: MapperGenerator,
	private val serializer: K3MSerializer = DefaultK3MSerializer(),
	private val mapperCombinatorProvider: MapperCombinator.MapperCombinatorProvider =
		DefaultMapperCombinator.DefaultMapperCombinatorProvider()
) {

	fun process(mappers: List<MapperInfo>) {
		logger.debug(LOG_TAG, "Create MapperCombinator")
		val mapperCombinator = mapperCombinatorProvider.create()
		for (mapper in mappers) {
			try {
				logger.debug(LOG_TAG, "serialize mapper: $mapper")
				val serializedMapper = serializer.serialize(mapper)
				mapperCombinator.add(
					packageName = mapper.packageName,
					sourceFullName = mapper.source.fullName,
					sourceType = mapper.source.type,
					serializedMapper = serializedMapper
				)
			} catch (ex: Exception) {
				logger.exception(ex)
			}
		}
		for (combinedMappers in mapperCombinator.combinedMappers) {
			logger.debug(LOG_TAG, "generate $combinedMappers")
			mapperGenerator.generate(combinedMappers)
		}
	}

	companion object {

		private const val LOG_TAG = "[K3M-CORE] K3MProcessor"
	}
}