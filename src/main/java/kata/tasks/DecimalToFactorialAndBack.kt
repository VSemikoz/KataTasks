package kata.tasks

import kotlin.test.assertEquals


/**
Coding decimal numbers with factorials is a way of writing out numbers in
a base system that depends on factorials, rather than powers of numbers.

In this system, the last digit is always 0 and is in base 0!. The digit before
that is either 0 or 1 and is in base 1!. The digit before that is either 0, 1, or 2 and
is in base 2!, etc. More generally, the nth-to-last digit is always 0, 1, 2, ..., n and is in base n!.

The decimal number 463 is encoded as "341010", because:

463 = 3×5! + 4×4! + 1×3! + 0×2! + 1×1! + 0×0!

If we are limited to digits 0..9, the biggest number we can encode is 10!-1 (= 3628799). So we extend
0..9 with letters A..Z. With these 36 digits we can now encode numbers up to 36!-1 (= 3.72 × 1041)
 */
object Dec2Fact {
    fun dec2FactString(n: Long): String {
        var result = ""
        var curr = n
        var index = getMaxFact(n)
        while (index >= 0) {
            val currFact = index.factorial()
            result += getSymbolFromCode(curr / currFact)
            curr %= currFact
            index--
        }
        return result
    }

    fun factString2Dec(str: String): Long {
        var result = 0L
        var currFact = 1L
        var index = 0L
        for (i in str.reversed()) {
            result += currFact * getCodeFromSymbol(i)
            index++
            currFact *= index
        }
        return result
    }

    fun getMaxFact(number: Long): Long {
        var index = 0L
        while (true) {
            val maxFact = index.factorial()
            if (number < maxFact) return --index
            index++
        }
    }

    fun Long.factorial(): Long {
        var result = 1L
        for (i in 2..this) {
            result *= i
        }
        return result
    }

    fun getSymbolFromCode(code: Long): Char {
        return if (code in 0..9) ('0'.toInt() + code).toChar()
        else (code - 10 + 'A'.toInt()).toChar()
    }

    fun getCodeFromSymbol(symb: Char): Long {
        val digCode = symb.toInt() - '0'.toInt()
        return if (digCode in 0..9) digCode.toLong()
        else symb.toLong() + 10 - 'A'.toLong()
    }
}

fun main() {
    testing1(36288000L, "A0000000000")
    testing1(2982L, "4041000")
    testing1(463L, "341010")

    testing2("341010", 463L)
    testing2("4042100", 2990L)
    testing2("27A0533231100", 1273928000L)
}

private fun testing1(nb: Long, expect: String) {
    val actual: String = Dec2Fact.dec2FactString(nb)
    assertEquals(expect, actual)
}

private fun testing2(str: String, expect: Long) {
    val actual: Long = Dec2Fact.factString2Dec(str)
    assertEquals(expect, actual)
}