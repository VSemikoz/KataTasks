package kata.tasks

import kotlin.test.assertEquals

fun top3(s: String): List<String> {
    val set = mutableSetOf<Pair<String, Int>>()
    val list = s.toLowerCase()
            .split("[^a-z']+".toRegex())
            .filter { it.contains("[a-z]".toRegex()) }
            .toMutableList()
    while (list.size != 0) {
        list[0].run {
            val count = list.count { it == this }
            set.add(Pair(this, count))
            list.removeIf { it == this }
        }
    }
    return set.sortedBy { it.second }.takeLast(3).map { it.first }.reversed()
}


fun main() {
    assertEquals(listOf("e", "d", "a"), top3("a a a  b  c c  d d d d  e e e e e"))
    assertEquals(listOf("e", "ddd", "aa"), top3("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e"))
    assertEquals(listOf("won't", "wont"), top3("  //wont won't won't "))
    assertEquals(listOf("e"), top3("  , e   .. "))
    assertEquals(emptyList(), top3("  ...  "))
    assertEquals(emptyList(), top3("  '  "))
    assertEquals(emptyList(), top3("  '''  "))
    assertEquals(listOf("a", "of", "on"), top3(sequenceOf(
            "In a village of La Mancha, the name of which I have no desire to call to",
            "mind, there lived not long since one of those gentlemen that keep a lance",
            "in the lance-rack, an old buckler, a lean hack, and a greyhound for",
            "coursing. An olla of rather more beef than mutton, a salad on most",
            "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra",
            "on Sundays, made away with three-quarters of his income."
    ).joinToString("\n")))
}