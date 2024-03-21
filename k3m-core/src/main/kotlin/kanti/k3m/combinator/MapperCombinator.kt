package kanti.k3m.combinator

import kanti.k3m.serializer.SerializedMapper

interface MapperCombinator {

	val combinedMappers: Sequence<CombinedMappers>

	fun add(
		packageName: String,
		sourceFullName: String,
		sourceType: String,
		serializedMapper: SerializedMapper
	)

	interface MapperCombinatorProvider {

		fun create(): MapperCombinator
	}
}