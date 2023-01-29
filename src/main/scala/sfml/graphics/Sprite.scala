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
