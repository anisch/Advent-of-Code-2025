fun main() {
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    val input = readInput("Day04")
    println(part1(input))

    check(part2(testInput) == 43)
    println(part2(input))
}

private typealias Grid = List<String>

private fun part1(input: List<String>): Int {
    return (0..input.lastIndex).sumOf { y ->
        (0..input[y].lastIndex).count { x ->
            val vec = Vec(y, x)
            input.isPaperRoll(vec) && input.fewer4(vec)
        }
    }
}

private fun part2(input: List<String>): Int {
    val tmp = input.toMutableList()

    var count = 0
    do {
        var modified = false
        count += (0..tmp.lastIndex).sumOf { y ->
            (0..tmp[y].lastIndex).count { x ->
                val vec = Vec(y, x)
                if (tmp.isPaperRoll(vec) && tmp.fewer4(vec)) {
                    tmp[y] = tmp[y].replaceRange(x, x + 1, ".")
                    modified = true
                    true
                } else false
            }
        }
    } while (modified)

    return count
}

private fun Grid.fewer4(vec: Vec): Boolean {
    var neighbours = 0

    if (isPaperRoll(Vec(vec.y - 1, vec.x - 1))) neighbours++
    if (isPaperRoll(Vec(vec.y - 1, vec.x))) neighbours++
    if (isPaperRoll(Vec(vec.y - 1, vec.x + 1))) neighbours++

    if (isPaperRoll(Vec(vec.y, vec.x - 1))) neighbours++
    if (isPaperRoll(Vec(vec.y, vec.x + 1))) neighbours++

    if (isPaperRoll(Vec(vec.y + 1, vec.x - 1))) neighbours++
    if (isPaperRoll(Vec(vec.y + 1, vec.x))) neighbours++
    if (isPaperRoll(Vec(vec.y + 1, vec.x + 1))) neighbours++

    return neighbours < 4
}

private fun Grid.isPaperRoll(vec: Vec): Boolean {
    if (vec.y !in 0..lastIndex) return false
    if (vec.x !in 0..this[vec.y].lastIndex) return false
    return this[vec.y][vec.x] == '@'
}
