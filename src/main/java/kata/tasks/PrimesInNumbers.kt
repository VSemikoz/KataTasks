package kata.tasks

import junit.framework.Assert.assertEquals

object PrimeDecomp {

    fun factors(l: Int): String {
        l.getPrime().let { list ->
            var result = ""
            list.groupBy { it }.map { it.key to it.value.size }
                    .forEach {
                        result += if (it.second == 1) "(${it.first})"
                        else "(${it.first}**${it.second})"
                    }
            return result
        }
    }

    fun Int.getPrime(): MutableList<Int> {
        var number = this
        val result = mutableListOf<Int>()
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
}

fun main() {
    testing(1024, "(2**10)")
    testing(7775460, "(2**2)(3**3)(5)(7)(11**2)(17)")
}

private fun testing(n: Int, exp: String) {
    val ans = PrimeDecomp.factors(n)
    assertEquals(exp, ans)
}