package kanti.k3m

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import kanti.k3m.data.MappingInfo
import kanti.k3m.serializer.DefaultK3MSerializer
import kanti.k3m.serializer.K3MSerializer
import kanti.k3m.serializer.parser.DefaultMappingParserFactory
import kanti.k3m.serializer.parser.MappingParserFactory

abstract class K3MSymbolProcessor(
	private val logger: KSPLogger,
	private val codeGenerator: CodeGenerator,
	private val serializer: K3MSerializer = DefaultK3MSerializer(),
	private val mappingParserFactory: MappingParserFactory = DefaultMappingParserFactory()
) : SymbolProcessor {

	abstract fun processMaps(resolver: Resolver): List<MappingInfo>

	override fun process(resolver: Resolver): List<KSAnnotated> {
		val mappings = processMaps(resolver)
		for (mapping in mappings) {
			val parser = mappingParserFactory.create(mapping)
			val serialized = serializer.serialize(parser)
		}
		return emptyList()
	}
}