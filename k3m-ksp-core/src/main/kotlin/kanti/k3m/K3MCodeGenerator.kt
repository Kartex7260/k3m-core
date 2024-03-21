package kanti.k3m

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import kanti.k3m.combinator.CombinedMappers
import kanti.k3m.logger.K3MLogger

class K3MCodeGenerator(
	private val codeGenerator: CodeGenerator,
	private val logger: K3MLogger = K3MLogger.NonLogger
) : MapperGenerator {

	override fun generate(combinedMappers: CombinedMappers) {
		logger.debug(LOG_TAG, "generate(combinedMappers = $combinedMappers)")
		val outputStream = codeGenerator.createNewFile(
			dependencies = Dependencies.ALL_FILES,
			packageName = combinedMappers.packageName,
			fileName = fileName(combinedMappers.sourceType)
		)
		try {
			outputStream.write(combinedMappers.combine().toByteArray())
		} catch (ex: Exception) {
			logger.exception(ex)
		} finally {
			outputStream.close()
		}
	}

	private fun fileName(source: String): String {
		return "From$source"
	}

	companion object {

		private const val LOG_TAG = "${Const.LOG_TAG} K3MCodeGenerator"
	}
}