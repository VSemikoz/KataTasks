package kata.tasks

import kata.tasks.ReducingBySteps.gcdi
import kata.tasks.ReducingBySteps.lcmu
import kata.tasks.ReducingBySteps.maxi
import kata.tasks.ReducingBySteps.mini
import kata.tasks.ReducingBySteps.operArray
import kata.tasks.ReducingBySteps.som
import java.lang.Math.*
import java.util.*
import java.util.function.LongBinaryOperator
import kotlin.test.assertEquals

/**
 * Data: an array of integers, a function f of two variables and an init value.

Example: a = [2, 4, 6, 8, 10, 20], f(x, y) = x + y; init = 0

Output: an array of integers, say r, such that

r = [r[0] = f(init, a[0]), r[1] = f(r[0], a[1]), r[2] = f(r[1], a[2]), ...]

With our example: r = [2, 6, 12, 20, 30, 50]

#Task: Write the following functions of two variables

som : (x, y) -> x + y
mini : (x, y) -> min(x, y)
maxi : (x, y) -> max(x, y)
lcmu : (x, y) -> lcm(abs(x), abs(y) (see note for lcm)
gcdi : (x, y) -> gcd(abs(x), abs(y) (see note for gcd)
and

function oper_array(fct, arr, init) (or operArray or oper-array) where

fct is the function of to variables to apply to the array arr (fct will be one of som, mini, maxi, lcmu or gcdi)
init is the initial value
#Examples:

a = [18, 69, -90, -78, 65, 40]
oper_array(gcd, a, a[0]) => [18, 3, 3, 3, 1, 1]
oper_array(lcm, a, a[0]) => [18, 414, 2070, 26910, 26910, 107640]
oper_array(sum, a, 0) => [18, 87, -3, -81, -16, 24]
oper_array(min, a, a[0]) => [18, 18, -90, -90, -90, -90]
oper_array(max, a, a[0]) => [18, 69, 69, 69, 69, 69]
 */
object ReducingBySteps {

    fun gcdi(xx: Long, yy: Long): Long = if (yy == 0L) abs(xx) else gcdi(yy, xx % yy)

    fun lcmu(a: Long, b: Long): Long = abs(a * b) / gcdi(a, b)

    fun som(a: Long, b: Long): Long = a + b

    fun maxi(a: Long, b: Long): Long = max(a, b)

    fun mini(a: Long, b: Long): Long = min(a, b)

    fun operArray(fct: LongBinaryOperator, arr: LongArray, init: Long): LongArray {
        val result = LongArray(arr.size)
        var buf = init
        arr.forEachIndexed { i, elem ->
            result[i] = fct.applyAsLong(buf, elem)
            buf = result[i]
        }
        return result
    }
}


fun main() {
    println("Fixed Tests operArray : gcdi, lcmu, som, mini, maxi")
    val a = longArrayOf(18, 69, -90, -78, 65, 40)
    var r = longArrayOf(18, 3, 3, 3, 1, 1)
    testing(Arrays.toString(operArray(LongBinaryOperator({ x, y -> gcdi(x, y) }), a, a[0])),
            Arrays.toString(r))
    r = longArrayOf(18, 414, 2070, 26910, 26910, 107640)
    testing(Arrays.toString(operArray(LongBinaryOperator({ a1, b1 -> lcmu(a1, b1) }), a, a[0])),
            Arrays.toString(r))
    r = longArrayOf(18, 87, -3, -81, -16, 24)
    testing(Arrays.toString(operArray(LongBinaryOperator({ a2, b2 -> som(a2, b2) }), a, 0)),
            Arrays.toString(r))
    r = longArrayOf(18, 18, -90, -90, -90, -90)
    testing(Arrays.toString(operArray(LongBinaryOperator({ a3, b3 -> mini(a3, b3) }), a, a[0])),
            Arrays.toString(r))
    r = longArrayOf(18, 69, 69, 69, 69, 69)
    testing(Arrays.toString(operArray(LongBinaryOperator({ a4, b4 -> maxi(a4, b4) }), a, a[0])),
            Arrays.toString(r))
}

private fun testing(actual: String, expected: String) {
    assertEquals(expected, actual)
}