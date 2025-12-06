fun main() {
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    val input = readInput("Day01")
    println(part1(input))

    check(part2(testInput) == 6)
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    var point = 50
    return input
        .map { it.first() to it.substring(1).toInt() }
        .count { (r, d) ->
            point += if (r == 'R') d else -d
            point = point.mod(100)
            point == 0
        }
}

private fun part2(input: List<String>): Int {
    var point = 50
    return input
        .map { it.first() to it.substring(1).toInt() }
        .sumOf { (r, d) ->
            var count = 0
            repeat(d) {
                point += if (r == 'R') 1 else -1
                point = point.mod(100)
                if (point == 0) count++
            }
            count
        }
}
