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

	override fun parse(mappingInfo: MappingInfo): ParsedMapper {
		return ParsedMapper(
			packageName = packageParser.parse(mappingInfo),
			imports = importsParser.parse(mappingInfo),
			sourceType = sourceTypeParser.parse(mappingInfo),
			destinationType = destinationTypeParser.parse(mappingInfo),
			dependencies = dependenciesParser.parse(mappingInfo),
			parameters = parametersParser.parse(mappingInfo)
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