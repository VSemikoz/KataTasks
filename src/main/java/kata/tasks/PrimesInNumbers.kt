package kata.tasks

import junit.framework.Assert.assertEquals

/**
Given a positive number n > 1 find the prime factor decomposition of n.
The result will be a string with the following form :

"(p1**n1)(p2**n2)...(pk**nk)"
where a ** b means a to the power of b

with the p(i) in increasing order and n(i) empty if n(i) is 1.

Example: n = 86240 should return "(2**5)(5)(7**2)(11)"
 */
object PrimeDecomp {

    fun factors(l: Int): String {
        l.getPrime().let { list ->
            var result = ""
            list.groupBy { it }.map { it.key to it.value.size }
                    .forEach {
                        result += if (it.second == 1) "(${it.first})"
                        else "(${it.first}**${it.second})"
                    }
            return result
        }
    }

    fun Int.getPrime(): MutableList<Int> {
        var number = this
        val result = mutableListOf<Int>()
        var i = 2
        while (i <= number) {
            if (number % i == 0) {
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
    testing(1024, "(2**10)")
    testing(7775460, "(2**2)(3**3)(5)(7)(11**2)(17)")
}

private fun testing(n: Int, exp: String) {
    val ans = PrimeDecomp.factors(n)
    assertEquals(exp, ans)
}