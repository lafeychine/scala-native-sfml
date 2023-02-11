package sfml
package graphics

import scalanative.unsafe.*

import internal.Type.{booleanToSfBool, sfBoolToBoolean}
import internal.graphics.Font.*

import graphics.Rect
import stdlib.String.toNativeStdString

class Font private[sfml] (private val font: Resource[sfFont]) extends AutoCloseable:

    private[sfml] inline def toNativeFont: Ptr[sfFont] = font.ptr

    override def close(): Unit =
        Font.close(toNativeFont)()

    def this() =
        this(Resource { (r: Ptr[sfFont]) => ctor(r) })

    final def loadFromFile(filename: String): Boolean =
        Zone { implicit z => sfFont_loadFromFile(toNativeFont, filename.toNativeStdString) }

object Font:
    extension (font: Ptr[sfFont])
        private[sfml] def close(): Unit =
            dtor(font)
