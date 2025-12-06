fun main() {
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357L)
    val input = readInput("Day03")
    println(part1(input))

    check(part2(testInput) == 3121910778619L)
    println(part2(input))
}

private fun part1(input: List<String>): Long {
    return input
        .map { it.toIntList() }
        .sumOf { it.batterie(2) }
}

private fun part2(input: List<String>): Long {
    return input
        .map { it.toIntList() }
        .sumOf { it.batterie(12) }
}

private fun String.toIntList() = toCharArray().map { it.digitToInt() }

private fun List<Int>.batterie(batSize: Int): Long {
    val idxList = mutableListOf<Int>()

    while (idxList.size < batSize) {
        val first = if (idxList.isEmpty()) 0 else idxList.last() + 1
        val end = size - batSize + idxList.size + 1
        val tmp = subList(first, end)
        val max = tmp.max()
        val idx = tmp.indexOf(max)
        idxList.addLast(idx + first)
    }

    return buildString { idxList.forEach { append(this@batterie[it]) } }.toLong()
}
