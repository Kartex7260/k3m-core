package kanti.k3m

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import kanti.k3m.data.MappingInfo

class K3MTestSymbolProcessor(
	logger: KSPLogger,
	codeGenerator: CodeGenerator
) : K3MSymbolProcessor(logger, codeGenerator) {

	override fun processMaps(resolver: Resolver): List<MappingInfo> {
		return listOf()
	}
}