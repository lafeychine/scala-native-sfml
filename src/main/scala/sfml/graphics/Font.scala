package sfml
package graphics

import scalanative.unsafe.*

import internal.Type.{booleanToSfBool, sfBoolToBoolean}
import internal.graphics.Font.*

import graphics.Rect
import stdlib.String.toNativeStdString

class Font private[sfml] (private val font: Ptr[sfFont]) extends Resource:

    private[sfml] inline def toNativeFont: Ptr[sfFont] = font

    override def close(): Unit =
        Font.close(font)()
        Resource.close(font)

    def this() =
        this(Resource { (r: Ptr[sfFont]) => ctor(r) })

    final def loadFromFile(filename: String): Boolean =
        Zone { implicit z => sfFont_loadFromFile(font, filename.toNativeStdString) }

object Font:
    extension (font: Ptr[sfFont])
        private[sfml] def close(): Unit =
            dtor(font)
