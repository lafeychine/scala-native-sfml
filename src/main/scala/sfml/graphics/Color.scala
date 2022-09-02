package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichByte

import internal.graphics.Color.*

class Color private[sfml] (private[sfml] val color: Ptr[sfColor]) extends Resource:

    def this(r: Byte, g: Byte, b: Byte, a: Byte) =
        this(Resource { (buffer: Ptr[sfColor]) =>
            ctor(buffer, r.toUByte, g.toUByte, b.toUByte, a.toUByte)
        })

    def this(r: Int, g: Int, b: Int, a: Int) = this(r.toByte, g.toByte, b.toByte, a.toByte)

    def this(r: Byte, g: Byte, b: Byte) = this(r, g, b, 255.toByte)

    def this(r: Int, g: Int, b: Int) = this(r.toByte, g.toByte, b.toByte)

    def this() = this(255, 255, 255)

    def close(): Unit = () // TODO

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
