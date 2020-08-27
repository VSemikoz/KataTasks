package kata.tasks

import kata.tasks.SumByFactors.sumOfDivided
import kotlin.test.assertEquals

/**
Given an array of positive or negative integers

I= [i1,..,in]

you have to produce a sorted array P of the form

[ [p, sum of all ij of I for which p is a prime factor (p positive) of ij] ...]

P will be sorted by increasing order of the prime numbers.
The final result has to be given as a string in Java, C#, C, C++ and as an array of arrays
in other languages.

Example:

I = (/12, 15/); // result = "(2 12)(3 27)(5 15)"
[2, 3, 5] is the list of all prime factors of the elements of I, hence the result.

Notes:

It can happen that a sum is 0 if some numbers are negative!
Example: I = [15, 30, -45] 5 divides 15, 30 and (-45) so 5 appears in the result,
the sum of the numbers for which 5 is a factor is 0 so we have [5, 0] in the result amongst others.

In Fortran - as in any other language - the returned string is not permitted to contain
any redundant trailing whitespace: you can use dynamically allocated character strings.
 */
object SumByFactors {
    fun sumOfDivided(l: IntArray): String {
        var result = ""
        val setOfPrimes = mutableSetOf<Int>()
        l.forEach { setOfPrimes.addAll(it.getPrime()) }
        setOfPrimes.sorted()
                .forEach{
                    result += "($it ${it.filterDivided(l).sum()})"
                }
        return result
    }

    fun Int.getPrime(): MutableSet<Int> {
        var number = this
        val result = mutableSetOf<Int>()
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

    fun Int.filterDivided(numbers: IntArray) = numbers.filter { it % this == 0 }
}


fun main() {
    var lst = intArrayOf(12, 15)
    assertEquals("(2 12)(3 27)(5 15)",
            sumOfDivided(lst))

    lst = intArrayOf(15, 21, 24, 30, 45)
    assertEquals("(2 54)(3 135)(5 90)(7 21)",
            sumOfDivided(lst))

    lst = intArrayOf(107, 158, 204, 100, 118, 123, 126, 110, 116, 100)
    assertEquals("(2 1032)(3 453)(5 310)(7 126)(11 110)(17 204)(29 116)(41 123)(59 118)(79 158)(107 107)",
            sumOfDivided(lst))

    lst = intArrayOf()
    assertEquals("",
            sumOfDivided(lst))

    lst = intArrayOf(1070, 1580, 2040, 1000, 1180, 1230, 1260, 1100, 1160, 1000)
    assertEquals("(2 12620)(3 4530)(5 12620)(7 1260)(11 1100)(17 2040)(29 1160)(41 1230)(59 1180)(79 1580)(107 1070)",
            sumOfDivided(lst))

}