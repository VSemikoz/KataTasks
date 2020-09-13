package kata.tasks

import kata.tasks.ConsecutiveKPrimes.consecKprimes
import kotlin.test.assertEquals

object ConsecutiveKPrimes {

    fun consecKprimes(k: Int, arr: LongArray) = arr
            .toList()
            .zipWithNext()
            .count { it.first.getPrime().count() == k && it.second.getPrime().count() == k }

    private fun Long.getPrime(): MutableList<Long> {
        val result = mutableListOf<Long>()
        var curr = this
        var index = 2L
        while (index <= curr) {
            if (curr % index == 0L) {
                result.add(index)
                curr /= index
                index--
            }
            index++
        }
        return result
    }
}

fun main() {
    println("Basic Tests consecKprimes")
    var a = longArrayOf(10081, 10071, 10077, 10065, 10060, 10070, 10086, 10083, 10078, 10076, 10089, 10085, 10063, 10074, 10068, 10073, 10072, 10075)
    testing(2, a, 2)
    a = longArrayOf(10064)
    testing(6, a, 0)
}

private fun testing(k: Int, arr: LongArray, expected: Int) {
    assertEquals(expected, consecKprimes(k, arr))
}