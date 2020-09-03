package kata.tasks

import kotlin.test.assertEquals
import kotlin.test.fail

/**
A sandpile is a grid of piles of sand ranging from 0 to some max integer value.
For simplicity's sake, we'll look at a 3x3 grid containing 0, 1, 2, or 3 grains of sand.

An example of this sort of sandpile might look like this:
1 3 0     . ∴
1 2 1  =  . : .
3 2 2     ∴ : :
This means that in row one, column one, there's 1 grain of sand, then 3 grains, then 0, etc.
I mentioned that Sandpiles are a form of number, and as numbers, they support a single operation: addition.
To add two sandpiles, simply add the number of grains of sand within each
cell with the matching cell in the second value:

1 3 0   2 0 2   (1+2) (3+0) (0+2)   3 3 2
1 2 1 + 2 1 0 = (1+2) (2+1) (1+0) = 3 3 1
3 2 2   0 0 1   (3+0) (2+0) (2+1)   3 2 3

You probably already have wondered, what happens if the number of grains goes above our max value of 3?
The answer is, that pile topples over. If the pile is in the middle, it dumps one grain of sand to each neighbor,
keeping whatever is left over. If it's on the edge, it loses one grain to each direct neighbor and also loses
one grain for any edges that are on the side, which just disappear. This means that, no matter what, the over-sized
cell loses 4 grains of sand, while any neighboring cells gain 1 grain of sand.

This repeats until we've reached a state or equilibrium, like so:

 *            *
1 3 0   3 0 2   4 3 2    * 0 5 2      2 1 3      2 1 3
1 2 1 + 2 3 0 = 3 5 1 =>   5 1 2 => * 1 3 2 =>   2 3 2 =>
3 2 2   0 0 1   3 2 3      3 3 3      4 3 3    * 0 4 3
 *

 *
2 1 3    2 2 3      2 2 4      2 3 0 *
=> 2 4 2 => 3 0 4   => 3 1 0 * => 3 1 1
1 0 4    1 2 0 *    1 2 1      1 2 1
 *          *
So the final sum looks like this.

1 3 0   3 0 2   2 3 0
1 2 1 + 2 3 0 = 3 1 1
3 2 2   0 0 1   1 2 1
That's a lot of work, and fairly error-prone. (Trust me!)
 */
object PlayingWithSandpiles {
    class Sandpile(piles: String) {
        var sandpile: MutableList<MutableList<Int>> = MutableList(3) { MutableList(3) { 0 } }

        init {
            piles.filter { it.isDigit() }.forEachIndexed { i, c ->
                sandpile[i / 3][i % 3] = Character.getNumericValue(c)
            }
            topples()
        }

        constructor() : this("000\n000\n000")

        private fun topples() {
            var cont = true
            while (cont) {
                cont = false
                repeat(9) {
                    if (sandpile[it / 3][it % 3] > 3) {
                        cont = true
                        toplessForCell(it / 3, it % 3)
                    }
                }
            }
        }

        private fun toplessForCell(i: Int, j: Int) {
            sandpile[i][j] = sandpile[i][j] - 4
            if (i > 0) sandpile[i - 1][j] += 1
            if (j > 0) sandpile[i][j - 1] += 1
            if (i < 2) sandpile[i + 1][j] += 1
            if (j < 2) sandpile[i][j + 1] += 1
        }

        fun add(addSandpile: Sandpile): Sandpile {
            val result = Sandpile()
            val flatAdd = addSandpile.sandpile
            repeat(9) {
                result.sandpile[it / 3][it % 3] = sandpile[it / 3][it % 3] + flatAdd[it / 3][it % 3]
            }
            result.topples()
            return result
        }

        override fun toString(): String = sandpile.flatten()
                .joinToString(separator = "") { it.toString() }
                .chunked(3)
                .joinToString(separator = "\n")
    }
}


fun main() {
    println("should create a custom sandpile")
    val sp1 = PlayingWithSandpiles.Sandpile("130\n121\n322").toString()
    validate(sp1, "130\n121\n322")

    println("should handle adding an empty sandpile to a non-empty one")
    val sp2 = PlayingWithSandpiles.Sandpile("130\n121\n322")
    val sp3 = PlayingWithSandpiles.Sandpile()
    val sp4 = sp2.add(sp3).toString()
    validate(sp4, "130\n121\n322")

    println("should handle toppling a custom sandpile")
    val sp5 = PlayingWithSandpiles.Sandpile("430\n121\n322").toString()
    validate(sp5, "101\n231\n322")
}

private fun validate(user: String, exp: String) = if (user == exp) assertEquals(exp, user) else fail(failMessage(user, exp))

private fun failMessage(user: String, exp: String) = "Expected:\n\n\t${exp.substring(0..2)}\n\t${exp.substring(4..6)}\n\t${exp.substring(8..10)}\n\nbut got:\n\n\t${user.substring(0..2)}\n\t${user.substring(4..6)}\n\t${user.substring(8..10)}"
