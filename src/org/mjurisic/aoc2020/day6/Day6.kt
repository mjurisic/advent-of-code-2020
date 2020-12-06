package org.mjurisic.aoc2020.day6

import java.io.File

class Day6 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                var buffer: String = ""
                var groupSize = 0
                var result = 0

                File(ClassLoader.getSystemResource("resources/input6.txt").file).forEachLine {
                    if (it.length > 0) {
                        buffer = "$buffer$it "
                        groupSize++
                    } else {
                        result += parseGroup(buffer, groupSize)
                        buffer = ""
                        groupSize = 0
                    }
                }

                result += parseGroup(buffer, groupSize)

                println(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun parseGroup(input: String, groupSize: Int): Int {
            val chars = HashMap<Char, Int>()
            input.filter { char -> char != ' ' }.iterator().forEach { char ->
                if (chars.containsKey(char)) {
                    chars[char] = chars[char]!! + 1
                } else {
                    chars[char] = 1
                }
            }

            return chars.entries.filter { it -> it.value == groupSize }.size
        }

    }
}



