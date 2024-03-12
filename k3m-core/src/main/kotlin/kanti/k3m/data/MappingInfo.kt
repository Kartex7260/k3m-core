package kanti.k3m.data

interface MappingInfo {

	val `package`: String

	val sourcePackage: String
	val destinationPackage: String

	val sourceType: String
	val destinationType: String

	val parameters: Iterable<ParameterInfo>
}

private data class MappingInfoImpl(
	override val `package`: String,
	override val sourcePackage: String,
	override val destinationPackage: String,
	override val sourceType: String,
	override val destinationType: String,
	override val parameters: Iterable<ParameterInfo>
) : MappingInfo

fun MappingInfo(
	packageName: String,
	sourcePackage: String,
	destinationPackage: String,
	sourceClassName: String,
	destinationClassName: String,
	parameters: Iterable<ParameterInfo>
): MappingInfo = MappingInfoImpl(
	`package` = packageName,
	sourcePackage = sourcePackage,
	destinationPackage = destinationPackage,
	sourceType = sourceClassName,
	destinationType = destinationClassName,
	parameters = parameters
)