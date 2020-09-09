package kata.tasks

import kata.tasks.FactorialDecomposition.decomp
import kotlin.test.assertEquals

/**
The aim of the kata is to decompose n! (factorial n) into its prime factors.

Examples:

n = 12; decomp(12) -> "2^10 * 3^5 * 5^2 * 7 * 11"
since 12! is divisible by 2 ten times, by 3 five times, by 5 two times and by 7 and 11 only once.

n = 22; decomp(22) -> "2^19 * 3^9 * 5^4 * 7^3 * 11^2 * 13 * 17 * 19"

n = 25; decomp(25) -> 2^22 * 3^10 * 5^6 * 7^3 * 11^2 * 13 * 17 * 19 * 23
Prime numbers should be in increasing order. When the exponent of a prime is 1 don't put the exponent.
 */
object FactorialDecomposition {
    fun decomp(m: Int): String {
        var result = ""
        val factors = mutableMapOf<Long, Long>()
        (1..m).forEach { currDigit ->
            val currFactor = currDigit.toLong().factorize()
            currFactor.associateWith { f -> currFactor.count { it == f }.toLong() }
                    .forEach {
                        if (it.key in factors.keys) factors[it.key] = factors[it.key]!!.plus(it.value)
                        else factors[it.key] = it.value
                    }
        }
        factors.forEach {
            result += if (it.value == 1L) "${it.key} * "
            else "${it.key}^${it.value} * "
        }
        return result.substringBeforeLast(" * ")
    }


    private fun Long.factorize(): List<Long> {
        var number = this
        val result = mutableListOf<Long>()
        var i = 2L
        while (i <= number) {
            if (number % i == 0L) {
                result.add(i)
                number /= i
                i--
            }
            i++
        }
        return result
    }
}

fun main() {
    testing(17, "2^15 * 3^6 * 5^3 * 7^2 * 11 * 13 * 17")
    testing(5, "2^3 * 3 * 5")
    testing(22, "2^19 * 3^9 * 5^4 * 7^3 * 11^2 * 13 * 17 * 19")
    testing(14, "2^11 * 3^5 * 5^2 * 7^2 * 11 * 13")
    testing(25, "2^22 * 3^10 * 5^6 * 7^3 * 11^2 * 13 * 17 * 19 * 23")
}

private fun testing(n: Int, expect: String) {
    val actual = decomp(n)
    assertEquals(expect, actual)
}