package kanti.k3m.serializer.parser.fragments

import kanti.k3m.K3MConst
import kanti.k3m.K3MLogger
import kanti.k3m.symbol.ConverterInfo
import kanti.k3m.symbol.MapperInfo
import kanti.k3m.serializer.parser.DependencyInfo

class DependenciesParser(
	private val logger: K3MLogger = K3MLogger.NonLogger
) : FragmentParser<Iterable<DependencyInfo>> {

	override fun parse(mapperInfo: MapperInfo): Iterable<DependencyInfo> {
		logger.debug(LOG_TAG, "parse(mapperInfo = $mapperInfo)")
		val dependencies = mutableListOf<DependencyInfo>()
		mapperInfo.parseDependencies(dependencies)
		return dependencies
	}

	private fun MapperInfo.parseDependencies(dependencies: MutableList<DependencyInfo>) {
		logger.debug(LOG_TAG, "parseDependencies(dependencies = $dependencies)")
		for (parameter in parameters) {
			parameter.converter?.addIfClassFunc(dependencies)
		}
	}

	private fun ConverterInfo.addIfClassFunc(dependencies: MutableList<DependencyInfo>) {
		logger.debug(LOG_TAG, "addIfClassFunc(dependencies = $dependencies)")
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

	companion object {

		private const val LOG_TAG = "${K3MConst.LOG_TAG} DependenciesParser"
	}
}