package kanti.k3m.data

sealed class ConverterInfo {

	class GlobalFunc(
		val funcName: String,
		val packageName: String
	) : ConverterInfo()

	class ClassFunc(
		val function: String,
		val type: TypeInfo,
		private val dependencyParameterName: String?
	) : ConverterInfo() {

		val paramName: String get() = dependencyParameterName
			?: type.type.replaceFirstChar {
				if (it.isUpperCase())
					it.lowercase()[0]
				else
					it
			}
	}
}