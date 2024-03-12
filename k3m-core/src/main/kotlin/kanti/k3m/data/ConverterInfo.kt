package kanti.k3m.data

sealed class ConverterInfo {

	class GlobalFunc(
		val funcName: String,
		val packageName: String
	) : ConverterInfo()

	class ClassFunc(
		val function: String,
		val type: TypeInfo,
		val dependencyParameterName: String?
	) : ConverterInfo()
}