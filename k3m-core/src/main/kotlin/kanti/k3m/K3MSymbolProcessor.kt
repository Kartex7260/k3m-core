package kanti.k3m

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import kanti.k3m.combinator.DefaultMapperCombinator
import kanti.k3m.data.MapperInfo
import kanti.k3m.data.fullName
import kanti.k3m.serializer.DefaultK3MSerializer
import kanti.k3m.serializer.K3MSerializer

abstract class K3MSymbolProcessor(
	private val logger: KSPLogger,
	private val codeGenerator: CodeGenerator,
	private val serializer: K3MSerializer = DefaultK3MSerializer()
) : SymbolProcessor {

	abstract fun processMaps(resolver: Resolver): List<MapperInfo>

	override fun process(resolver: Resolver): List<KSAnnotated> {
		val mappers = processMaps(resolver)
		val mapperCombinator = DefaultMapperCombinator()
		for (mapper in mappers) {
			try {
				val serializedMapper = serializer.serialize(mapper)
				mapperCombinator.add(
					packageName = mapper.packageName,
					sourceFullName = mapper.source.fullName,
					serializedMapper = serializedMapper
				)
			} catch (ex: Exception) {
				logger.exception(ex)
			}
		}
		return emptyList()
	}

	private fun CodeGenerator.generate(mapperInfo: MapperInfo, line: String) {
		val outputStream = createNewFile(
			dependencies = Dependencies.ALL_FILES,
			packageName = mapperInfo.packageName,
			fileName = "${mapperInfo.source.type}To${mapperInfo.destination.type}"
		)
		outputStream.write(line.toByteArray())
		outputStream.close()
	}
}