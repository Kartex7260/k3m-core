package kanti.k3m

import kanti.k3m.combinator.CombinedMappers

interface MapperGenerator {

	fun generate(combinedMappers: CombinedMappers)
}