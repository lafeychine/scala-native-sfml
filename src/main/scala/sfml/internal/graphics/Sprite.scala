package sfml
package internal
package graphics

import scalanative.unsafe.*

import system.Vector2

@link("sfml-graphics")
@extern object Sprite:
    type sfSprite

    def sfSprite_create(): Ptr[sfSprite] = extern
    def sfSprite_destroy(sprite: Ptr[sfSprite]): Unit = extern

    def sfSprite_getGlobalBounds_fix(sprite: Ptr[sfSprite], rect: Ptr[Rect.sfFloatRect]): Unit = extern

    def sfSprite_getColor_fix(sprite: Ptr[sfSprite], color: Ptr[Color.sfColor]): Unit = extern
    def sfSprite_setColor_fix(sprite: Ptr[sfSprite], color: Ptr[Color.sfColor]): Unit = extern

    def sfSprite_getOrigin_fix(sprite: Ptr[sfSprite], origin: Ptr[Vector2.sfVector2f]): Unit = extern
    def sfSprite_setOrigin_fix(sprite: Ptr[sfSprite], origin: Ptr[Vector2.sfVector2f]): Unit = extern

    def sfSprite_getPosition_fix(sprite: Ptr[sfSprite], position: Ptr[Vector2.sfVector2f]): Unit = extern
    def sfSprite_setPosition_fix(sprite: Ptr[sfSprite], position: Ptr[Vector2.sfVector2f]): Unit = extern

    def sfSprite_getScale_fix(sprite: Ptr[sfSprite], scale: Ptr[Vector2.sfVector2f]): Unit = extern
    def sfSprite_setScale_fix(sprite: Ptr[sfSprite], scale: Ptr[Vector2.sfVector2f]): Unit = extern

    def sfSprite_getTexture(sprite: Ptr[sfSprite]): Ptr[Texture.sfTexture] = extern
    def sfSprite_setTexture(sprite: Ptr[sfSprite], texture: Ptr[Texture.sfTexture], resetRect: Type.sfBool): Unit = extern

    def sfSprite_getTextureRect_fix(sprite: Ptr[sfSprite], rectangle: Ptr[Rect.sfIntRect]): Unit = extern
    def sfSprite_setTextureRect_fix(sprite: Ptr[sfSprite], rectangle: Ptr[Rect.sfIntRect]): Unit = extern
