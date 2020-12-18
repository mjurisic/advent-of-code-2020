package org.mjurisic.aoc2020.day16;

import java.io.File

class Day16 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {
            val ranges = ArrayList<IntRange>()
            var invalidSum = 0
            File(ClassLoader.getSystemResource("resources/input16.txt").file).forEachLine {
                if (it.contains("or")) {
                    println(it)
                    val split =
                        it.substring(it.indexOf(":") + 1, it.indexOf("or")).trim().split("-").map { it1 -> it1.toInt() }
                    val split2 = it.substring(it.lastIndexOf("or") + 3).split("-").map { it1 -> it1.toInt() }

//                    val split2 = it.substring(it.indexOf(":") + 1, it.indexOf("or")).trim().split("-").map { it1 -> it1.toInt() }
                    ranges.add(IntRange(split[0], split[1]))
                    ranges.add(IntRange(split2[0], split2[1]))
                } else {

                    it.split(",").map { it.toInt() }
                        .forEach { that -> if (ranges.none { it.contains(that) }) invalidSum += that }
                }
            }

            println(invalidSum)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
