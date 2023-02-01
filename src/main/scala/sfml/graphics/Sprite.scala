package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.graphics.Sprite.*

class Sprite private[sfml] (private[sfml] val sprite: Ptr[sfSprite]) extends Transformable(sprite.at2) with Drawable with Resource:

    override def close(): Unit =
        Resource.close(sprite)

    def this(texture: Texture) =
        this(Resource { (r: Ptr[sfSprite]) =>
            Zone { implicit z => ctor(r, texture.texture) }
        })

    override final def draw(target: RenderTarget, states: RenderStates): Unit =
        Zone { implicit z => RenderTarget.patch_draw(sprite.at1, target, states) }

    final def color: Color =
        Color.toColor(sfSprite_getColor(sprite))()

    final def color_=(color: Color): Unit =
        Zone { implicit z => sfSprite_setColor(sprite, color.color) }

    final def textureRect: Rect[Int] =
        Rect.toRectInt(sfSprite_getTextureRect(sprite))()

    final def textureRect_=(rect: Rect[Int]): Unit =
        Zone { implicit z => sfSprite_setTextureRect(sprite, rect.intRect) }
