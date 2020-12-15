package org.mjurisic.aoc2020.day15

import java.util.*
import kotlin.collections.HashMap

class Day15 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {
            val input = "7,14,0,17,11,1,2"
            val numbers = LinkedList(input.split(",").map { it.toInt() })
            val occurrences = HashMap<Int, Tuple>()
            numbers.forEachIndexed { i, number -> occurrences[number] = Tuple(i, null) }

            var i = numbers.size
            while (i < 30000000) {

                val last = numbers.last()

                if (occurrences.containsKey(last) && occurrences[last]!!.beforeLast != null) {
                    val diff = occurrences[last]!!.last!! - occurrences[last]!!.beforeLast!!
                    numbers.add(diff)
                } else {
                    numbers.add(0)
                }

                if (occurrences.containsKey(numbers.last())) {
                    occurrences[numbers.last()]?.beforeLast = occurrences[numbers.last()]?.last
                    occurrences[numbers.last()]?.last = i
                } else {
                    occurrences[numbers.last()] = Tuple(i, null)
                }


                i++
            }

            println(i)
            println(numbers.last())


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
class Tuple(var last:Int?, var beforeLast:Int?)




