package kata.tasks

import kata.tasks.JohnAnnSignCodewars.ann
import kata.tasks.JohnAnnSignCodewars.john
import kata.tasks.JohnAnnSignCodewars.sumAnn
import kata.tasks.JohnAnnSignCodewars.sumJohn
import kotlin.test.assertEquals


/**
Let us call a(n) - and j(n) - the number
of katas done by Ann - and John - at day n. We have a(0) = 1 and in the same manner j(0) = 0.

They have chosen the following rules:

On day n the number of katas done by Ann should be n minus the number of
katas done by John at day t, t being equal to the number of katas done by Ann herself at day n - 1.

On day n the number of katas done by John should be n minus the number of
katas done by Ann at day t, t being equal to the number of katas done by John himself at day n - 1.
 */
object JohnAnnSignCodewars {

    private fun getAnnJohnSeq(): Sequence<Pair<Long, Long>> {
        return sequence {
            var day = 0
            val annSeq = mutableListOf(1L)
            val johnSeq = mutableListOf(0L)
            var ann = 1L
            var john = 0L
            while (true) {
                yield(Pair(ann, john))
                day += 1
                john = day - annSeq[johnSeq[(day - 1)].toInt()]
                johnSeq.add(john)
                ann = day - johnSeq[annSeq[(day - 1)].toInt()]
                annSeq.add(ann)
            }
        }
    }

    fun john(n: Long) = getAnnJohnSeq().take(n.toInt()).map { it.second }.toList()

    fun ann(n: Long) = getAnnJohnSeq().take(n.toInt()).map { it.first }.toList()

    fun sumJohn(n: Long) = john(n).sum()

    fun sumAnn(n: Long) = ann(n).sum()

}


fun main() {
    testJohn(11, listOf(0, 0, 1, 2, 2, 3, 4, 4, 5, 6, 6))
    testAnn(6, listOf(1, 1, 2, 2, 3, 3))
    testSumAnn(115, 4070)
    testSumJohn(75, 1720)
}


private fun testJohn(n: Long, res: List<Long>) {
    assertEquals(res, john(n))
}

private fun testAnn(n: Long, res: List<Long>) {
    assertEquals(res, ann(n))
}

private fun testSumAnn(n: Long, res: Long) {
    assertEquals(res, sumAnn(n))
}

private fun testSumJohn(n: Long, res: Long) {
    assertEquals(res, sumJohn(n))
}
