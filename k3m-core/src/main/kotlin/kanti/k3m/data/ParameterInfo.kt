package kanti.k3m.data

interface ParameterInfo {

	val sourceName: String
	val destinationName: String
}

private data class ParameterInfoImpl(
	override val sourceName: String,
	override val destinationName: String
) : ParameterInfo

fun ParameterInfo(
	sourceName: String,
	destinationName: String
): ParameterInfo = ParameterInfoImpl(
	sourceName = sourceName,
	destinationName = destinationName
)