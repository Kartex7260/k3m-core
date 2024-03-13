package kanti.k3m.generator

import com.google.devtools.ksp.processing.CodeGenerator
import kanti.k3m.combinator.CombinedMappers

interface MapperGenerator {

	fun generate(codeGenerator: CodeGenerator, combinedMappers: CombinedMappers)
}