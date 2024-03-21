package kanti.k3m.serializer.parser.fragments

import kanti.k3m.symbol.MapperInfo

interface FragmentParser<Result> {

	fun parse(mapperInfo: MapperInfo): Result
}