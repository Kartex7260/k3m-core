package kanti.k3m.parser.fragments

import kanti.k3m.Const
import kanti.k3m.logger.K3MLogger
import kanti.k3m.symbol.ConverterInfo
import kanti.k3m.symbol.MapperInfo
import kanti.k3m.parser.ParsedDependency

class DependenciesParser(
	private val logger: K3MLogger = K3MLogger.NonLogger
) : FragmentParser<Sequence<ParsedDependency>> {

	override fun parse(mapperInfo: MapperInfo): Sequence<ParsedDependency> {
		logger.debug(LOG_TAG, "Parsing dependencies from the \"$mapperInfo\" mapper")
		val dependencies = mutableListOf<ParsedDependency>()
		mapperInfo.parseDependencies(dependencies)
		return dependencies.asSequence()
	}

	private fun MapperInfo.parseDependencies(dependencies: MutableList<ParsedDependency>) {
		for (parameter in parameters) {
			parameter.converter?.addIfClassFunc(dependencies)
		}
	}

	private fun ConverterInfo.addIfClassFunc(dependencies: MutableList<ParsedDependency>) {
		when (this) {
			is ConverterInfo.ClassFunc -> {
				val paramName = paramName
				dependencies.add(
					ParsedDependency(
						parameter = paramName,
						type = type.type
					)
				)
			}

			else -> {}
		}
	}

	companion object {

		private const val LOG_TAG = "${Const.LOG_TAG} DependenciesParser"
	}
}