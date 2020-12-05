package org.mjurisic.aoc2020.day2

import java.awt.Point
import java.io.File

class Day3 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                val matrix = ArrayList<String>()
                File(ClassLoader.getSystemResource("resources/input3.txt").file).forEachLine {
                    matrix.add(it)
                }

                val width = matrix[0].length

                val pos = Point(0, 0)
                var trees = 0

                while (pos.y < matrix.size) {
                    println("testing position ${pos.x} ${pos.y}, char at pos=${matrix[pos.y][pos.x]}")
                    if ('#' == matrix[pos.y][pos.x]) {
                        println("BOOM, a tree")
                        trees++
                    }
                    pos.x = pos.x + 3
                    pos.y = pos.y + 1
                    if (pos.x >= width) {
                        pos.x = pos.x - width
                    }
                }

                println(trees)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

