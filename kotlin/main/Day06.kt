fun main() {
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 4277556L)
    val input = readInput("Day06")
    println(part1(input)) // 5322004718681

    check(part2(testInput) == 3263827L)
    println(part2(input)) // 9876636978528
}

private val regexOps = """[+*]""".toRegex()
private val regexNumbers = """\d+""".toRegex()

private fun part1(input: List<String>): Long {
    val ops = regexOps
        .findAll(input.last())
        .map { it.value }
        .toList()

    val nums = input
        .dropLast(1)
        .map { regexNumbers.findAll(it).map { m -> m.value.toLong() } }
        .map { it.toList() }

    return nums[0].indices.sumOf { x ->
        (1..nums.lastIndex).fold(nums[0][x]) { acc, cur ->
            when (ops[x]) {
                "+" -> acc + nums[cur][x]
                "*" -> acc * nums[cur][x]
                else -> error("Invalid op")
            }
        }
    }
}

private fun part2(input: List<String>): Long {
    val ops = regexOps
        .findAll(input.last())
        .map { it.value }
        .toList()

    return input
        .dropLast(1)
        .toRTL()
        .fold(emptyList<List<Long>>()) { acc, cur ->
            val tmp = acc.lastOrNull() ?: emptyList()
            if (cur.isBlank()) {
                acc.plusElement(emptyList())
            } else {
                val num = cur.trim().toLong()
                acc.dropLast(1).plusElement(tmp.plusElement(num))
            }
        }
        .mapIndexed { i, l ->
            when (ops[i]) {
                "+" -> l.sum()
                "*" -> l.fold(1L) { acc, l -> acc * l }
                else -> error("Invalid op")
            }
        }
        .sum()
}

private fun List<String>.toRTL(): List<String> {
    val h = this
    val max = maxOf { it.length }
    val result = mutableListOf<String>()

    for (x in 0..<max) {
        val s = buildString {
            for (y in h.indices) {
                if (x > h[y].lastIndex) append(" ")
                else append(h[y][x])
            }
        }
        result.add(s)
    }
    return result
}
