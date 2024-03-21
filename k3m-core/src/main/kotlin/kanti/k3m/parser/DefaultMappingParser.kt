package kanti.k3m.parser

import kanti.k3m.Const
import kanti.k3m.logger.K3MLogger
import kanti.k3m.parser.fragments.*
import kanti.k3m.symbol.MapperInfo

class DefaultMappingParser(
	private val logger: K3MLogger = K3MLogger.NonLogger,
	private val packageParser: FragmentParser<String> = PackageParser(logger),
	private val importsParser: FragmentParser<Sequence<ParsedImport>> = ImportsParser(logger),
	private val sourceTypeParser: FragmentParser<String> = SourceTypeParser(logger),
	private val destinationTypeParser: FragmentParser<String> = DestinationTypeParser(logger),
	private val dependenciesParser: FragmentParser<Sequence<ParsedDependency>> = DependenciesParser(logger),
	private val parametersParser: FragmentParser<Sequence<ParsedParameter>> = ParametersParser(logger)
) : MappingParser {

	override fun parse(mapperInfo: MapperInfo): ParsedMapper {
		logger.debug(LOG_TAG, "Parsing the \"$mapperInfo\" mapper")
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

		private const val LOG_TAG = "${Const.LOG_TAG} DefaultMappingParser"
	}

	class DefaultMappingParserFactory(
		private val logger: K3MLogger = K3MLogger.NonLogger
	) : MappingParser.MappingParserFactory {

		override fun create(
			packageParser: FragmentParser<String>,
			importsParser: FragmentParser<Sequence<ParsedImport>>,
			sourceTypeParser: FragmentParser<String>,
			destinationTypeParser: FragmentParser<String>,
			dependenciesParser: FragmentParser<Sequence<ParsedDependency>>,
			parametersParser: FragmentParser<Sequence<ParsedParameter>>
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