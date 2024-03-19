package kanti.k3m

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import kanti.k3m.data.MapperInfo

abstract class K3MSymbolProcessor(
	kspLogger: KSPLogger,
	codeGenerator: CodeGenerator
) : SymbolProcessor {

	private val logger: K3MLogger = K3MKSPLogger(kspLogger = kspLogger)

	private val k3mProcessor = K3MProcessor(
		logger = logger,
		mapperGenerator = K3MCodeGenerator(codeGenerator = codeGenerator)
	)

	abstract fun resolveMappers(logger: K3MLogger, resolver: Resolver): List<MapperInfo>

	override fun process(resolver: Resolver): List<KSAnnotated> {
		val mappers = resolveMappers(logger, resolver)
		k3mProcessor.process(mappers)
		return emptyList()
	}
}