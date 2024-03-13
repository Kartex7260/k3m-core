package kartex.test

data class KartexUser(
	val id: Int = 0,
	val name: String = ""
)

data class KartexHome(
	val id: Int = 0,
	val type2: String = ""
)

data class KartexCar(
	val horses: String
)

data class SourceFuncConvertDestination(
	val string: String
)

data class SourceFuncExtConvertDestination(
	val string: String
)

data class StaticFuncDestination(
	val string: String
)
