package org.mjurisic.aoc2020.day14

import java.io.File
import java.math.BigInteger

class Day14 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {

            var mask = ""
            var mem = HashMap<String, BigInteger>()

            File(ClassLoader.getSystemResource("resources/input14.txt").file).forEachLine {
                if (it.startsWith("mask")) {
                    mask = it.substring(7)
                } else {
                    val groups = Regex("mem\\[(\\d+)\\] = (\\d+)").find(it)!!.groups
                    var address = groups[1]!!.value
                    var value = BigInteger(groups[2]!!.value)

                    mem[address] = calculateValue(mask, value)


                }

            }

            var sum = BigInteger("0")
            mem.values.forEach {
                sum = sum.add(it)
            }

            println(sum)


        } catch (e: Exception) {
            e.printStackTrace()
        }

        private fun calculateValue(mask: String, value: BigInteger): BigInteger {
            var result = ""
            var stringValue = value.toString(2).reversed()
            mask.reversed().forEachIndexed { i, c ->
                var charValue = '0'
                if (i < stringValue.length) {
                    charValue = stringValue[i]
                }
                when (c) {
                    '0' -> result += '0'
                    '1' -> result += '1'
                    'X' -> result += charValue
                }
            }
            return BigInteger(result.reversed(), 2)
        }


    }
}



