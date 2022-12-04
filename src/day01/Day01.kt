package day01

import readInput

fun main() {
    fun part1(input: List<String>): Int = sortedCalories(input).first()
    fun part2(input: List<String>): Int = sortedCalories(input).take(3).sum()

    val testInput = readInput(name = "Day01_test", "day01")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput(name = "Day01", "day01")
    println("=== Part 1 ===")
    println(part1(input))
    println("=== Part 2 ===")
    println(part2(input))
}

fun sortedCalories(input: List<String>): List<Int> {
    var curCalories = 0
    var elfs: MutableList<Int> = mutableListOf()

    input
            .forEachIndexed { index, row ->
                when {
                    index == input.size - 1 -> {
                        elfs = elfs.plus(curCalories + row.toInt()).toMutableList()
                        curCalories = 0
                    }

                    row.isEmpty() -> {
                        elfs = elfs.plus(curCalories).toMutableList()
                        curCalories = 0
                    }

                    else -> curCalories += row.toInt()
                }
            }


    return elfs.sortedByDescending { it }
}
