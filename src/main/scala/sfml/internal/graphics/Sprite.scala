package sfml
package internal
package graphics

import scalanative.unsafe.*

@link("sfml-graphics")
@extern private[sfml] object Sprite:
    type sfSprite = CStruct3[Drawable.sfDrawable, Transformable.sfTransformable, CArray[Byte, Nat.Digit3[Nat._1, Nat._1, Nat._2]]]

    @name("_ZN2sf6SpriteC2ERKNS_7TextureE")
    def ctor(self: Ptr[sfSprite], texture: Ptr[Texture.sfTexture]): Unit = extern

    @name("_ZNK2sf6Sprite4drawERNS_12RenderTargetENS_12RenderStatesE")
    def sfSprite_draw(self: Ptr[sfSprite], target: Ptr[RenderTarget.sfRenderTarget], states: RenderStates.sfRenderStates): Unit = extern
