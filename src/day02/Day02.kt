package day02

import readInput

fun main() {
    fun part1(input: List<String>): Int = input.calculatePart1

    fun part2(input: List<String>): Int = input.calculatePart2

    val testInput = readInput(name = "Day02_test", "day02")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput(name = "Day02", "day02")
    println("=== Part 1 ===")
    println(part1(input))
    println("=== Part 2 ===")
    println(part2(input))
}

private val List<String>.calculatePart1 : Int get() =
    cleanPart1Input.
    sumOf { round ->
        val (opponent, you) = round.split(' ')
        calculatePart1Round(opponent.first(), you.first())
    }

private val List<String>.cleanPart1Input: List<String> get() =  map {
    it
            .replace('X', 'A')
            .replace('Y', 'B')
            .replace('Z', 'C')
}

private fun calculatePart1Round(opponent: Char, you: Char): Int {
    val result = when {
        opponent == you -> 3
        opponent == 'A' && you == 'C' -> 0
        opponent == 'A' && you == 'B' -> 6
        opponent == 'B' && you == 'C' -> 6
        opponent == 'B' && you == 'A' -> 0
        opponent == 'C' && you == 'A' -> 6
        opponent == 'C' && you == 'B' -> 0
        else -> 0
    }
    return result + pointMap[you]!!
}

private val List<String>.calculatePart2 : Int get() =
    sumOf { round ->
        val (opponent, result) = round.split(' ')
        calculatePart2Round(opponent.first(), result.first())
    }

private fun calculatePart2Round(opponent: Char, result: Char): Int {
    val you = when {
        opponent == 'A' && result == 'X' -> 'C'
        opponent == 'A' && result == 'Y' -> 'A'
        opponent == 'A' && result == 'Z' -> 'B'
        opponent == 'B' && result == 'X' -> 'A'
        opponent == 'B' && result == 'Y' -> 'B'
        opponent == 'B' && result == 'Z' -> 'C'
        opponent == 'C' && result == 'X' -> 'B'
        opponent == 'C' && result == 'Y' -> 'C'
        opponent == 'C' && result == 'Z' -> 'A'
        else -> 0
    }
    return pointMap[you]!! + resultMap[result]!!
}


private val pointMap = mapOf('A' to 1, 'B' to 2, 'C' to 3)
private val resultMap = mapOf('X' to 0, 'Y' to 3, 'Z' to 6)