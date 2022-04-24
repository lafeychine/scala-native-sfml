package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichByte

import internal.graphics.Color.sfColor

case class Color(var r: Byte, var g: Byte, var b: Byte, var a: Byte) extends SFMLBind[sfColor]:

    private[sfml] def bind(implicit z: Zone) =
        val color = alloc[sfColor]()

        color._1 = r.toUByte
        color._2 = g.toUByte
        color._3 = b.toUByte
        color._4 = a.toUByte
        return color

    def :=(rhs: Color) =
        r = rhs.r
        g = rhs.g
        b = rhs.b
        a = rhs.a

object Color:
    def apply(): Color =
        Color(255, 255, 255)

    def apply(r: Byte, g: Byte, b: Byte): Color =
        Color(r, g, b, 255.toByte)

    def apply(r: Int, g: Int, b: Int): Color =
        Color(r.toByte, g.toByte, b.toByte)

    def apply(r: Int, g: Int, b: Int, a: Int): Color =
        Color(r.toByte, g.toByte, b.toByte, a.toByte)

    def apply(color: Color): Color =
        Color(color.r, color.g, color.b, color.a)

    def Black(): Color = Color(0, 0, 0)
    def White(): Color = Color(255, 255, 255)
    def Red(): Color = Color(255, 0, 0)
    def Green(): Color = Color(0, 255, 0)
    def Blue(): Color = Color(0, 0, 255)
    def Yellow(): Color = Color(255, 255, 0)
    def Magenta(): Color = Color(255, 0, 255)
    def Cyan(): Color = Color(0, 255, 255)
    def Transparent(): Color = Color(0, 0, 0, 0)
