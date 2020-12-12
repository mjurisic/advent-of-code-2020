package org.mjurisic.aoc2020.day12

import java.io.File
import kotlin.math.abs

class Day12 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {

            val ship = Ship(0, 0, 0)
            File(ClassLoader.getSystemResource("resources/input12.txt").file).forEachLine {
                val command = it.first()
                val value = it.substring(1, it.length).toInt()
                moveShipInDirection(command, ship, value)
                if (command == 'F') {
                    moveShipForward(ship, value)
                } else if (command == 'R' || command == 'L') {
                    turnShip(command, ship, value)
                }
//                println("$command, *$value*")
//                println(ship)
            }


            val manhattan = abs(ship.x) + abs(ship.y)
            println(manhattan)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        private fun moveShipForward(ship: Ship, value: Int) {
            when (ship.direction) {
                0 -> moveShipInDirection('E', ship, value)
                90 -> moveShipInDirection('S', ship, value)
                180 -> moveShipInDirection('W', ship, value)
                270 -> moveShipInDirection('N', ship, value)
            }
        }

        private fun turnShip(command: Char, ship: Ship, value: Int) {
            when(command) {
                'R' -> ship.direction += value
                'L' -> ship.direction -= value
            }
            if (ship.direction >= 360) {
                ship.direction = ship.direction - 360
            }
            if (ship.direction < 0) {
                ship.direction = 360 + ship.direction
            }
        }

        private fun moveShipInDirection(dir: Char, ship: Ship, value: Int) {
            when (dir) {
                'N' -> ship.y -= value
                'S' -> ship.y += value
                'E' -> ship.x += value
                'W' -> ship.x -= value
            }
        }

    }
}

class Ship(var x:Int, var y:Int, var direction:Int) {
    override fun toString(): String {
        return "Ship(x=$x, y=$y, direction='$direction')"
    }
}


