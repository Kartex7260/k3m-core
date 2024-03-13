package kanti.k3m.serializer.parser

import kanti.k3m.data.*
import kanti.k3m.serializer.parser.fragments.*

interface MappingParser {

	fun parse(mapperInfo: MapperInfo): ParsedMapper

	interface MappingParserFactory {

		fun create(
			packageParser: FragmentParser<String> = PackageParser(),
			importsParser: FragmentParser<Iterable<ImportInfo>> = ImportsParser(),
			sourceTypeParser: FragmentParser<String> = SourceTypeParser(),
			destinationTypeParser: FragmentParser<String> = DestinationTypeParser(),
			dependenciesParser: FragmentParser<Iterable<DependencyInfo>> = DependenciesParser(),
			parametersParser: FragmentParser<Iterable<ParameterInfo>> = ParametersParser()
		): MappingParser
	}
}