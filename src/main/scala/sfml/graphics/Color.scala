package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichByte

import internal.graphics.Color.*

class Color(var r: Byte, var g: Byte, var b: Byte, var a: Byte) extends StackResource[Ptr[sfColor]]:

    private[sfml] def bind(implicit z: Zone) =
        val color = alloc[sfColor]()

        color._1 = r.toUByte
        color._2 = g.toUByte
        color._3 = b.toUByte
        color._4 = a.toUByte
        return color

    def this(r: Int, g: Int, b: Int, a: Int) = this(r.toByte, g.toByte, b.toByte, a.toByte)

    def this(r: Byte, g: Byte, b: Byte) = this(r, g, b, 255.toByte)

    def this(r: Int, g: Int, b: Int) = this(r.toByte, g.toByte, b.toByte)

    def this() = this(255, 255, 255)

    def this(color: Color) = this(color.r, color.g, color.b, color.a)


object Color:
    def Black(): Color = Color(0, 0, 0)
    def White(): Color = Color(255, 255, 255)
    def Red(): Color = Color(255, 0, 0)
    def Green(): Color = Color(0, 255, 0)
    def Blue(): Color = Color(0, 0, 255)
    def Yellow(): Color = Color(255, 255, 0)
    def Magenta(): Color = Color(255, 0, 255)
    def Cyan(): Color = Color(0, 255, 255)
    def Transparent(): Color = Color(0, 0, 0, 0)
