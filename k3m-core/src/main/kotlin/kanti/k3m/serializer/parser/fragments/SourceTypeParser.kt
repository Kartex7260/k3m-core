package kanti.k3m.serializer.parser.fragments

import kanti.k3m.K3MConst
import kanti.k3m.K3MLogger
import kanti.k3m.data.MapperInfo

class SourceTypeParser(
	private val logger: K3MLogger = K3MLogger.NonLogger
) : FragmentParser<String> {

	override fun parse(mapperInfo: MapperInfo): String {
		logger.debug(LOG_TAG, "parse(mapperInfo = $mapperInfo)")
		return mapperInfo.source.type
	}

	companion object {

		private const val LOG_TAG = "${K3MConst.LOG_TAG} SourceTypeParser"
	}
}