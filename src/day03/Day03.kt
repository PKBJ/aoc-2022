package day03

import readInput

fun main() {
    fun part1(input: List<String>): Int = calculatePart1(input)

    fun part2(input: List<String>): Int = calculatePart2(input)

    val testInput = readInput(name = "Day03_test", "day03")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput(name = "Day03", "day03")
    println("=== Part 1 ===")
    println(part1(input))
    println("=== Part 2 ===")
    println(part2(input))
}

private fun calculatePart1(input: List<String>): Int =
        input
                .map { line -> line.chunked(line.length / 2) }
                .sumOf {
                    val compartment1 = it.first()
                    val compartment2 = it[1]

                    val sharedItem = compartment1.first { item -> compartment2.contains(item) }
                    resultMap[sharedItem]!!
                }

private fun calculatePart2(input: List<String>): Int =
        input
                .chunked(3)
                .sumOf {
                    val elf1 = it.first()
                    val elf2 = it[1]
                    val elf3 = it.last()

                    val sharedItemsElf1Elf2 = elf1.filter { item -> elf2.contains(item) }
                    val badge = sharedItemsElf1Elf2.first { item -> elf3.contains(item) }

                    resultMap[badge]!!
                }

private val resultMap = ('a'..'z').mapIndexed { index, c -> c to index + 1 }.toMap() +
        ('A'..'Z').mapIndexed { index, c -> c to index + 27 }.toMap()
