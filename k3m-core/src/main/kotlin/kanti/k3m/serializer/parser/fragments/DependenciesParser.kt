package kanti.k3m.serializer.parser.fragments

import kanti.k3m.data.ConverterInfo
import kanti.k3m.data.MapperInfo
import kanti.k3m.serializer.parser.DependencyInfo

class DependenciesParser : FragmentParser<Iterable<DependencyInfo>> {

	override fun parse(mapperInfo: MapperInfo): Iterable<DependencyInfo> {
		val dependencies = mutableListOf<DependencyInfo>()
		mapperInfo.parseDependencies(dependencies)
		return dependencies
	}

	private fun MapperInfo.parseDependencies(dependencies: MutableList<DependencyInfo>) {
		for (parameter in parameters) {
			parameter.converter?.addIfClassFunc(dependencies)
		}
	}

	private fun ConverterInfo.addIfClassFunc(dependencies: MutableList<DependencyInfo>) {
		when (this) {
			is ConverterInfo.ClassFunc -> {
				val paramName = paramName
				dependencies.add(
					DependencyInfo(
						parameter = paramName,
						type = type.type
					)
				)
			}

			else -> {}
		}
	}
}