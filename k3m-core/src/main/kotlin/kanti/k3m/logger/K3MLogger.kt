package kanti.k3m.logger

interface K3MLogger {

	fun debug(tag: String, message: String, symbol: Any? = null)
	fun info(tag: String, message: String, symbol: Any? = null)
	fun warn(tag: String, message: String, symbol: Any? = null)
	fun error(tag: String, message: String, symbol: Any? = null)

	fun exception(e: Throwable)

	companion object {

		val NonLogger = object : K3MLogger {
			override fun debug(tag: String, message: String, symbol: Any?) {}
			override fun info(tag: String, message: String, symbol: Any?) {}
			override fun warn(tag: String, message: String, symbol: Any?) {}
			override fun error(tag: String, message: String, symbol: Any?) {}

			override fun exception(e: Throwable) {}
		}
	}
}