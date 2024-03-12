package kanti.test

import kanti.home.toKartexHome

fun main() {
	val kantiUser = KantiUser(id = 9, name = "Test")
	val kartexUser = kantiUser.toKartexUser()
	println(kartexUser)

	val kantiHome = KantiHome(id = 9, type = HomeType.Own)
	val kartexHome = kantiHome.toKartexHome()
	println(kartexHome)
}

data class KantiUser(
	val id: Int = 0,
	val name: String = ""
)

data class KantiHome(
	val id: Int = 0,
	val type: HomeType
)

enum class HomeType {
	Own, Rent
}