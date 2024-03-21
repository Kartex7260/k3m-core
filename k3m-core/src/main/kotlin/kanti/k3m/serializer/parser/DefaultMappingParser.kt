package kanti.k3m.serializer.parser

import kanti.k3m.K3MConst
import kanti.k3m.K3MLogger
import kanti.k3m.symbol.MapperInfo
import kanti.k3m.serializer.parser.fragments.*

class DefaultMappingParser(
	private val logger: K3MLogger = K3MLogger.NonLogger,
	private val packageParser: FragmentParser<String> = PackageParser(logger),
	private val importsParser: FragmentParser<Iterable<ImportInfo>> = ImportsParser(logger),
	private val sourceTypeParser: FragmentParser<String> = SourceTypeParser(logger),
	private val destinationTypeParser: FragmentParser<String> = DestinationTypeParser(logger),
	private val dependenciesParser: FragmentParser<Iterable<DependencyInfo>> = DependenciesParser(logger),
	private val parametersParser: FragmentParser<Iterable<ParameterInfo>> = ParametersParser()
) : MappingParser {

	override fun parse(mapperInfo: MapperInfo): ParsedMapper {
		logger.debug(LOG_TAG, "parse(mapperInfo = $mapperInfo)")
		return ParsedMapper(
			packageName = packageParser.parse(mapperInfo),
			imports = importsParser.parse(mapperInfo),
			sourceType = sourceTypeParser.parse(mapperInfo),
			destinationType = destinationTypeParser.parse(mapperInfo),
			dependencies = dependenciesParser.parse(mapperInfo),
			parameters = parametersParser.parse(mapperInfo)
		)
	}

	companion object {

		private const val LOG_TAG = "${K3MConst.LOG_TAG} DefaultMappingParser"
	}

	class DefaultMappingParserFactory(
		private val logger: K3MLogger = K3MLogger.NonLogger
	) : MappingParser.MappingParserFactory {

		override fun create(
			packageParser: FragmentParser<String>,
			importsParser: FragmentParser<Iterable<ImportInfo>>,
			sourceTypeParser: FragmentParser<String>,
			destinationTypeParser: FragmentParser<String>,
			dependenciesParser: FragmentParser<Iterable<DependencyInfo>>,
			parametersParser: FragmentParser<Iterable<ParameterInfo>>
		): MappingParser {
			return DefaultMappingParser(
				logger = logger,
				packageParser = packageParser,
				importsParser = importsParser,
				sourceTypeParser = sourceTypeParser,
				destinationTypeParser = destinationTypeParser,
				dependenciesParser = dependenciesParser,
				parametersParser = parametersParser
			)
		}
	}
}