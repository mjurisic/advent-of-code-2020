package org.mjurisic.aoc2020.day6

import java.io.File

class Day6 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                var buffer: String = ""
                var result = 0
                File(ClassLoader.getSystemResource("resources/input6.txt").file).forEachLine {
                    if (it.length > 0) {
                        buffer = "$buffer$it "
                    } else {
                        result += parseGroup(buffer)
                        buffer = ""
                    }
                }

                result += parseGroup(buffer)

                println(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun parseGroup(input: String):Int {
            val chars = HashSet<Char>()
            input.filter { char -> char != ' ' }.iterator().forEach { char -> chars.add(char) }
            return chars.size
        }

    }
}



