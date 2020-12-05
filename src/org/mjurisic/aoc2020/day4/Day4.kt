package org.mjurisic.aoc2020.day4

import java.io.File
import java.util.function.Consumer

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
                && isValidBirthYear() && isValidIssueYear() && isValidExpirationYear() && isValidHeight() && isValidHairColor()
                && isValidEyeColor() && isValidPid()
    }

    private fun isValidBirthYear(): Boolean {
        return byr!!.toInt() in 1920..2020
    }

    private fun isValidIssueYear(): Boolean {
        return iyr!!.toInt() in 2010..2020 && iyr!! < eyr!!
    }

    private fun isValidExpirationYear(): Boolean {
        return eyr!!.toInt() in 2020..2030;
    }

    private fun isValidHeight(): Boolean {
        hgt?.let {
            if (it.endsWith("cm")) {
                return it.substring(0, it.length - 2).toInt() in 150..193
            } else if (it.endsWith("in")) {
                return it.substring(0, it.length - 2).toInt() in 59..76
            }
        }
        return false
    }

    private fun isValidHairColor(): Boolean {
        hcl?.let {
            return Regex("^#(?:[0-9a-f]){6}").matches(it)
        }
        return false
    }

    private fun isValidEyeColor(): Boolean {
        ecl?.let {
            return Regex("amb|blu|brn|gry|grn|hzl|oth").matches(it)
        }
        return false
    }

    private fun isValidPid(): Boolean {
        pid?.let {
            return Regex("\\d{9}").matches(it)
        }
        return false
    }

    override fun toString(): String {
        return "Passport(input='$input', byr=$byr, iyr=$iyr, eyr=$eyr, hgt=$hgt, hcl=$hcl, ecl=$ecl, pid=$pid, cid=$cid)"
    }
}

