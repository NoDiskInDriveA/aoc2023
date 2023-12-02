import kotlin.test.Test
import kotlin.test.assertEquals

class Day01KtTest {
    @Test
    fun part1() {
        val example = part1(Helper.getInput("day01/part1.example.txt"))
        val real = part1(Helper.getInput("day01/input.txt"))

        assertEquals(142, example)
        println("Part1: $real")
    }

    @Test
    fun part2() {
        val example = part2(Helper.getInput("day01/part2.example.txt"))
        val real = part2(Helper.getInput("day01/input.txt"))

        assertEquals(281, example)
        println("Part2: $real")
    }
}