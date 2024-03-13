package kanti.k3m

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import kanti.k3m.data.ConverterInfo
import kanti.k3m.data.MapperInfo
import kanti.k3m.data.ParameterLinkInfo
import kanti.k3m.data.TypeInfo

class K3MTestSymbolProcessor(
	logger: KSPLogger,
	codeGenerator: CodeGenerator
) : K3MSymbolProcessor(logger, codeGenerator) {

	private var finished = false

	override fun processMaps(resolver: Resolver): List<MapperInfo> {
		if (finished)
			return emptyList()
		finished = true
		return listOf(
			MapperInfo(
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
			),
			MapperInfo(
				packageName = "kanti.home",
				source = TypeInfo(
					packageName = "kanti.test",
					type = "KantiHome"
				),
				destination = TypeInfo(
					packageName = "kartex.test",
					type = "KartexHome"
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
						sourceName = "type",
						destinationName = "type2",
						sourceType = TypeInfo(
							packageName = "kanti.test",
							type = "HomeType"
						),
						destinationType = TypeInfo(
							packageName = "kotlin",
							type = "String"
						),
						converter = ConverterInfo.GlobalFunc(
							funcName = "convert",
							packageName = "kanti.test.conv"
						)
					)
				)
			),
			MapperInfo(
				packageName = "kanti.car",
				source = TypeInfo(
					packageName = "kanti.test",
					type = "KantiCar"
				),
				destination = TypeInfo(
					packageName = "kartex.test",
					type = "KartexCar"
				),
				parameters = listOf(
					ParameterLinkInfo(
						sourceName = "engine",
						destinationName = "horses",
						sourceType = TypeInfo(
							packageName = "kanti.test",
							type = "Engine"
						),
						destinationType = TypeInfo(
							packageName = "kotlin",
							type = "String"
						),
						converter = ConverterInfo.ClassFunc(
							function = "convert",
							type = TypeInfo(
								packageName = "kanti.test.conv",
								type = "EngineConverter"
							),
							dependencyParameterName = "conv"
						)
					)
				)
			),
			MapperInfo(
				packageName = "kanti.car",
				source = TypeInfo(
					packageName = "kanti.test",
					type = "KantiCar"
				),
				destination = TypeInfo(
					packageName = "kartex.test",
					type = "KartexCar"
				),
				parameters = listOf(
					ParameterLinkInfo(
						sourceName = "engine",
						destinationName = "horses",
						sourceType = TypeInfo(
							packageName = "kanti.test",
							type = "Engine"
						),
						destinationType = TypeInfo(
							packageName = "kotlin",
							type = "String"
						),
						converter = ConverterInfo.GlobalFunc(
							funcName = "convertEngine",
							packageName = "kanti.test.conv"
						)
					)
				)
			),
			MapperInfo(
				packageName = "kanti.sourceFunc",
				source = TypeInfo(
					packageName = "kanti.test",
					type = "SourceFuncConvertSource"
				),
				destination = TypeInfo(
					packageName = "kartex.test",
					type = "SourceFuncConvertDestination"
				),
				parameters = listOf(
					ParameterLinkInfo(
						sourceName = "int",
						destinationName = "string",
						sourceType = TypeInfo(
							packageName = "kotlin",
							type = "Int"
						),
						destinationType = TypeInfo(
							packageName = "kotlin",
							type = "String"
						),
						converter = ConverterInfo.SourceFunc(
							function = "toString"
						)
					)
				)
			),
			MapperInfo(
				packageName = "kanti.sourceFunc",
				source = TypeInfo(
					packageName = "kanti.test",
					type = "SourceFuncExtConvertSource"
				),
				destination = TypeInfo(
					packageName = "kartex.test",
					type = "SourceFuncExtConvertDestination"
				),
				parameters = listOf(
					ParameterLinkInfo(
						sourceName = "int",
						destinationName = "string",
						sourceType = TypeInfo(
							packageName = "kotlin",
							type = "Int"
						),
						destinationType = TypeInfo(
							packageName = "kotlin",
							type = "String"
						),
						converter = ConverterInfo.SourceFuncExtension(
							function = "toTestString",
							packageName = "kanti.test.conv"
						)
					)
				)
			),
			MapperInfo(
				packageName = "kanti.staticFunc",
				source = TypeInfo(
					packageName = "kanti.test",
					type = "StaticFuncSource"
				),
				destination = TypeInfo(
					packageName = "kartex.test",
					type = "StaticFuncDestination"
				),
				parameters = listOf(
					ParameterLinkInfo(
						sourceName = "int",
						destinationName = "string",
						sourceType = TypeInfo(
							packageName = "kotlin",
							type = "Int"
						),
						destinationType = TypeInfo(
							packageName = "kotlin",
							type = "String"
						),
						converter = ConverterInfo.ClassFuncStatic(
							function = "conv",
							type = TypeInfo(
								packageName = "kanti.test.conv",
								type = "Static"
							)
						)
					)
				)
			)
		)
	}
}