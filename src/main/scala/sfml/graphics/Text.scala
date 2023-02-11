package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.graphics.Text.*

import system.String.toNativeString

class Text private[sfml] (private val text: Resource[sfText])
    extends Transformable(Resource(text.ptr.at2))
    with Drawable
    with AutoCloseable:

    private[sfml] inline def toNativeText: Ptr[sfText] = text.ptr

    override def close(): Unit =
        system.String.close(text.ptr.at3)()
        graphics.VertexArray.close(text.ptr.at5)()
        graphics.VertexArray.close(text.ptr.at6)()

    def this() =
        this(Resource { (r: Ptr[sfText]) => ctor(r) })

    def this(string: String, font: Font, characterSize: Int = 30) =
        this(Resource { (r: Ptr[sfText]) =>
            Zone { implicit z => ctor(r, string.toNativeString, font.toNativeFont, characterSize.toUInt) }
        })

    override final def draw(target: RenderTarget, states: RenderStates): Unit =
        Zone { implicit z => RenderTarget.patch_draw(toNativeText.at1, target, states) }

    final def globalBounds: Rect[Float] =
        transform.transformRect(localBounds)

    final def localBounds: Rect[Float] =
        sfText_ensureGeometryUpdate(toNativeText)

        Rect.toRectFloat(toNativeText.at7)()

    /* Getter / Setter */

    final def characterSize: Int =
        sfText_getCharacterSize(toNativeText).toInt

    final def characterSize_=(size: Int) =
        sfText_setCharacterSize(toNativeText, size.toUInt)

    final def color: Color =
        Color.toColor(sfText_getColor(toNativeText))()

    final def color_=(color: Color) =
        Zone { implicit z => sfText_setColor(toNativeText, color.toNativeColor) }

    final def fillColor: Color =
        Color.toColor(sfText_getFillColor(toNativeText))()

    final def fillColor_=(color: Color) =
        Zone { implicit z => sfText_setFillColor(toNativeText, color.toNativeColor) }

    // NOTE: To be able to use [`font_=`]
    final def font = ()

    final def font_=(font: Font) =
        Zone { implicit z => sfText_setFont(toNativeText, font.toNativeFont) }

    final def letterSpacing: Float =
        sfText_getLetterSpacing(toNativeText)

    final def letterSpacing_=(spacingFactor: Float) =
        sfText_setLetterSpacing(toNativeText, spacingFactor)

    final def lineSpacing: Float =
        sfText_getLineSpacing(toNativeText)

    final def lineSpacing_=(spacingFactor: Float) =
        sfText_setLineSpacing(toNativeText, spacingFactor)

    final def outlineColor: Color =
        Color.toColor(sfText_getOutlineColor(toNativeText))()

    final def outlineColor_=(color: Color) =
        Zone { implicit z => sfText_setOutlineColor(toNativeText, color.toNativeColor) }

    final def outlineThickness: Float =
        sfText_getOutlineThickness(toNativeText)

    final def outlineThickness_=(thickness: Float) =
        sfText_setOutlineThickness(toNativeText, thickness)

    // TODO: sfText_getString
    final def string = ()

    final def string_=(string: String) =
        Zone { implicit z => sfText_setString(toNativeText, string.toNativeString) }
