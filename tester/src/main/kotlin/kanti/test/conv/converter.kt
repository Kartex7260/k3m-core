package kanti.test.conv

import kanti.test.Engine
import kanti.test.HomeType

fun convert(type: HomeType): String {
	return type.name
}

class EngineConverter {

	fun convert(engine: Engine): String {
		return engine.horses.toString()
	}
}

fun convertEngine(engine: Engine): String {
	return engine.horses.toString()
}

fun Int.toTestString(): String {
	return toString()
}

object Static {

	fun conv(int: Int): String {
		return int.toString()
	}
}