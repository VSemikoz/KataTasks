package kata.tasks

import kotlin.test.assertEquals

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
