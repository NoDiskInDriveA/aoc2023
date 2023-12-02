package day02

import kotlin.math.max


data class Game(
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

fun List<CubeCount>.maxPower(): Int = reduce { acc, current -> acc.combineToMax(current) }.power()

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
                var (red, green, blue) = Triple(0, 0, 0)

                round
                    .split(',')
                    .map { it.trim() }
                    .forEach { colorstr ->
                        when {
                            colorstr.endsWith("green", true) -> green = colorstr.filter { c -> c.isDigit() }.toInt()
                            colorstr.endsWith("red", true) -> red = colorstr.filter { c -> c.isDigit() }.toInt()
                            colorstr.endsWith("blue", true) -> blue = colorstr.filter { c -> c.isDigit() }.toInt()
                        }
                    }

                CubeCount(red = red, blue = blue, green = green)
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
