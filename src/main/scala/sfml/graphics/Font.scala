package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.Font.*

case class Font private[sfml] (font: Ptr[sfFont]) extends SFMLBind[sfFont] with AutoCloseable:

    private[sfml] def bind(implicit z: Zone) = font

    def close(): Unit =
        sfFont_destroy(font)

object Font:
    def apply(filename: String): Font =
        Font(Zone { implicit z => sfFont_createFromFile(toCString(filename)) })
