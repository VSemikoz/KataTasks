package kata.tasks

import kotlin.test.assertEquals

/**
 * A friend of mine takes a sequence of numbers from 1 to n (where n > 0).
Within that sequence, he chooses two numbers, a and b.
He says that the product of a and b should be equal to the sum of all numbers in the sequence, excluding a and b.
Given a number n, could you tell me the numbers he excluded from the sequence?
The function takes the parameter: n (n is always strictly greater than 0) and returns an array or a string (depending on the language) of the form:
 */

object RemovedNumbers {
    fun removNb(n: Long): Array<LongArray> {
        val result = mutableListOf<LongArray>()
        val total = (1..n).sum()
        for (a in 1..n) {
            val b = (total - a) / (1 + a)
            if ((total - a) % (1 + a) == 0L && b in 0..n + 1) {
                result.add(longArrayOf(a, b))
            }
        }
        return result.toTypedArray()
    }
}

fun main() {
    testing(26, "[[15, 21], [21, 15]]")
    testing(101, "[[55, 91], [91, 55]]")
    testing(102, "[[70, 73], [73, 70]]")
    testing(100, "[]")
}


fun testing(n: Long, expect: String) {
    val actual = RemovedNumbers.removNb(n)
    assertEquals(expect, actual.contentDeepToString())
}