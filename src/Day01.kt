import kotlin.math.max
import kotlin.math.min

private val digits = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)

fun part2(str: List<String>): Int {

    return str
        .sumOf { line: String ->
            line.indices
                .firstNotNullOf { index: Int ->
                    if (line[index].isDigit())
                        line[index].digitToInt()
                    else
                        digits.firstNotNullOfOrNull { it: Map.Entry<String, Int> ->
                            if (line.substring(index, min(index + it.key.length, line.length)) == it.key) it.value else null
                        }
                }
                .times(10)
                .plus(
                    line.indices
                        .reversed()
                        .firstNotNullOf { index: Int ->
                            if (line[index].isDigit())
                                line[index].digitToInt()
                            else
                                digits.firstNotNullOfOrNull { it: Map.Entry<String, Int> ->
                                    if (line.substring(max(index - it.key.length + 1, 0), index + 1) == it.key) it.value else null
                                }
                        }
                )
        }
}

fun part1(str: List<String>): Int {
    return str
        .sumOf { line: String ->
            line
                .first { chr -> chr.isDigit() }
                .digitToInt()
                .times(10)
                .plus(
                    line
                        .last { chr -> chr.isDigit() }
                        .digitToInt()
                )
        }
}
