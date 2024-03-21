package kanti.k3m.parser

import kanti.k3m.parser.fragments.*
import kanti.k3m.symbol.MapperInfo

interface MappingParser {

	fun parse(mapperInfo: MapperInfo): ParsedMapper

	interface MappingParserFactory {

		fun create(
			packageParser: FragmentParser<String> = PackageParser(),
			importsParser: FragmentParser<Sequence<ParsedImport>> = ImportsParser(),
			sourceTypeParser: FragmentParser<String> = SourceTypeParser(),
			destinationTypeParser: FragmentParser<String> = DestinationTypeParser(),
			dependenciesParser: FragmentParser<Sequence<ParsedDependency>> = DependenciesParser(),
			parametersParser: FragmentParser<Sequence<ParsedParameter>> = ParametersParser()
		): MappingParser
	}
}