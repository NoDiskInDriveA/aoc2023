import kotlin.test.Test
import kotlin.test.assertEquals
import day02.*

class Day02Test {
    @Test
    fun testPart1() {
        val cubeCount = CubeCount(red = 12, green = 13, blue = 14)
        val example = part1(Helper.getInput("day02/example.txt"), cubeCount)
        val real = part1(Helper.getInput("day02/input.txt"), cubeCount)

        assertEquals(8, example)
        assertEquals(3099, real) // refactoring check
        println("Part1: $real")
    }

    @Test
    fun testPart2() {
        val example = part2(Helper.getInput("day02/example.txt"))
        val real = part2(Helper.getInput("day02/input.txt"))

        assertEquals(2286, example)
        assertEquals(72970, real) // refactoring check
        println("Part2: $real")
    }
}