package kanti.k3m.serializer.fragments

import kanti.k3m.Const
import kanti.k3m.logger.K3MLogger
import kanti.k3m.parser.ParsedMapper

class ImportsSerializer(
	private val logger: K3MLogger = K3MLogger.NonLogger
) : FragmentSerializer<Iterable<String>> {

	override fun serialize(parsedMapper: ParsedMapper): Iterable<String> {
		logger.debug(LOG_TAG, "serialize(parsedMapper = $parsedMapper")
		val imports = mutableListOf<String>()
		for (import in parsedMapper.imports) {
			imports.add("import ${import.fullName}")
		}
		return imports
	}

	companion object {

		private const val LOG_TAG = "${Const.LOG_TAG} ImportsSerializer"
	}
}