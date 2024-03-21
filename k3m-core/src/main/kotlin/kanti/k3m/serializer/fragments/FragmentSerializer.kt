package kanti.k3m.serializer.fragments

import kanti.k3m.parser.ParsedMapper

interface FragmentSerializer<Result> {

	fun serialize(parsedMapper: ParsedMapper): Result
}