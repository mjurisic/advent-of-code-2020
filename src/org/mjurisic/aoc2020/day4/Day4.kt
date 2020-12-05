package org.mjurisic.aoc2020.day4

import java.io.File

class Day4 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                val passports = ArrayList<Passport>()
                var buffer: String = ""
                File(ClassLoader.getSystemResource("resources/input4.txt").file).forEachLine {
                    if (it.length > 0) {
                        buffer = "$buffer$it "
                    } else {
                        passports.add(parsePassport(buffer))
                        buffer = ""
                    }
                }

                // add last passport
                passports.add(parsePassport(buffer))


                val filter = passports.filter { it.isValid() }
                println(filter.count())

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun parsePassport(input: String): Passport {
            return Passport(
                input,
                byr = Regex("byr:(\\w+)").find(input)?.groups?.get(1)?.value,
                iyr = Regex("iyr:(\\w+)").find(input)?.groups?.get(1)?.value,
                eyr = Regex("eyr:(\\w+)").find(input)?.groups?.get(1)?.value,
                hgt = Regex("hgt:(\\d+\\w+)").find(input)?.groups?.get(1)?.value,
                hcl = Regex("hcl:(#?\\w+)").find(input)?.groups?.get(1)?.value,
                ecl = Regex("ecl:(#?\\w+)").find(input)?.groups?.get(1)?.value,
                pid = Regex("pid:(#?\\w+)").find(input)?.groups?.get(1)?.value,
                cid = Regex("cid:(\\w+)").find(input)?.groups?.get(1)?.value
            )
        }

    }
}

class Passport(
    var input: String,
    var byr: String?,
    var iyr: String?,
    var eyr: String?,
    var hgt: String?,
    var hcl: String?,
    var ecl: String?,
    var pid: String?,
    var cid: String?
) {

    fun isValid(): Boolean {
        return byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null
    }

    override fun toString(): String {
        return "Passport(input='$input', byr=$byr, iyr=$iyr, eyr=$eyr, hgt=$hgt, hcl=$hcl, ecl=$ecl, pid=$pid, cid=$cid)"
    }
}

