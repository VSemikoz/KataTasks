package kata.tasks

import kotlin.test.assertEquals

/**
 * In this kata, your task is to implement what I call Iterative Rotation Cipher (IRC). To complete the task, you will create an object with two methods, encode and decode. (For non-JavaScript versions, you only need to write the two functions without the enclosing dict)

Input
The encode method will receive two arguments — a positive integer n and a string value.

The decode method will receive one argument — a string value.

Output
Each method will return a string value.

How It Works
Encoding and decoding are done by performing a series of character and substring rotations on a string input.

Encoding: The number of rotations is determined by the value of n. The sequence of rotations is applied in the following order:
 Step 1: remove all spaces in the string (but remember their positions)
 Step 2: shift the order of characters in the new string to the right by n characters
 Step 3: put the spaces back in their original positions
 Step 4: shift the characters of each substring (separated by one or more consecutive spaces) to the right by n
Repeat this process until it has been completed n times in total.
The value n is then prepended to the resulting string with a space.

Decoding: Decoding simply reverses the encoding process.
 */
object IterativeRotationCipher {
    fun encode(n: Int, s: String): String {
        var result = s
        repeat(n) {
            result = result.removeSpaces()
                    .shiftCharRight(n)
                    .setSpaceInPositions(s.getSpaceIndexes())
                    .split(" ")
                    .joinToString(separator = " ") { it.shiftCharRight(n) }
        }
        return "$n $result"
    }

    fun decode(s: String): String {
        val n = s.getN()
        var result = s.substring(s.indexOf(" ") + 1, s.length)
        repeat(n) {
            result = result
                    .split(" ")
                    .joinToString(separator = " ") { it.shiftCharLeft(n) }
                    .removeSpaces()
                    .shiftCharLeft(n)
                    .setSpaceInPositions(result.getSpaceIndexes())
        }
        return result
    }

    fun String.removeSpaces() = this.replace(" ", "")

    fun String.shiftCharRight(n: Int) = if (length == 0) ""
    else "${subSequence(length - (n % length), length)}${subSequence(0, length - (n % length))}"

    fun String.shiftCharLeft(n: Int) = if (length == 0) ""
    else "${subSequence((n % length), length)}${subSequence(0, (n % length))}"

    fun String.getSpaceIndexes(): MutableList<Int> {
        val result = mutableListOf<Int>()
        forEachIndexed { index, c ->
            if (c == ' ') result.add(index)
        }
        return result
    }

    fun String.setSpaceInPositions(spacePositions: MutableList<Int>): String {
        var result = this
        spacePositions.forEach { result = "${result.substring(0, it)} ${result.substring(it, result.length)}" }
        return result
    }

    fun String.getN() = substring(0, indexOf(" ")).toInt()
}

fun main() {
    val encodeExamples = arrayOf(
            Pair(10, "If you wish to make an apple pie from scratch, you must first invent the universe."),
            Pair(14, "True evil is a mundane bureaucracy."),
            Pair(22, "There is nothing more atrociously cruel than an adored child."),
            Pair(36, "As I was going up the stair\nI met a man who wasn't there!\nHe wasn't there again today,\nOh how I wish he'd go away!"),
            Pair(29, "I avoid that bleak first hour of the working day during which my still sluggish senses and body make every chore a penance.\nI find that in arriving later, the work which I do perform is of a much higher quality.")
    )
    val decodeExamples = arrayOf(
            "10 hu fmo a,ys vi utie mr snehn rni tvte .ysushou teI fwea pmapi apfrok rei tnocsclet",
            "14 daue ilev is a munbune Traurecracy.",
            "22 tareu oo iucnaTr dled oldthser.hg hiarm nhcn se rliyet oincoa",
            "36 ws h weA dgIaa ug owh n!asrit git \n msm phw teaI'e tanantwhe reos\ns ther! aHeae 'gwadin\nt haw n htoo ,I'i sy aohOy",
            "29 a r.lht niou gwryd aoshg gIsi mk lei adwhfci isd seensn rdohy mo kleie oltbyhes a\naneu p.n rndr tehh irnne yifav t eo,raclhtc frpw IIti im gwkaidhv aicufh ima doea eruhi y io qshhcoa kr ef l btah gtrrse otnvugrt"
    )

    encodeExamples.forEachIndexed { i, (n, s) -> assertEquals(decodeExamples[i], IterativeRotationCipher.encode(n, s)) }
    decodeExamples.forEachIndexed { i, s -> assertEquals(encodeExamples[i].second, IterativeRotationCipher.decode(s)) }

}
