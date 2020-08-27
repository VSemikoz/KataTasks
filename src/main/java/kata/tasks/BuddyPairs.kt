package kata.tasks

import kata.tasks.BuddyPairs.buddy
import kotlin.math.sqrt
import kotlin.test.assertEquals

/**
 * You know what divisors of a number are. The divisors of a positive integer n are said to be proper when you consider only the divisors other than n itself. In the following description, divisors will mean proper divisors. For example for 100 they are 1, 2, 4, 5, 10, 20, 25, and 50.

Let s(n) be the sum of these proper divisors of n. Call buddy two positive integers such that the sum of the proper divisors of each number is one more than the other number:

(n, m) are a pair of buddy if s(m) = n + 1 and s(n) = m + 1

For example 48 & 75 is such a pair:

Divisors of 48 are: 1, 2, 3, 4, 6, 8, 12, 16, 24 --> sum: 76 = 75 + 1
Divisors of 75 are: 1, 3, 5, 15, 25 --> sum: 49 = 48 + 1
Task
Given two positive integers start and limit, the function buddy(start, limit) should return the first pair (n m) of buddy pairs such that n (positive integer) is between start (inclusive) and limit (inclusive); m can be greater than limit and has to be greater than n

If there is no buddy pair satisfying the conditions, then return "Nothing" or (for Go lang) nil

Examples
(depending on the languages)

buddy(10, 50) returns [48, 75]
buddy(48, 50) returns [48, 75]
or
buddy(10, 50) returns "(48 75)"
buddy(48, 50) returns "(48 75)"
 */
object BuddyPairs {

    fun buddy(start: Long, limit: Long): String {
        (start..limit).forEach { i ->
            val firstSum = getDivisors(i).sum()
            val secondValue = firstSum - 1
            val secondSum = getDivisors(secondValue).sum()
            if (isBuddy(firstSum, secondValue) && isBuddy(secondSum, i) && i < secondValue) return "($i $secondValue)"
        }
        return "Nothing"
    }

    fun isBuddy(sum: Long, value: Long): Boolean = sum - 1 == value

    fun getDivisors(n: Long): LongArray {
        val result: MutableList<Long> = mutableListOf()
        (1..(sqrt(n.toDouble())).toLong()).forEach { i ->
            if (n % i == 0L) result.addAll(arrayOf(i, n / i))
        }
        result.remove(n)
        return result.toSet().toLongArray()
    }
}


fun main() {
    testing(1071625, 1103735, "(1081184 1331967)")
    testing(2382, 3679, "Nothing")
    testing(381, 4318, "(1050 1925)")
}

private fun testing(start: Long, limit: Long, expected: String) {
    println("Start: $start, Limit: $limit, Expected: $expected")
    assertEquals(expected, buddy(start, limit))
}