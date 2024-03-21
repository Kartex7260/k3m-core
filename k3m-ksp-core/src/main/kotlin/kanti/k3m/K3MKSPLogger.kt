package kanti.k3m

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSNode
import kanti.k3m.logger.BaseK3MLogger

class K3MKSPLogger(
	private val kspLogger: KSPLogger
) : BaseK3MLogger() {

	override fun debug(line: String, symbol: Any?) {
		if (symbol is KSNode)
			kspLogger.logging(line, symbol)
		else
			kspLogger.logging(line, null)
	}

	override fun error(line: String, symbol: Any?) {
		if (symbol is KSNode)
			kspLogger.error(line, symbol)
		else
			kspLogger.error(line, null)
	}

	override fun info(line: String, symbol: Any?) {
		if (symbol is KSNode)
			kspLogger.info(line, symbol)
		else
			kspLogger.info(line, null)
	}

	override fun warn(line: String, symbol: Any?) {
		if (symbol is KSNode)
			kspLogger.warn(line, symbol)
		else
			kspLogger.warn(line, null)
	}

	override fun exception(e: Throwable) {
		kspLogger.exception(e)
	}
}