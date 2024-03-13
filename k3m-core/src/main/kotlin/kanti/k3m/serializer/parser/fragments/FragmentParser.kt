package kanti.k3m.serializer.parser.fragments

import kanti.k3m.data.MappingInfo

interface FragmentParser<Result> {

	fun parse(mappingInfo: MappingInfo): Result
}