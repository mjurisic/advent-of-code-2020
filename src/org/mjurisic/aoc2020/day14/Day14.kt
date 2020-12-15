package org.mjurisic.aoc2020.day14

import java.io.File
import java.math.BigInteger

class Day14 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {

            var mask = ""
            val mem = HashMap<String, BigInteger>()

            File(ClassLoader.getSystemResource("resources/input14.txt").file).forEachLine {
                if (it.startsWith("mask")) {
                    mask = it.substring(7)
                } else {
                    val groups = Regex("mem\\[(\\d+)] = (\\d+)").find(it)!!.groups
                    val address = groups[1]!!.value
                    val value = BigInteger(groups[2]!!.value)

                    writeValues(mem, address, mask, value)
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

        private fun writeValues(
            mem: java.util.HashMap<String, BigInteger>,
            address: String,
            bitmask: String,
            value: BigInteger
        ) {
            val applyBitmask = applyBitmask(bitmask, BigInteger(address))
            val explodeBitmask = explodeBitmask(applyBitmask, 0, listOf(applyBitmask.replace('X', '0')))
            explodeBitmask.forEach{
                mem[it] = value
            }
        }

        private fun explodeBitmask(bitmask: String, start: Int, input: List<String>): List<String> {
            val exploded = ArrayList<String>()

            exploded.addAll(input)
            if (start == bitmask.length) {
                return exploded
            }

            if (bitmask[start] == 'X') {
                for (i in 0 until exploded.size) {
                    val replaced = exploded[i].replaceRange(start, start + 1, "1")
                    exploded.add(replaced)
                }
            }
            return explodeBitmask(bitmask, start + 1, exploded)

        }

        private fun applyBitmask(bitmask: String, address: BigInteger): String {
            var result = ""
            val reversedAddress = address.toString(2).reversed()
            bitmask.reversed().forEachIndexed { i, c ->
                var charValue = '0'
                if (i < reversedAddress.length) {
                    charValue = reversedAddress[i]
                }
                when (c) {
                    '1' -> result += '1'
                    '0' -> result += charValue
                    'X' -> result += 'X'
                }
            }
            return result.reversed()
        }

        private fun calculateValue(mask: String, value: BigInteger): BigInteger {
            var result = ""
            val stringValue = value.toString(2).reversed()
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



