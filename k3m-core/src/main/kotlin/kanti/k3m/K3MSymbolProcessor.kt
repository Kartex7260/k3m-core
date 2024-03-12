package kanti.k3m

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import kanti.k3m.data.MappingInfo

abstract class K3MSymbolProcessor(
	private val logger: KSPLogger,
	private val codeGenerator: CodeGenerator
) : SymbolProcessor {

	abstract fun processMaps(resolver: Resolver): List<MappingInfo>

	override fun process(resolver: Resolver): List<KSAnnotated> {
		val mappings = processMaps(resolver)
		return emptyList()
	}
}