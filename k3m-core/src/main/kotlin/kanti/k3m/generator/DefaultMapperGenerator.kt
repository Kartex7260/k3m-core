package kanti.k3m.generator

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import kanti.k3m.combinator.CombinedMappers

class DefaultMapperGenerator : MapperGenerator {

	override fun generate(codeGenerator: CodeGenerator, combinedMappers: CombinedMappers) {
		codeGenerator.apply {
			val outputStream = createNewFile(
				dependencies = Dependencies.ALL_FILES,
				packageName = combinedMappers.packageName,
				fileName = "From${combinedMappers.sourceType}"
			)
			outputStream.write(combinedMappers.combine().toByteArray())
			outputStream.close()
		}
	}
}