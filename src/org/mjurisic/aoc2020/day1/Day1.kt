package org.mjurisic.aoc2020.day1

import java.io.File
import java.util.*

class Day1 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                var entries = ArrayList<Int>()
                File(ClassLoader.getSystemResource("resources/input1-1.txt").file).forEachLine {
                    entries.add(Integer.parseInt(it))
                }

                for (i in (0 until entries.size)) {
                    for (j in (i + 1 until entries.size - 1)) {
                        for (k in (j + 1 until entries.size - 1)) {
                            if (entries[i] +  entries[j] + entries[k] == 2020) {
                                println(entries[i] * entries[j] * entries[k])
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}
