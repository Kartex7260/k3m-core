package kanti.k3m.combinator

import kanti.k3m.serializer.SerializedMapper

class DefaultMapperCombinator : MapperCombinator {

	private val mappers = HashMap<MapperIndex, MutableList<SerializedMapper>>()

	override val combinedMappers: Iterable<CombinedMappers>
		get() = mappers.map { entry ->
			val mappersSequence = entry.value.asSequence()
			CombinedMappers(
				packageName = entry.key.packageName,
				sourceType = entry.key.sourceType,
				imports = mappersSequence.flatMap { it.imports }.sorted().toList(),
				mappers = mappersSequence.map { it.mapper }.toList()
			)
		}

	override fun add(
		packageName: String,
		sourceFullName: String,
		sourceType: String,
		serializedMapper: SerializedMapper
	) {
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

	private data class MapperIndex(
		val packageName: String,
		val sourceFullName: String,
		val sourceType: String
	)

	class DefaultMapperCombinatorProvider : MapperCombinator.MapperCombinatorProvider {

		override fun create(): MapperCombinator {
			return DefaultMapperCombinator()
		}
	}
}