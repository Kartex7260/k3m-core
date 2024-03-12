package kanti.k3m

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class K3MTestSymbolProcessorProvider : SymbolProcessorProvider {

	override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
		return K3MTestSymbolProcessor(
			logger = environment.logger,
			codeGenerator = environment.codeGenerator
		)
	}
}