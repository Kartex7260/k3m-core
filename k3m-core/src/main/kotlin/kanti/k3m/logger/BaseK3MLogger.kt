package kanti.k3m.logger

abstract class BaseK3MLogger : K3MLogger {

	override fun debug(tag: String, message: String, symbol: Any?) {
		debug(format(tag, message), symbol)
	}

	abstract fun debug(line: String, symbol: Any?)

	override fun info(tag: String, message: String, symbol: Any?) {
		info(format(tag, message), symbol)
	}

	abstract fun info(line: String, symbol: Any?)

	override fun warn(tag: String, message: String, symbol: Any?) {
		warn(format(tag, message), symbol)
	}

	abstract fun warn(line: String, symbol: Any?)

	override fun error(tag: String, message: String, symbol: Any?) {
		error(format(tag, message), symbol)
	}

	abstract fun error(line: String, symbol: Any?)

	private fun format(tag: String, message: String): String {
		return "$tag: $message"
	}
}