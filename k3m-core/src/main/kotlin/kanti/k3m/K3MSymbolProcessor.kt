package kanti.k3m

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import kanti.k3m.combinator.DefaultMapperCombinator
import kanti.k3m.combinator.MapperCombinator
import kanti.k3m.data.MapperInfo
import kanti.k3m.data.fullName
import kanti.k3m.generator.DefaultMapperGenerator
import kanti.k3m.generator.MapperGenerator
import kanti.k3m.serializer.DefaultK3MSerializer
import kanti.k3m.serializer.K3MSerializer

abstract class K3MSymbolProcessor(
	private val logger: KSPLogger,
	private val codeGenerator: CodeGenerator,
	private val serializer: K3MSerializer = DefaultK3MSerializer(),
	private val mapperCombinator: MapperCombinator.MapperCombinatorProvider =
		DefaultMapperCombinator.DefaultMapperCombinatorProvider(),
	private val mapperGenerator: MapperGenerator = DefaultMapperGenerator()
) : SymbolProcessor {

	abstract fun processMaps(resolver: Resolver): List<MapperInfo>

	override fun process(resolver: Resolver): List<KSAnnotated> {
		val mappers = processMaps(resolver)
		val mapperCombinator = mapperCombinator.create()
		for (mapper in mappers) {
			try {
				val serializedMapper = serializer.serialize(mapper)
				mapperCombinator.add(
					packageName = mapper.packageName,
					sourceFullName = mapper.source.fullName,
					sourceType = mapper.source.type,
					serializedMapper = serializedMapper
				)
			} catch (ex: Exception) {
				logger.exception(ex)
			}
		}
		for (combinedMappers in mapperCombinator.combinedMappers) {
			mapperGenerator.generate(codeGenerator, combinedMappers)
		}
		return emptyList()
	}
}