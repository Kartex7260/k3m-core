package kanti.k3m.parser.fragments

import kanti.k3m.Const
import kanti.k3m.logger.K3MLogger
import kanti.k3m.symbol.MapperInfo

class SourceTypeParser(
	private val logger: K3MLogger = K3MLogger.NonLogger
) : FragmentParser<String> {

	override fun parse(mapperInfo: MapperInfo): String {
		logger.debug(LOG_TAG, "Parsing source type from the \"$mapperInfo\" mapper")
		return mapperInfo.source.type
	}

	companion object {

		private const val LOG_TAG = "${Const.LOG_TAG} SourceTypeParser"
	}
}