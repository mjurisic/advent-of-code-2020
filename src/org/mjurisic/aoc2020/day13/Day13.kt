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


            //part 2, stolen from https://github.com/p88h/aoc2020/blob/main/src/main/kotlin/day13.kt
            val ex1 = "13,x,x,41,x,x,x,37,x,x,x,x,x,419,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,23,x,x,x,x,x,29,x,421,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17"
            val sched = ex1.split(',').mapIndexed { p, v -> v to p }
                .filter { it.first != "x" }.map { it.first.toLong() to it.second }.toList()
            val start = 1002394L


            val prod = sched.map { it.first }.reduce { a, b -> a * b }
            println(sched.map {
                val rem = (it.first - it.second) % it.first
                val pp = prod / it.first
                ( rem * inv(pp, it.first) * pp )
            }.sum() % prod)




        } catch (e: Exception) {
            e.printStackTrace()
        }

        internal fun inv(a0: Long, m0: Long): Long {
            var m = m0; var a = a0; var x0 = 0L; var x1 = 1L
            while (a > 1) {
                val q = a / m;
                val t0 = m; m = a % m; a = t0
                val t1 = x0; x0 = x1 - q * x0; x1 = t1
            }
            return if (x1 < 0) x1 + m0 else x1
        }


    }
}




