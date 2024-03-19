package kanti.k3m.data

data class TypeInfo(
	val packageName: String,
	val type: String
) {

	val fullName: String get() = "$packageName.$type"

	override fun toString(): String {
		return "TypeInfo($fullName)"
	}
}