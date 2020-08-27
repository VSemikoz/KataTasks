package kata.tasks

import kata.tasks.MaximumSubarraySum.maxSequence
import kotlin.test.assertEquals

/**
 * The maximum sum subarray problem consists in finding the maximum sum of a contiguous subsequence in an array or list of integers:

maxSequence(listOf(-2, 1, -3, 4, -1, 2, 1, -5, 4));
// should be 6: listOf(4, -1, 2, 1)
Easy case is when the list is made up of only positive numbers and the maximum sum is the sum of the whole array. If the list is made up of only negative numbers, return 0 instead.

Empty list is considered to have zero greatest sum. Note that the empty list or array is also a valid sublist/subarray.
 */
object MaximumSubarraySum {

    fun maxSequence(arr: List<Int>): Int {
        var maxSoFar = 0
        var maxEndingHere = 0
        arr.forEach {
            maxEndingHere += it
            if (maxSoFar < maxEndingHere) maxSoFar = maxEndingHere
            if (maxEndingHere < 0) maxEndingHere = 0
        }
        return maxSoFar
    }
}


fun main() {
    assertEquals(0, maxSequence(emptyList()))
    assertEquals(6, maxSequence(listOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
}