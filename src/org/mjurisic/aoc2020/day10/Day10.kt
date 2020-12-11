package org.mjurisic.aoc2020.day10

import java.io.File

class Day10 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {
            val numbers = ArrayList<Int>()


            File(ClassLoader.getSystemResource("resources/input10.txt").file).forEachLine {
                numbers.add(it.toInt())
            }
            numbers.add(0)
            numbers.add(numbers.max()!! + 3)
            numbers.sort()
            var diff1 = 0
            var diff3 = 0

            for (i in 1 until numbers.size) {
                if (numbers[i] - numbers[i - 1] == 1) {
                    diff1++
                }
                if (numbers[i] - numbers[i - 1] == 3) {
                    diff3++
                }
            }
            println("$diff1 $diff3, ${diff1 * diff3}")

            //part 2
            val f = LongArray(numbers.size) { 0L }
            f[numbers.size - 1] = 1
            for (i in numbers.size - 2 downTo 0) {
                f[i] = f[i + 1]
                if (i + 3 < numbers.size && numbers[i + 3] <= numbers[i] + 3) {
                    f[i] += f[i + 3]
                }
                if (i + 2 < numbers.size && numbers[i + 2] <= numbers[i] + 3) {
                    f[i] += f[i + 2]
                }
            }
            println(f[0])



        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}

