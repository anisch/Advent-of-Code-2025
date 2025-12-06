fun main() {
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 1227775554L)
    val input = readInput("Day02")
    println(part1(input))

    check(part2(testInput) == 4174379265L)
    println(part2(input))
}

private fun part1(input: List<String>): Long {
    return input.first()
        .split(",")
        .map { it.split("-") }
        .map { it.first().toLong()..it.last().toLong() }
        .sumOf { range ->
            range.filter { num ->
                val s = num.toString()
                val mid = s.length ushr 1
                val sStart = s.substring(0..<mid)
                val sEnd = s.substring(mid..s.lastIndex)
                sStart == sEnd
            }.sum()
        }
}

private fun part2(input: List<String>): Long {
    return input.first()
        .split(",")
        .map { it.split("-") }
        .map { it.first().toLong()..it.last().toLong() }
        .sumOf { range ->
            range.filter { num -> num.invalidSequenz() }.sum()
        }
}

private fun Long.invalidSequenz(): Boolean {
    val s = toString()
    return (1..(s.length ushr 1)).any { d ->
        val split = s.chunked(d)
        split.all { it == split.first() }
    }
}
