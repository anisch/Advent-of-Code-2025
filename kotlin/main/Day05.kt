fun main() {
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 3)
    val input = readInput("Day05")
    println(part1(input))

    check(part2(testInput) == 14L)
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    val freshList = input
        .takeWhile { it != "" }
        .map { it.split("-") }
        .map { (f, l) -> f.toLong()..l.toLong() }

    val ingredientList = input
        .dropWhile { it != "" }
        .drop(1)
        .map { it.toLong() }

    return ingredientList.count { ingredient ->
        freshList.any { ingredient in it }
    }
}

private fun part2(input: List<String>): Long {
    return input
        .takeWhile { it != "" }
        .map { it.split("-") }
        .map { (f, l) -> f.toLong()..l.toLong() }
        .sortedBy { it.first }
        .fold(listOf<LongRange>()) { acc, cur ->
            when {
                acc.isEmpty() -> acc.plusElement(cur)
                !acc.last().isUnique(cur) -> acc.dropLast(1).plusElement(acc.last().unique(cur))
                else -> acc.plusElement(cur)
            }
        }
        .sumOf { it.size }
}

private val LongRange.size
    get() = last - first + 1

private fun LongRange.isUnique(other: LongRange): Boolean = when {
    first in other -> false
    last in other -> false
    other.first in this -> false
    other.last in this -> false
    else -> true
}

private fun LongRange.unique(other: LongRange): LongRange {
    val h = listOf(first, last, other.first, other.last)
    return h.min()..h.max()
}
