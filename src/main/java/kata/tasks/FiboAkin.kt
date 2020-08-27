package kata.tasks

import kotlin.test.assertEquals

/**
 * How isu[8] calculated?
We have u[7] = 5 and u[6] = 4. These numbers tell us that we have to go backwards from index 8 to index 8 - 5 = 3 and to index 8 - 4 = 4 so to index 3 and 4.

u[3] = 2 and u[4] = 3 hence u[8] = u[3] + u[4] = 2 + 3 = 5.

Another example: let us calculate u[13]. At indexes 12 and 11 we have 8 and 6. Going backwards of 8 and 6 from 13 we get indexes 13 - 8 = 5 and 13 - 6 = 7.
u[5] = 3 and u[7] = 5 so u[13] = u[5] + u[7] = 3 + 5 = 8 .

Task
0) Express u(n) as a function of n, u[n - 1], u[n - 2]. (not tested).
1) Given two numbers n, k (integers > 2) write the function length_sup_u_k(n, k) or lengthSupUK or length-sup-u-k returning the number of terms u[i] >= k with 1 <= i <= n. If we look above we can see that between u[1] and u[23] we have four u[i] greater or equal to 12: length_sup_u_k(23, 12) => 4
2) Given n (integer > 2) write the function comp(n) returning the number of times where a term of u is less than its predecessor up to and including u[n].
 */
object FiboAkin {

    fun lengthSupUK(n: Int, k: Int): Long = genSeq().take(n).count { it >= k }.toLong()

    fun comp(n: Int): Long = genSeq().take(n).zipWithNext().count { it.first > it.second }.toLong()

    fun genSeq(): Sequence<Int> = sequence {
        var index = 2
        val buf = mutableListOf(1, 1)
        yieldAll(buf.asSequence())
        while (true) {
            val first = buf[index - buf[index - 2]]
            val second = buf[index - buf[index - 1]]
            yield(first + second)
            buf.add(first + second)
            index++
        }
    }

    fun dotest1(n: Int, k: Int, expected: Long) {
        assertEquals(expected, lengthSupUK(n, k))
    }

    fun dotest2(n: Int, expected: Long) {
        assertEquals(expected, comp(n))
    }
}

fun main() {
    println("Basic Tests lengthSupUK")
    FiboAkin.dotest1(50, 25, 2)
    FiboAkin.dotest1(3332, 973, 1391)

    println("Basic Tests comp")
    FiboAkin.dotest2(74626, 37128)
    FiboAkin.dotest2(71749, 35692)
}
