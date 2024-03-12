package kanti.k3m.data

data class TypeInfo(
	val packageName: String,
	val type: String
)

val TypeInfo.fullName: String get() = "$packageName.$type"