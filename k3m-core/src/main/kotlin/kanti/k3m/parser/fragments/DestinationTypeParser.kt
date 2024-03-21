package kanti.k3m.parser.fragments

import kanti.k3m.K3MConst
import kanti.k3m.logger.K3MLogger
import kanti.k3m.symbol.MapperInfo

class DestinationTypeParser(
	private val logger: K3MLogger = K3MLogger.NonLogger
) : FragmentParser<String> {

	override fun parse(mapperInfo: MapperInfo): String {
		logger.debug(LOG_TAG, "parse(mapperInfo = $mapperInfo)")
		return mapperInfo.destination.type
	}

	companion object {

		private const val LOG_TAG = "${K3MConst.LOG_TAG} DestinationTypeParser"
	}
}