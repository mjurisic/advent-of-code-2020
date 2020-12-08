package org.mjurisic.aoc2020.day8

import java.io.File

class Day8 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {
            val commands = ArrayList<Command>()
            var acc = 0
            var i = 0

            File(ClassLoader.getSystemResource("resources/input8.txt").file).forEachLine {
                val command = it.substring(0, it.indexOf(' '))
                var value = it.substring(it.indexOf(' ') + 1)
                if (value.contains('+')) {
                    value = value.substring(1)
                }
                commands.add(Command(command, value.toInt()))
            }

            while(true) {
                val command = commands[i]
                if (command.isDone) {
                    break
                }
                when (command.command) {
                    "nop" -> i++
                    "acc" -> {
                        acc += command.value
                        i++
                    }
                    "jmp" -> i += command.value
                }
                command.isDone = true
            }

            println(acc)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

class Command(var command: String, var value: Int) {
    var isDone = false
    override fun toString(): String {
        return "Command(command='$command', value=$value)"
    }
}




