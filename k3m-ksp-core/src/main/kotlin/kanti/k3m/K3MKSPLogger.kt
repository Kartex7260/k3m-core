package kanti.k3m

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSNode

class K3MKSPLogger(
	private val kspLogger: KSPLogger
) : K3MLogger {

	override fun debug(tag: String, message: String, symbol: Any?) {
		if (symbol is KSNode)
			kspLogger.logging("$tag: $message", symbol)
		else
			kspLogger.logging("$tag: $message", null)
	}

	override fun error(tag: String, message: String, symbol: Any?) {
		if (symbol is KSNode)
			kspLogger.error("$tag: $message", symbol)
		else
			kspLogger.error("$tag: $message", null)
	}

	override fun exception(e: Throwable) {
		kspLogger.exception(e)
	}

	override fun info(tag: String, message: String, symbol: Any?) {
		if (symbol is KSNode)
			kspLogger.info("$tag: $message", symbol)
		else
			kspLogger.info("$tag: $message", null)
	}

	override fun warn(tag: String, message: String, symbol: Any?) {
		if (symbol is KSNode)
			kspLogger.warn("$tag: $message", symbol)
		else
			kspLogger.warn("$tag: $message", null)
	}
}