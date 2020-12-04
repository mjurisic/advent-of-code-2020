package org.mjurisic.aoc2020.day2

import java.io.File
import java.util.*

class Day2 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                var entries = ArrayList<Password>()
                File("/home/jurisima/playground/aoc2020/src/org/mjurisic/aoc2020/day2/input2.txt").forEachLine {
                    val min = it.substring(0, it.indexOf("-"))
                    val max = it.substring(it.indexOf("-") + 1, it.indexOf(" "))
                    val letter = it.substring(it.indexOf(" ") + 1, it.indexOf(":"))
                    val pw = it.substring(it.indexOf(": ") + 2)
                    entries.add(
                        Password(
                            Integer.valueOf(min),
                            Integer.valueOf(max),
                            letter.single(),
                            pw
                        )
                    )
                }

                println(entries.filter { it.isValidV2() }.size)


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

class Password(var min: Int, var max: Int, var letter: Char, var password: String) {

    override fun toString(): String {
        return "Password(min=$min, max=$max, letter=$letter, password='$password')"
    }

    fun isValid():Boolean {
        val occurences = password.filter { it == letter }.length
        return occurences in min..max
    }

    fun isValidV2():Boolean {
        return (password[min-1] == letter).xor(password[max-1] == letter)
    }
}
