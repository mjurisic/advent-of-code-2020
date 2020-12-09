package org.mjurisic.aoc2020.day9

import java.io.File

class Day9 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {
            val numbers = ArrayList<Long>()


            File(ClassLoader.getSystemResource("resources/input9.txt").file).forEachLine {
                numbers.add(it.toLong())
            }

            val foundNumber = findInvalidNumber(numbers, 0, 25)
            println(foundNumber)
            findRange(numbers, foundNumber, 0, 1)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        private fun findRange(numbers: java.util.ArrayList<Long>, foundNumber: Long, start: Int, sampleSize: Int) {
            var sum: Long = 0
            for (i in start..start + sampleSize) {
                sum += numbers[i]
            }

            when {
                sum < foundNumber -> {
                    findRange(numbers, foundNumber, start, sampleSize + 1)
                }
                sum == foundNumber -> {
                    println("Found range: $start, $sampleSize")
                    val subList = numbers.subList(start, start + sampleSize)
                    val result = subList.min()!! + subList.max()!!
                    println(result)
                }
                sum > foundNumber -> {
                    findRange(numbers, foundNumber, start + 1, 1)
                }
            }
        }

        private fun findInvalidNumber(numbers: java.util.ArrayList<Long>, start: Int, size: Int): Long {
            val wanted = numbers[start + size]

            var found = false
            for (i in start..start + size) {
                for (j in start + 1 until start + size) {
                    if (numbers[i] + numbers[j] == wanted) {
                        found = true
                    }
                }
            }
            if (!found) {
                return wanted
            }
            return findInvalidNumber(numbers, start + 1, size)
        }
    }
}

