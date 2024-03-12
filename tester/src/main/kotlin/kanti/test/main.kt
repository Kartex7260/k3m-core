package kanti.test

import kanti.car.toKartexCar
import kanti.home.toKartexHome
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