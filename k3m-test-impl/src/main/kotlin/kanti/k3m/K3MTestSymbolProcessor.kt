package kanti.k3m

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import kanti.k3m.data.MappingInfo
import kanti.k3m.data.ParameterLinkInfo
import kanti.k3m.data.TypeInfo

class K3MTestSymbolProcessor(
	logger: KSPLogger,
	codeGenerator: CodeGenerator
) : K3MSymbolProcessor(logger, codeGenerator) {

	override fun processMaps(resolver: Resolver): List<MappingInfo> {
		return listOf(
			MappingInfo(
				packageName = "kanti.test",
				source = TypeInfo(
					packageName = "kanti.test",
					type = "KantiUser"
				),
				destination = TypeInfo(
					packageName = "kartex.test",
					type = "KartexUser"
				),
				parameters = listOf(
					ParameterLinkInfo(
						sourceName = "id",
						destinationName = "id",
						sourceType = TypeInfo(
							packageName = "kotlin",
							type = "Int"
						),
						destinationType = TypeInfo(
							packageName = "kotlin",
							type = "Int"
						),
						converter = null
					),
					ParameterLinkInfo(
						sourceName = "name",
						destinationName = "name",
						sourceType = TypeInfo(
							packageName = "kotlin",
							type = "String"
						),
						destinationType = TypeInfo(
							packageName = "kotlin",
							type = "String"
						),
						converter = null
					)
				)
			)
		)
	}
}