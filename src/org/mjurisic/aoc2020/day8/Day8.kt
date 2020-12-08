package org.mjurisic.aoc2020.day8

import java.io.File

class Day8 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {
            val commands = ArrayList<Command>()

            var isDone = false
            var changedIndex = 0
            var previousChangedIndex = -1
            var acc:Int

            File(ClassLoader.getSystemResource("resources/input8.txt").file).forEachLine {
                val command = it.substring(0, it.indexOf(' '))
                var value = it.substring(it.indexOf(' ') + 1)
                if (value.contains('+')) {
                    value = value.substring(1)
                }
                commands.add(Command(command, value.toInt()))
            }

            do {
                acc = 0
                var i = 0

                while (true) {
                    if (i == commands.size -1) {
                        isDone = true
                        break
                    }
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

                    if (command.isDone && changedIndex == previousChangedIndex) {
                        break
                    }
                    command.isDone = true
                    previousChangedIndex = changedIndex
                }

                // reset command state between loops
                commands.forEach{
                    it.isDone = false
                    if (it.originalCommand != null) {
                        it.command = it.originalCommand!!
                    }
                }

                // mutate commands to break the loop
                var found = false
                while(!found && changedIndex < commands.size) {
                    val command = commands[changedIndex]
                    if (command.command == "jmp") {
                        found = true
                        command.command = "nop"
                        command.originalCommand = "jmp"
                    } else if (command.command == "nop") {
                        found = true
                        command.command = "jmp"
                        command.originalCommand = "nop"
                    }
                    changedIndex++

                }

            } while (!isDone && i < commands.size )



            println("acc:$acc")

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

class Command(var command: String, var value: Int) {
    var isDone = false
    var originalCommand:String? = null
    override fun toString(): String {
        return "Command(command='$command', value=$value)"
    }
}




