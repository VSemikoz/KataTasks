package kata.tasks

import kotlin.test.assertEquals

/**
Your task in order to complete this Kata is to write a function which formats a duration, given as a number of seconds,
in a human-friendly way.

The function must accept a non-negative integer. If it is zero, it just returns "now". Otherwise,
the duration is expressed as a combination of years, days, hours, minutes and seconds.

It is much easier to understand with an example:

formatDuration (62)    // returns "1 minute and 2 seconds"
formatDuration (3662)  // returns "1 hour, 1 minute and 2 seconds"
 */

object TimeFormatter {
    enum class TimeUnits(var secondsCount: Int) {
        Second(1),
        Minute(Second.secondsCount * 60),
        Hour(Minute.secondsCount * 60),
        Day(Hour.secondsCount * 24),
        Year(Day.secondsCount * 365)
    }

    fun TimeUnits.wordFromUnit(unitCount: Int) = if (unitCount > 1) "$unitCount ${name.toLowerCase()}s"
    else "$unitCount ${name.toLowerCase()}"

    fun formatDuration(seconds: Int): String {
        if (seconds == 0) return "now"
        val listOfTime = mutableListOf<String>()
        var currSec = seconds
        for (tu in TimeUnits.values().reversed()) {
            if (currSec >= tu.secondsCount) {
                (currSec / tu.secondsCount).let {
                    listOfTime.add(tu.wordFromUnit(it))
                    currSec %= tu.secondsCount
                }
            }
        }
        listOfTime.joinToString(separator = ", ").run {
            indexOfLast { it == ',' }.let {
                return if (it != -1) replaceRange(it, it + 1, " and")
                else this
            }
        }
    }
}

fun main() {
    // Example Test Cases
    assertEquals("1 second", TimeFormatter.formatDuration(1))
    assertEquals("1 minute and 2 seconds", TimeFormatter.formatDuration(62))
    assertEquals("2 minutes", TimeFormatter.formatDuration(120))
    assertEquals("1 hour", TimeFormatter.formatDuration(3600))
    assertEquals("1 hour, 1 minute and 2 seconds", TimeFormatter.formatDuration(3662))
}