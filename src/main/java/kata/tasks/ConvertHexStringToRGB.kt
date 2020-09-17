package kata.tasks

import kata.tasks.ConvertHexStringToRGB.RGB
import kata.tasks.ConvertHexStringToRGB.hexStringToRGB
import kotlin.test.assertEquals

/**
When working with color values it can sometimes be useful to extract the individual red,
green, and blue (RGB) component values for a color. Implement a function that meets these requirements:

Accepts a case-insensitive hexadecimal color string as its parameter (ex. "#FF9933" or "#ff9933")
Returns an object with the structure {r: 255, g: 153, b: 51} where r, g, and b range from 0 through 255
Note: your implementation does not need to support the shorthand form of hexadecimal notation (ie "#FFF")
 */
object ConvertHexStringToRGB {
    data class RGB(val r: Int, val g: Int, val b: Int)

    fun hexStringToRGB(hexString: String): RGB {
        hexString.substring(1)
                .windowed(2, step = 2)
                .map { it.toInt(radix = 16) }.let {
                    return RGB(it[0], it[1], it[2])
                }
    }
}

fun main() {
    assertEquals(RGB(r = 255, g = 153, b = 51), hexStringToRGB("#FF9933"))
    assertEquals(RGB(r = 190, g = 173, b = 237), hexStringToRGB("#beaded"))
    assertEquals(RGB(r = 0, g = 0, b = 0), hexStringToRGB("#000000"))
    assertEquals(RGB(r = 17, g = 17, b = 17), hexStringToRGB("#111111"))
    assertEquals(RGB(r = 250, g = 52, b = 86), hexStringToRGB("#Fa3456"))
}