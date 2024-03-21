package kanti.k3m

import kanti.k3m.combinator.DefaultMapperCombinator
import kanti.k3m.combinator.MapperCombinator
import kanti.k3m.logger.K3MLogger
import kanti.k3m.symbol.MapperInfo
import kanti.k3m.serializer.DefaultK3MSerializer
import kanti.k3m.serializer.K3MSerializer

class K3MProcessor(
	private val logger: K3MLogger = K3MLogger.NonLogger,
	private val mapperGenerator: MapperGenerator,
	private val serializer: K3MSerializer = DefaultK3MSerializer(logger),
	private val mapperCombinatorProvider: MapperCombinator.MapperCombinatorProvider =
		DefaultMapperCombinator.DefaultMapperCombinatorProvider(logger)
) {

	fun process(mappers: List<MapperInfo>) {
		logger.info(LOG_TAG, "Processing the mapper list (count = ${mappers.size})")
		val mapperCombinator = mapperCombinatorProvider.create()
		for (mapper in mappers) {
			logger.debug(LOG_TAG, "Serialization the \"$mapper\" mapper")
			try {
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
			logger.debug(LOG_TAG, "Generating the \"$combinedMappers\" combined mapper`s")
			mapperGenerator.generate(combinedMappers)
		}
	}

	companion object {

		private const val LOG_TAG = "${Const.LOG_TAG} K3MProcessor"
	}
}