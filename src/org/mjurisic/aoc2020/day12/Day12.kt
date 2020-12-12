package org.mjurisic.aoc2020.day12

import java.io.File
import kotlin.math.abs

class Day12 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {

            val ship = Ship(0, 0)
            val waypoint = Waypoint(10, -1)
            File(ClassLoader.getSystemResource("resources/input12.txt").file).forEachLine {
                val command = it.first()
                val value = it.substring(1, it.length).toInt()
                moveWaypointInDirection(command, waypoint, value)
                if (command == 'F') {
                    moveShipForward(ship, waypoint, value)
                } else if (command == 'R' || command == 'L') {
                    rotateWaypoint(command, ship, waypoint, value)
                }
            }


            val manhattan = abs(ship.x) + abs(ship.y)
            println(manhattan)

        } catch (e: Exception) {
            e.printStackTrace()
        }

        private fun moveShipForward(ship: Ship, waypoint: Waypoint, value: Int) {
            val dx = waypoint.x - ship.x
            val dy = waypoint.y - ship.y

            repeat(value) {
                ship.x += dx
                ship.y += dy
            }
            waypoint.x = ship.x + dx
            waypoint.y = ship.y + dy
        }

        private fun rotateWaypoint(direction: Char, ship: Ship, waypoint: Waypoint, value: Int) {
            val dx = waypoint.x - ship.x
            val dy = waypoint.y - ship.y

            if (direction == 'L' && value == 90 || direction == 'R' && value == 270) {
                waypoint.y = ship.y - dx
                waypoint.x = ship.x + dy
            } else if (direction == 'R' && value == 90 || direction == 'L' && value == 270) {
                waypoint.y = ship.y + dx
                waypoint.x = ship.x - dy
            } else if (value == 180) {
                waypoint.y = ship.y - dy
                waypoint.x = ship.x - dx
            }

        }

        private fun moveWaypointInDirection(dir: Char, waypoint: Waypoint, value: Int) {
            when (dir) {
                'N' -> waypoint.y = waypoint.y - value
                'S' -> waypoint.y = waypoint.y + value
                'E' -> waypoint.x = waypoint.x + value
                'W' -> waypoint.x = waypoint.x - value
            }
        }

    }
}

class Ship(var x: Int, var y: Int) {
    override fun toString(): String {
        return "Ship(x=$x, y=$y)"
    }
}

class Waypoint(var x: Int, var y: Int) {
    override fun toString(): String {
        return "Waypoint(x=$x, y=$y)"
    }
}


