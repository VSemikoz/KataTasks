package kata.tasks

import kata.tasks.RangeExtraction.rangeExtraction
import kotlin.test.assertEquals

object RangeExtraction {
    fun rangeExtraction(arr: IntArray): String {
        var str = ""

        arr.forEachIndexed{index, element ->
            if (index == 0 || element - arr[index - 1] > 1)
                str += ",$element"
            else if ((index < arr.size - 1 && arr[index + 1] - element > 1) || index == arr.size - 1)
                if (index > 1 && element - arr[index - 2] == 2)
                    str += "-$element"
                else
                    str += ",$element"
        }
        return str.drop(1)
    }
}

fun main() {
    assertEquals("-6,-3-1,3-5,7-11,14,15,17-20", rangeExtraction(intArrayOf(-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20)))
    assertEquals("-3--1,2,10,15,16,18-20", rangeExtraction(intArrayOf(-3, -2, -1, 2, 10, 15, 16, 18, 19, 20)))
}