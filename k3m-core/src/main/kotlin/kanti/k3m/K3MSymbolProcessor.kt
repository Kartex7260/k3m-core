package kanti.k3m

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated

class K3MSymbolProcessor : SymbolProcessor {

	override fun process(resolver: Resolver): List<KSAnnotated> {
		return emptyList()
	}
}