package org.mjurisic.aoc2020.day11

import java.io.File

class Day11 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {
            val matrix = ArrayList<String>()

            File(ClassLoader.getSystemResource("resources/input11.txt").file).forEachLine {
                matrix.add(it)
            }

            var transformations = 0
            var old = matrix
            var new = transformV2(matrix)
            while (old != new) {
                old = ArrayList(new)
                new = transformV2(old)
                transformations++
            }
            println(transformations)

            var occupied = 0
            for (s in new) {
                occupied += s.count { it == '#' }
            }
            println("occupied $occupied")


        } catch (e: Exception) {
            e.printStackTrace()
        }

        private fun transform(input: java.util.ArrayList<String>): ArrayList<String> {
            val newMap = ArrayList<String>()
            for (i in 0 until input.size) {
                var row = ""
                for (j in 0 until input[0].length) {
                    val adjacentFields = getAdjacentFields(input, i, j)
                    if (input[i][j] == 'L' && !adjacentFields.any { it == '#' }) {
                        row += '#'
                    } else if (input[i][j] == '#' && adjacentFields.filter { it == '#' }.count() >= 4) {
                        row += 'L'
                    } else {
                        row += input[i][j]
                    }
                }
                newMap.add(row)
            }
            return newMap
        }

        private fun transformV2(input: java.util.ArrayList<String>): ArrayList<String> {
            val newMap = ArrayList<String>()
            for (i in 0 until input.size) {
                var row = ""
                for (j in 0 until input[0].length) {
                    val visibleSeats = getVisibleSeats(input, i, j)
                    if (input[i][j] == 'L' && !visibleSeats.any { it == '#' }) {
                        row += '#'
                    } else if (input[i][j] == '#' && visibleSeats.filter { it == '#' }.count() >= 5) {
                        row += 'L'
                    } else {
                        row += input[i][j]
                    }
                }
                newMap.add(row)
            }
            return newMap
        }

        private fun getVisibleSeats(map: java.util.ArrayList<String>, row: Int, col: Int): ArrayList<Char> {
            val visibleSeats = ArrayList<Char>()
            var found = false
            var i = row
            var j = col
            while (i > 0 && !found) {
                val seat = map[i - 1][j]
                if (seat == '#' || seat == 'L') {
                    found = true
                    visibleSeats.add(seat)
                }
                i--
            }
            found = false
            i = row
            j = col
            while (j > 0 && !found) {
                val seat = map[i][j - 1]
                if (seat == '#' || seat == 'L') {
                    found = true
                    visibleSeats.add(seat)
                }
                j--
            }
            i = row
            j = col
            found = false

            while (i < map.size - 1 && !found) {
                val seat = map[i + 1][j]
                if (seat == '#' || seat == 'L') {
                    found = true
                    visibleSeats.add(seat)
                }
                i++
            }

            i = row
            j = col
            found = false

            while (j < map[0].length - 1 && !found) {
                val seat = map[i][j + 1]
                if (seat == '#' || seat == 'L') {
                    found = true
                    visibleSeats.add(seat)
                }
                j++
            }


            i = row
            j = col
            found = false

            while (i > 0 && j > 0 && !found) {
                val seat = map[i - 1][j - 1]
                if (seat == '#' || seat == 'L') {
                    found = true
                    visibleSeats.add(seat)
                }
                j--
                i--
            }

            i = row
            j = col
            found = false
            while (i > 0 && j < map[0].length - 1 && !found) {
                val seat = map[i - 1][j + 1]
                if (seat == '#' || seat == 'L') {
                    found = true
                    visibleSeats.add(seat)
                }
                i--
                j++
            }

            i = row
            j = col
            found = false

            while (i < map.size - 1 && j < map[0].length - 1 && !found) {
                val seat = map[i + 1][j + 1]
                if (seat == '#' || seat == 'L') {
                    found = true
                    visibleSeats.add(seat)
                }
                i++
                j++
            }

            i = row
            j = col
            found = false
            while (i < map.size - 1 && j > 0 && !found) {
                val seat = map[i + 1][j - 1]
                if (seat == '#' || seat == 'L') {
                    found = true
                    visibleSeats.add(seat)
                }
                i++
                j--
            }
            return visibleSeats
        }

        private fun getAdjacentFields(map: java.util.ArrayList<String>, i: Int, j: Int): ArrayList<Char> {
            val adjacentFields = ArrayList<Char>()
            if (i > 0) {
                adjacentFields.add(map[i - 1][j])
            }
            if (j > 0) {
                adjacentFields.add(map[i][j - 1])
            }
            if (i < map.size - 1) {
                adjacentFields.add(map[i + 1][j])
            }
            if (j < map[0].length - 1) {
                adjacentFields.add(map[i][j + 1])
            }
            if (i > 0 && j > 0) {
                adjacentFields.add(map[i - 1][j - 1])
            }
            if (i > 0 && j < map[0].length - 1) {
                adjacentFields.add(map[i - 1][j + 1])
            }
            if (i < map.size - 1 && j < map[0].length - 1) {
                adjacentFields.add(map[i + 1][j + 1])
            }

            if (i < map.size - 1 && j > 0) {
                adjacentFields.add(map[i + 1][j - 1])
            }
            return adjacentFields
        }


    }


}

