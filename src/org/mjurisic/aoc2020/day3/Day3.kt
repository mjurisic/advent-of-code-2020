package org.mjurisic.aoc2020.day3

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
                var treesResult:Long = 1

                treesResult *= findTrees(matrix, width, 1,1)
                treesResult *= findTrees(matrix, width, 3,1)
                treesResult *= findTrees(matrix, width, 5,1)
                treesResult *= findTrees(matrix, width, 7,1)
                treesResult *= findTrees(matrix, width, 1,2)

                println(treesResult)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun findTrees(matrix: ArrayList<String>, width: Int, deltaX: Int, deltaY: Int): Int {
            val position = Point(0, 0)
            var trees = 0
            while (position.y < matrix.size) {
                if ('#' == matrix[position.y][position.x]) {
                    trees++
                }
                calculateMovement(position, deltaX, deltaY)
                if (position.x >= width) {
                    position.x = position.x - width
                }
            }

            return trees
        }

        private fun calculateMovement(pos: Point, deltaX: Int, deltaY: Int) {
            pos.x = pos.x + deltaX
            pos.y = pos.y + deltaY
        }
    }
}

