package kanti.test

import kanti.car.toKartexCar
import kanti.home.toKartexHome
import kanti.sourceFunc.toSourceFuncConvertDestination
import kanti.sourceFunc.toSourceFuncExtConvertDestination
import kanti.staticFunc.toStaticFuncDestination
import kanti.test.conv.EngineConverter

fun main() {
	val kantiUser = KantiUser(id = 9, name = "Test")
	val kartexUser = kantiUser.toKartexUser()
	println(kartexUser)

	val kantiHome = KantiHome(id = 9, type = HomeType.Own)
	val kartexHome = kantiHome.toKartexHome()
	println(kartexHome)

	val converter = EngineConverter()
	val kantiCar = KantiCar()
	val kartexCar = kantiCar.toKartexCar(conv = converter)
	println(kartexCar)
	val kantiCar2 = kantiCar.toKartexCar()
	println(kantiCar2)

	val sourceFunConvertSource = SourceFuncConvertSource()
	val sourceFuncConvertDestination = sourceFunConvertSource.toSourceFuncConvertDestination()
	println(sourceFuncConvertDestination)

	val sourceFunExtConvertSource = SourceFuncExtConvertSource()
	val sourceFuncExtConvertDestination = sourceFunExtConvertSource.toSourceFuncExtConvertDestination()
	println(sourceFuncExtConvertDestination)

	val staticFuncSource = StaticFuncSource()
	val staticFuncDestination = staticFuncSource.toStaticFuncDestination()
	println(staticFuncDestination)
}

data class KantiUser(
	val id: Int = 0,
	val name: String = ""
)

data class KantiHome(
	val id: Int = 0,
	val type: HomeType = HomeType.Own
)

enum class HomeType {
	Own
}

data class KantiCar(
	val engine: Engine = Engine()
)

class Engine(val horses: Int = 600)

data class SourceFuncConvertSource(
	val int: Int = 0
)

data class SourceFuncExtConvertSource(
	val int: Int = 0
)

data class StaticFuncSource(
	val int: Int = 0
)