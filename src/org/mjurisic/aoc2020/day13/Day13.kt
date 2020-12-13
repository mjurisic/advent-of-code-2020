package org.mjurisic.aoc2020.day13

import java.io.File
import kotlin.math.abs

class Day13 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {
            val minutes = 1002394
            val s = "13,x,x,41,x,x,x,37,x,x,x,x,x,419,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,23,x,x,x,x,x,29,x,421,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17"
            val numbers = s.split(",").filter { it != "x" }.map { it.toInt() }

            var min = Int.MAX_VALUE
            var index = numbers.size
            for (i in numbers.indices) {
                val result = minutes / numbers[i]
                val diff = (result + 1) * numbers[i] - minutes
                if (diff < min) {
                    min = diff
                    index = i
                }
//                println("${numbers[i]}, $result, $diff")

            }
            println("$min, $index, ${numbers[index] * min}")


        } catch (e: Exception) {
            e.printStackTrace()
        }



    }
}



