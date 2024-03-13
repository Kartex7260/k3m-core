package kanti.k3m.serializer.parser

import kanti.k3m.data.*
import kanti.k3m.serializer.parser.fragments.*

class DefaultMappingParser(
	private val packageParser: FragmentParser<String> = PackageParser(),
	private val importsParser: FragmentParser<Iterable<ImportInfo>> = ImportsParser(),
	private val sourceTypeParser: FragmentParser<String> = SourceTypeParser(),
	private val destinationTypeParser: FragmentParser<String> = DestinationTypeParser(),
	private val dependenciesParser: FragmentParser<Iterable<DependencyInfo>> = DependenciesParser(),
	private val parametersParser: FragmentParser<Iterable<ParameterInfo>> = ParametersParser()
) : MappingParser {

	override fun parse(mapperInfo: MapperInfo): ParsedMapper {
		return ParsedMapper(
			packageName = packageParser.parse(mapperInfo),
			imports = importsParser.parse(mapperInfo),
			sourceType = sourceTypeParser.parse(mapperInfo),
			destinationType = destinationTypeParser.parse(mapperInfo),
			dependencies = dependenciesParser.parse(mapperInfo),
			parameters = parametersParser.parse(mapperInfo)
		)
	}

	class DefaultMappingParserFactory : MappingParser.MappingParserFactory {

		override fun create(
			packageParser: FragmentParser<String>,
			importsParser: FragmentParser<Iterable<ImportInfo>>,
			sourceTypeParser: FragmentParser<String>,
			destinationTypeParser: FragmentParser<String>,
			dependenciesParser: FragmentParser<Iterable<DependencyInfo>>,
			parametersParser: FragmentParser<Iterable<ParameterInfo>>
		): MappingParser {
			return DefaultMappingParser(
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