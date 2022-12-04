package day04

import readInput

fun main() {
    fun part1(input: List<String>): Int = calculatePart1(input)

    fun part2(input: List<String>): Int = calculatePart2(input)

    val testInput = readInput(name = "Day04_test", "day04")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput(name = "Day04", "day04")
    println("=== Part 1 ===")
    println(part1(input))
    println("=== Part 2 ===")
    println(part2(input))
}

private fun calculatePart1(input: List<String>): Int =
        input
                .map { it.split(',') }
                .sumOf { elfPair ->
                    val elf1Sections = elfPair.first().split('-').let { it.first().toInt() to it[1].toInt() }
                    val elf2Sections = elfPair[1].split('-').let { it.first().toInt() to it[1].toInt() }

                    when {
                        elf1Sections.first >= elf2Sections.first && elf1Sections.second <= elf2Sections.second -> 1
                        elf2Sections.first >= elf1Sections.first && elf2Sections.second <= elf1Sections.second -> 1
                        else -> 0
                    }.toInt()
                }

private fun calculatePart2(input: List<String>): Int =
        input
                .map { it.split(',') }
                .sumOf { elfPair ->
                    val elf1Sections = elfPair.first().split('-').let { it.first().toInt() to it[1].toInt() }
                    val elf2Sections = elfPair[1].split('-').let { it.first().toInt() to it[1].toInt() }

                    when {
                        elf2Sections.first > elf1Sections.second -> 0
                        elf1Sections.first > elf2Sections.second -> 0
                        elf1Sections.second < elf1Sections.first -> 0
                        elf2Sections.second < elf2Sections.first -> 0
                        else -> 1
                    }.toInt()
                }
