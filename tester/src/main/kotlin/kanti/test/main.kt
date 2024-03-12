package kanti.test

import kanti.k3m.toKartexUser

fun main() {
	val kantiUser = KantiUser(id = 9, name = "Test")
	val kartexUser = kantiUser.toKartexUser()
	println(kartexUser)
}

data class KantiUser(
	val id: Int = 0,
	val name: String = ""
)