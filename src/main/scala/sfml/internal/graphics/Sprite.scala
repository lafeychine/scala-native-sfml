package sfml
package internal
package graphics

import scalanative.unsafe.*

@link("sfml-graphics")
@extern private[sfml] object Sprite:
    type sfSprite = CStruct3[Drawable.sfDrawable, Transformable.sfTransformable, CArray[Byte, Nat.Digit3[Nat._1, Nat._0, Nat._4]]]

    @name("_ZN2sf6SpriteC2Ev")
    def ctor(self: Ptr[sfSprite]): Unit = extern

    @name("_ZN2sf6SpriteC2ERKNS_7TextureE")
    def ctor(self: Ptr[sfSprite], texture: Ptr[Texture.sfTexture]): Unit = extern

    @name("_ZNK2sf6Sprite4drawERNS_12RenderTargetENS_12RenderStatesE")
    def sfSprite_draw(self: Ptr[sfSprite], target: Ptr[RenderTarget.sfRenderTarget], states: RenderStates.sfRenderStates): Unit = extern

    @name("_ZNK2sf6Sprite8getColorEv")
    def sfSprite_getColor(self: Ptr[sfSprite]): Ptr[Color.sfColor] = extern

    @name("_ZN2sf6Sprite8setColorERKNS_5ColorE")
    def sfSprite_setColor(self: Ptr[sfSprite], color: Ptr[Color.sfColor]): Unit = extern

    @name("_ZN2sf6Sprite10setTextureERKNS_7TextureEb")
    def sfSprite_setTexture(self: Ptr[sfSprite], texture: Ptr[Texture.sfTexture], resetRect: Type.sfBool): Unit = extern

    @name("_ZNK2sf6Sprite14getTextureRectEv")
    def sfSprite_getTextureRect(self: Ptr[sfSprite]): Ptr[Rect.sfIntRect] = extern

    @name("_ZN2sf6Sprite14setTextureRectERKNS_4RectIiEE")
    def sfSprite_setTextureRect(self: Ptr[sfSprite], rect: Ptr[Rect.sfIntRect]): Unit = extern
