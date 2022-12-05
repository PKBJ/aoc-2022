package day05

import readInput
import java.util.*

fun main() {
    fun part1(input: List<String>): String = input.calculatePart1()

    fun part2(input: List<String>): String = input.calculatePart2()

    val testInput = readInput(name = "Day05_test", "day05")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput(name = "Day05", "day05")
    println("=== Part 1 ===")
    println(part1(input))
    println("=== Part 2 ===")
    println(part2(input))
}

private fun List<String>.parse(): Pair<List<Stack<String>>, List<Move>> {
    val crateInput = takeWhile { !it.contains("[0-9]".toRegex()) }.reversed()
    val nrOfStacks = crateInput.first().chunked(4).size
    val crateStacks = List(nrOfStacks) { Stack<String>() }

    crateInput.forEach {
        val crates = it.chunked(4)
        crates.forEachIndexed { index, s ->
            val crateSign = s.trim().replace("[", "").replace("]", "")
            if (crateSign.contains("[A-Z]".toRegex())) {
                crateStacks[index].push(crateSign)
            }
        }

    }

    val moves = filter { it.contains("move") }
            .map { move ->
                val splitted = move.split(" ").filter { it.contains("[0-9]".toRegex()) }
                Move(nrOfCrates = splitted[0].toInt(), fromIndex = splitted[1].toInt() - 1, toIndex = splitted[2].toInt() - 1)
            }

    return crateStacks to moves
}

data class Move(val nrOfCrates: Int, val fromIndex: Int, val toIndex: Int)

private fun List<String>.calculatePart1(): String =
        parse().let { (stacks, moves) ->
            moves.forEach { move ->
                repeat(move.nrOfCrates) {
                    stacks[move.toIndex].push(stacks[move.fromIndex].pop())
                }
            }
            stacks.joinToString("") { it.pop() }
        }

private fun List<String>.calculatePart2(): String =
        parse().let { (stacks, moves) ->
            moves.forEach { move ->
                val crateStack = Stack<String>()
                repeat(move.nrOfCrates) {
                    crateStack.push(stacks[move.fromIndex].pop())
                }
                repeat(move.nrOfCrates) {
                    stacks[move.toIndex].push(crateStack.pop())
                }
            }
            stacks.joinToString("") { it.pop() }
        }
