package sfml
package graphics

import scalanative.unsafe.*

import internal.Type.booleanToSfBool
import internal.graphics.Color.sfColor
import internal.graphics.Rect.{sfFloatRect, sfIntRect}
import internal.graphics.Sprite.*
import internal.system.Vector2.sfVector2f

import graphics.Color
import system.Vector2f

case class Sprite private[sfml] (sprite: Ptr[sfSprite]) extends SFMLBind[sfSprite] with AutoCloseable:

    private[sfml] def bind(implicit z: Zone) = sprite

    def close(): Unit =
        sfSprite_destroy(sprite)

    def color: Color =
        Zone { implicit z =>
            val color = alloc[sfColor]()

            sfSprite_getColor_fix(sprite, color)
            Color(color._1.toByte, color._2.toByte, color._3.toByte, color._4.toByte)
        }

    def color_=(color: Color) =
        Zone { implicit z => sfSprite_setColor_fix(sprite, color.bind(z)) }

    def globalBounds: FloatRect =
        Zone { implicit z =>
            val rect = alloc[sfFloatRect]()

            sfSprite_getGlobalBounds_fix(sprite, rect)
            FloatRect(rect._1, rect._2, rect._3, rect._4)
        }

    def origin: Vector2f =
        Zone { implicit z =>
            val vect = alloc[sfVector2f]()

            sfSprite_getOrigin_fix(sprite, vect)
            Vector2f(vect._1, vect._2)
        }

    def origin_=(origin: Vector2f) =
        Zone { implicit z => sfSprite_setOrigin_fix(sprite, origin.bind(z)) }

    def position: Vector2f =
        Zone { implicit z =>
            val vect = alloc[sfVector2f]()

            sfSprite_getPosition_fix(sprite, vect)
            Vector2f(vect._1, vect._2)
        }

    def position_=(position: Vector2f) =
        Zone { implicit z => sfSprite_setPosition_fix(sprite, position.bind(z)) }

    def scale: Vector2f =
        Zone { implicit z =>
            val vect = alloc[sfVector2f]()

            sfSprite_getScale_fix(sprite, vect)
            Vector2f(vect._1, vect._2)
        }

    def scale_=(scale: Vector2f) =
        Zone { implicit z => sfSprite_setScale_fix(sprite, scale.bind(z)) }

    def texture: Texture =
        Texture(sfSprite_getTexture(sprite))

    def texture_=(texture: Texture) =
        Zone { implicit z => sfSprite_setTexture(sprite, texture.bind(z), true) }

    def textureRect: IntRect =
        Zone { implicit z =>
            val rect = alloc[sfIntRect]()

            sfSprite_getTextureRect_fix(sprite, rect)
            IntRect(rect._1, rect._2, rect._3, rect._4)
        }

    def textureRect_=(rectangle: IntRect) =
        Zone { implicit z => sfSprite_setTextureRect_fix(sprite, rectangle.bind(z)) }

object Sprite:
    def apply(): Sprite =
        Sprite(sfSprite_create())

    def apply(texture: Texture): Sprite =
        val sprite = Sprite()

        sprite.texture = texture
        sprite
