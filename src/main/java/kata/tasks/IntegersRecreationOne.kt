package kata.tasks

import kotlin.math.sqrt
import kotlin.test.assertEquals

/**
Divisors of 42 are : 1, 2, 3, 6, 7, 14, 21, 42. These divisors squared are: 1, 4, 9, 36, 49, 196, 441, 1764.
The sum of the squared divisors is 2500 which is 50 * 50, a square!

Given two integers m, n (1 <= m <= n) we want to find all integers between m and n whose sum
of squared divisors is itself a square. 42 is such a number.

The result will be an array of arrays or of tuples (in C an array of Pair) or a string,
each subarray having two elements, first the number whose squared divisors is a square and
then the sum of the squared divisors.

#Examples:

list_squared(1, 250) --> [[1, 1], [42, 2500], [246, 84100]]
list_squared(42, 250) --> [[42, 2500], [246, 84100]]
The form of the examples may change according to the language, see Example Tests: for more details.
 */
object SumSquaredDivisors {

    fun listSquared(m: Long, n: Long): String {
        val result = mutableSetOf<MutableList<Long>>()
        for (i in m..n) {
            val squaredSum = getSumOfSquaredDivision(i.getDivisors())
            val squaredSumDoubled = squaredSum.toDouble()
            if (sqrt(squaredSumDoubled) - sqrt(squaredSumDoubled).toInt() == 0.0) {
                result.add(mutableListOf(i, squaredSum))
            }
        }
        return result.toString()
    }

    private fun Long.getDivisors(): Set<Long> {
        val result = mutableSetOf<Long>()
        for (i in 1..sqrt(this.toDouble()).toInt()) {
            val curr = i.toLong()
            if (this % curr == 0L) result.addAll(arrayListOf(curr, this / curr))
        }
        return result
    }

    private fun getSumOfSquaredDivision(set: Set<Long>) = set.map { it * it }.sum()
}

fun main() {
    assertEquals("[[1, 1], [42, 2500], [246, 84100]]", SumSquaredDivisors.listSquared(1, 250))
    assertEquals("[[42, 2500], [246, 84100]]", SumSquaredDivisors.listSquared(42, 250))
}