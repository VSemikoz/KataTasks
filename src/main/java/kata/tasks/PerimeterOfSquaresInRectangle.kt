package kata.tasks

import java.math.BigInteger
import kotlin.test.assertEquals
/**
 * The drawing shows 6 squares the sides of which have a length of 1, 1, 2, 3, 5, 8. It's easy to see that the sum of the perimeters of these squares is : 4 * (1 + 1 + 2 + 3 + 5 + 8) = 4 * 20 = 80

Could you give the sum of the perimeters of all the squares in a rectangle when there are n + 1 squares disposed in the same manner as in the drawing:

alternative text

#Hint: See Fibonacci sequence #Ref: http://oeis.org/A000045

The function perimeter has for parameter n where n + 1 is the number of squares (they are numbered from 0 to n) and returns the total perimeter of all the squares.

perimeter(5)  should return 80
perimeter(7)  should return 216
 */
object SumFct {
    fun perimeter(n: Int): BigInteger = fibSeq()
            .take(n + 1)
            .fold(BigInteger.ZERO) { acc, e -> acc + e }
            .times(BigInteger.valueOf(4))

    fun fibSeq(): Sequence<BigInteger> = sequence {
        var a = BigInteger.valueOf(1)
        var b = BigInteger.valueOf(1)
        yield(a)
        while (true) {
            yield(b)
            a = b.also { b += a }
        }
    }
}

fun main() {
    assertEquals(BigInteger.valueOf(80), SumFct.perimeter(5))
    assertEquals(BigInteger.valueOf(216), SumFct.perimeter(7))
    assertEquals(BigInteger.valueOf(14098308), SumFct.perimeter(30))
}