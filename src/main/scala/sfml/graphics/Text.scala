package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.graphics.Text.*

import system.String.stringToSfString

class Text private[sfml] (private[sfml] val text: Ptr[sfText]) extends Transformable(text.at2) with Drawable with Resource:

    override def close(): Unit =
        Resource.close(text)

    def this() =
        this(Resource { (r: Ptr[sfText]) => ctor(r) })

    def this(string: String, font: Font, characterSize: Int = 30) =
        this(Resource { (r: Ptr[sfText]) =>
            Zone { implicit z => ctor(r, string, font.font, characterSize.toUInt) }
        })

    override final def draw(target: RenderTarget, states: RenderStates): Unit =
        Zone { implicit z => RenderTarget.patch_draw(text.at1, target, states) }
