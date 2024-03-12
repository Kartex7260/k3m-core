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