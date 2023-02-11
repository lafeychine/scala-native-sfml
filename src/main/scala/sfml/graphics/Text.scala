package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.graphics.Text.*

import system.String.toNativeString

class Text private[sfml] (private val text: Ptr[sfText]) extends Transformable(text.at2) with Drawable with Resource:

    private[sfml] inline def toNativeText: Ptr[sfText] = text

    override def close(): Unit =
        system.String.close(text.at3)()
        graphics.VertexArray.close(text.at5)()
        graphics.VertexArray.close(text.at6)()
        Resource.close(text)

    def this() =
        this(Resource { (r: Ptr[sfText]) => ctor(r) })

    def this(string: String, font: Font, characterSize: Int = 30) =
        this(Resource { (r: Ptr[sfText]) =>
            Zone { implicit z => ctor(r, string.toNativeString, font.toNativeFont, characterSize.toUInt) }
        })

    override final def draw(target: RenderTarget, states: RenderStates): Unit =
        Zone { implicit z => RenderTarget.patch_draw(text.at1, target, states) }

    final def globalBounds: Rect[Float] =
        transform.transformRect(localBounds)

    final def localBounds: Rect[Float] =
        sfText_ensureGeometryUpdate(text)

        Rect.toRectFloat(text.at7)()

    /* Getter / Setter */

    final def characterSize: Int =
        sfText_getCharacterSize(text).toInt

    final def characterSize_=(size: Int) =
        sfText_setCharacterSize(text, size.toUInt)

    final def color: Color =
        Color.toColor(sfText_getColor(text))()

    final def color_=(color: Color) =
        Zone { implicit z => sfText_setColor(text, color.toNativeColor) }

    final def fillColor: Color =
        Color.toColor(sfText_getFillColor(text))()

    final def fillColor_=(color: Color) =
        Zone { implicit z => sfText_setFillColor(text, color.toNativeColor) }

    // NOTE: To be able to use [`font_=`]
    final def font = ()

    final def font_=(font: Font) =
        Zone { implicit z => sfText_setFont(text, font.toNativeFont) }

    final def letterSpacing: Float =
        sfText_getLetterSpacing(text)

    final def letterSpacing_=(spacingFactor: Float) =
        sfText_setLetterSpacing(text, spacingFactor)

    final def lineSpacing: Float =
        sfText_getLineSpacing(text)

    final def lineSpacing_=(spacingFactor: Float) =
        sfText_setLineSpacing(text, spacingFactor)

    final def outlineColor: Color =
        Color.toColor(sfText_getOutlineColor(text))()

    final def outlineColor_=(color: Color) =
        Zone { implicit z => sfText_setOutlineColor(text, color.toNativeColor) }

    final def outlineThickness: Float =
        sfText_getOutlineThickness(text)

    final def outlineThickness_=(thickness: Float) =
        sfText_setOutlineThickness(text, thickness)

    // TODO: sfText_getString
    final def string = ()

    final def string_=(string: String) =
        Zone { implicit z => sfText_setString(text, string.toNativeString) }
