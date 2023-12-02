package day02

import kotlin.math.max

private enum class Color {
    RED,BLUE,GREEN
}

private data class Game(
    val id: Int,
    val rounds: List<CubeCount>
)

data class CubeCount(
    val red: Int = 0,
    val blue: Int = 0,
    val green: Int = 0,
) {
    fun canFitInto(other: CubeCount): Boolean {
        return red <= other.red && green <= other.green && blue <= other.blue
    }

    fun combineToMax(other: CubeCount): CubeCount {
        return CubeCount(
            red = max(red, other.red),
            green = max(green, other.green),
            blue = max(blue, other.blue)
        )
    }

    fun power(): Int {
        return red * green * blue
    }
}

private fun List<CubeCount>.maxPower(): Int = reduce { acc, current -> acc.combineToMax(current) }.power()


private fun gameFactory(str: String): Game {
    return Game(
        id = str
            .substringBefore(':')
            .filter { it.isDigit() }
            .toInt(),
        rounds = str
            .substringAfter(':')
            .split(';')
            .map { round ->
                round
                    .split(',')
                    .map { it.trim() }
                    .associateBy({
                        when {
                            it.endsWith("green", true) -> Color.GREEN
                            it.endsWith("red", true) -> Color.RED
                            it.endsWith("blue", true) -> Color.BLUE
                            else -> throw Exception("Color unparseable")
                        }
                    }, {
                        it.filter { c -> c.isDigit() }.toInt()
                    })
                    .let { colors ->
                        CubeCount(
                            red = colors.getOrDefault(Color.RED, 0),
                            blue = colors.getOrDefault(Color.BLUE, 0),
                            green = colors.getOrDefault(Color.GREEN, 0),
                        )
                    }
            })
}


fun part1(str: List<String>, actualCubeCount: CubeCount): Int {
    return str
        .map { gameFactory(it) }
        .filter { game ->
            game.rounds
                .all { round ->
                    round.canFitInto(actualCubeCount)
                }
        }
        .sumOf { game ->
            game.id
        }
}

fun part2(str: List<String>): Int {
    return str
        .map { gameFactory(it) }
        .sumOf { game ->
            game.rounds.maxPower()
        }
}
