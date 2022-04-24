package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.graphics.Color.sfColor
import internal.graphics.Rect.sfFloatRect
import internal.graphics.Text.*
import internal.system.Vector2.sfVector2f

import system.Vector2f

case class Text private[sfml] (text: Ptr[sfText]) extends SFMLBind[sfText] with AutoCloseable:

    private[sfml] def bind(implicit z: Zone) = text

    def close(): Unit =
        sfText_destroy(text)

    def characterSize: Int =
        sfText_getCharacterSize(text).toInt

    def characterSize_=(size: Int) =
        sfText_setCharacterSize(text, size.toUInt)

    def color: Color =
        Zone { implicit z =>
            val color = alloc[sfColor]()

            sfText_getColor_fix(text, color)
            Color(color._1.toByte, color._2.toByte, color._3.toByte, color._4.toByte)
        }

    def color_=(color: Color) =
        Zone { implicit z => sfText_setColor_fix(text, color.bind(z)) }

    def font: Font =
        Font(sfText_getFont(text))

    def font_=(font: Font) =
        Zone { implicit z => sfText_setFont(text, font.bind(z)) }

    def globalBounds: FloatRect =
        Zone { implicit z =>
            val rect = alloc[sfFloatRect]()

            sfText_getGlobalBounds_fix(text, rect)
            FloatRect(rect._1, rect._2, rect._3, rect._4)
        }

    def position: Vector2f =
        Zone { implicit z =>
            val vect = alloc[sfVector2f]()

            sfText_getPosition_fix(text, vect)
            Vector2f(vect._1, vect._2)
        }

    def position_=(position: Vector2f) =
        Zone { implicit z => sfText_setPosition_fix(text, position.bind(z)) }

    def string: String =
        Zone { implicit z => fromCString(sfText_getString(text)) }

    def string_=(string: String) =
        Zone { implicit z => sfText_setString(text, toCString(string)) }

object Text:
    def apply(): Text =
        Text(sfText_create())
