package org.mjurisic.aoc2020.day13

class Day13 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {
            // part 1
            val minutes = 1002394
            val input =
                "13,x,x,41,x,x,x,37,x,x,x,x,x,419,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,23,x,x,x,x,x,29,x,421,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17"
            val numbers = input.split(",").filter { it != "x" }.map { it.toInt() }

            var min = Int.MAX_VALUE
            var index = numbers.size
            for (i in numbers.indices) {
                val result = minutes / numbers[i]
                val diff = (result + 1) * numbers[i] - minutes
                if (diff < min) {
                    min = diff
                    index = i
                }

            }
            println("$min, $index, ${numbers[index] * min}")


            //part 2
            val ex1 = "67,x,7,59,61"
            val n = ex1.replace('x', '1').split(",").filter { it != "x" }.map { it.toLong() }
            println(n)
            var start: Long = 0
            var found = false
            while (!found) {
                found = true
                if (start % 1000000000L == 0L) {
                    println(start)
                }
                for (i in start until start + n.size) {

                    val index = (i - start).toInt()
                    if (i % n[index] != 0L) {
                        found = false
                        break
//                        println ("$i, ${n[i - start]}")
                    }
                }
                if (found) {
                    break
                } else {
                    start++
                }
            }

            println(start)


        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}




