package org.mjurisic.aoc2020.day6

import java.io.File
import java.util.function.Consumer

class Day7 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = try {
            val nodes = HashMap<String, Node>()
            val vertices = ArrayList<Vertex>()

            File(ClassLoader.getSystemResource("resources/input7.txt").file).forEachLine {
                val bagColor = it.substring(0, it.indexOf("bags") - 1)

                val bagContent = it.substring(it.indexOf("contain") + 8, it.length - 1)
                val edges = ArrayList<Vertex>()
                if (bagContent != "no other bags") {
                    bagContent.split(",").forEach(Consumer {
                        val bag = it.trim()
                        val strength = bag.substring(0, bag.indexOf(" "))
                        val color = bag.substring(bag.indexOf(" ") + 1, bag.lastIndexOf(" "))
                        val vertex = Vertex(bagColor, color, strength.toInt())
                        edges.add(vertex)
                        vertices.add(vertex)
                    })
                }
                nodes.put(bagColor, Node(bagColor, edges))
            }
//          part 1
//          println(nodes.filter { isContained(it.value, vertices, nodes, "shiny gold") }.size)
            val sourceNode = nodes["shiny gold"]
            println(countBags(sourceNode!!, nodes, vertices))

        } catch (e: Exception) {
            e.printStackTrace()
        }

        // part 1
        private fun isContained(
            bag: Node,
            vertices: java.util.ArrayList<Vertex>,
            nodes: HashMap<String, Node>,
            color: String
        ): Boolean {
            if (bag.color == color) {
                return false
            }

            if (contains(bag, vertices, nodes, color)) {
                return true
            }

            return false
        }

        private fun contains(
            bag: Node,
            vertices: java.util.ArrayList<Vertex>,
            nodes: HashMap<String, Node>,
            color: String
        ): Boolean {
            if (bag.color == color) {
                return false
            }
            if (vertices.any { it.source == bag.color && it.destination == color }) {
                return true
            }

            if (vertices.filter { it.source == bag.color }.map { nodes[it.destination] }.any {
                    contains(
                        it!!,
                        vertices,
                        nodes,
                        color
                    )
                }) {
                return true
            }
            return false
        }

        // part 2
        private fun countBags(
            sourceNode: Node,
            nodes: java.util.HashMap<String, Node>,
            vertices: java.util.ArrayList<Vertex>
        ): Int {
            var count = 0
            sourceNode.vertices.forEach {
                count += it.strength
                val childNode = nodes[it.destination]!!
                count += it.strength * countBags(childNode, nodes, vertices)
            }

            return count
        }

    }
}

class Node(var color: String, var vertices: List<Vertex>) {
    override fun toString(): String {
        return "Node(color='$color', vertices=$vertices)"
    }
}

class Vertex(var source: String, var destination: String, var strength: Int) {
    override fun toString(): String {
        return "Vertex(source='$source', destination='$destination', strength=$strength)"
    }
}



