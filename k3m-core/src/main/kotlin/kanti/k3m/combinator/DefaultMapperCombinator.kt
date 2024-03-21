package kanti.k3m.combinator

import kanti.k3m.Const
import kanti.k3m.logger.K3MLogger
import kanti.k3m.serializer.SerializedMapper

class DefaultMapperCombinator(
	private val logger: K3MLogger
) : MapperCombinator {

	private val mappers = HashMap<MapperIndex, MutableList<SerializedMapper>>()

	override val combinedMappers: Sequence<CombinedMappers>
		get() {
			logger.debug(LOG_TAG, "Combine all mappers")
			return mappers.asSequence().map { entry ->
				logger.debug(LOG_TAG, "Combine ${entry.key} mappers")
				val mappersSequence = entry.value.asSequence()
				CombinedMappers(
					packageName = entry.key.packageName,
					sourceType = entry.key.sourceType,
					imports = mappersSequence.flatMap { it.imports }
						.distinct()
						.sorted().toList(),
					mappers = mappersSequence.map { it.mapper }.toList()
				)
			}
		}

	override fun add(
		packageName: String,
		sourceFullName: String,
		sourceType: String,
		serializedMapper: SerializedMapper
	) {
		logger.debug(LOG_TAG, "Add $packageName.$sourceType mapper")
		val mapperIndex = MapperIndex(
			packageName = packageName,
			sourceFullName = sourceFullName,
			sourceType = sourceType
		)
		if (!mappers.containsKey(mapperIndex)) {
			mappers[mapperIndex] = mutableListOf()
		}
		mappers[mapperIndex]?.add(serializedMapper)
	}

	companion object {

		private const val LOG_TAG = "${Const.LOG_TAG} DefaultMapperCombinator"
	}

	private data class MapperIndex(
		val packageName: String,
		val sourceFullName: String,
		val sourceType: String
	) {

		override fun toString(): String {
			return "$packageName.$sourceFullName"
		}
	}

	class DefaultMapperCombinatorProvider(
		private val logger: K3MLogger
	) : MapperCombinator.MapperCombinatorProvider {

		override fun create(): MapperCombinator {
			logger.debug(LOG_TAG, "create()")
			return DefaultMapperCombinator(logger)
		}

		companion object {

			const val LOG_TAG = "${Const.LOG_TAG} DefaultMapperCombinatorProvider"
		}
	}
}