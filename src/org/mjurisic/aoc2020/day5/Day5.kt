package org.mjurisic.aoc2020.day5

import java.io.File
import java.util.function.Consumer

class Day5 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                val seats = ArrayList<Seat>()
                File(ClassLoader.getSystemResource("resources/input5.txt").file).forEachLine {
                    seats.add(calculateSeat(it))
                }

                seats.sortBy { it.getId() }

                var previous = seats[0].getId()
                seats.forEach(Consumer {
                    if (it.getId() != previous + 1 && it.getId() != previous) {
                        println(previous+1)
                    }
                    previous = it.getId()
                })

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun calculateSeat(it: String): Seat {
            var row = ""
            for (i in 0..6) {
                row += if (it[i] == 'F') "0" else "1"
            }
            var col = ""
            for (i in 7..9) {
                col += if (it[i] == 'L') "0" else "1"
            }

            return Seat(it, Integer.parseInt(row, 2), Integer.parseInt(col, 2))
        }
    }
}

class Seat(var input: String, var row: Int, var column: Int) {
    override fun toString(): String {
        return "Seat(id=${getId()}input='$input', row=$row, column=$column)"
    }

    fun getId(): Int {
        return row * 8 + column
    }
}


