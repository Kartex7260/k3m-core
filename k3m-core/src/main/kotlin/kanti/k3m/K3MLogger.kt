package kanti.k3m

interface K3MLogger {

	fun debug(tag: String, message: String, symbol: Any? = null)
	fun info(message: String, symbol: Any? = null)
	fun warn(message: String, symbol: Any? = null)
	fun error(message: String, symbol: Any? = null)

	fun exception(e: Throwable)
}