package sfml
package graphics

import scalanative.unsafe.*

import internal.Type.sfBoolToBoolean
import internal.graphics.Rect.*

import system.Vector2f

case class FloatRect(var left: Float, var top: Float, var width: Float, var height: Float) extends SFMLBind[sfFloatRect]:

    private[sfml] def bind(implicit z: Zone) =
        val floatRect = alloc[sfFloatRect]()

        floatRect._1 = left
        floatRect._2 = top
        floatRect._3 = width
        floatRect._4 = height
        return floatRect

    def :=(rhs: FloatRect) =
        left = rhs.left
        top = rhs.top
        width = rhs.width
        height = rhs.height

    def contains(x: Float, y: Float): Boolean =
        Zone { implicit z => sfFloatRect_contains(bind(z), x, y) }

    def intersects(rhs: FloatRect): Option[FloatRect] =
        val rect = FloatRect()

        Zone { implicit z => Option.when(sfFloatRect_intersects(bind(z), rhs.bind(z), rect.bind(z)))(rect) }

object FloatRect:
    def apply(): FloatRect =
        FloatRect(0, 0, 0, 0)

    def apply(origin: Vector2f, size: Vector2f): FloatRect =
        FloatRect(origin.x, origin.y, size.x, size.y)

    def apply(rect: FloatRect): FloatRect =
        FloatRect(rect.left, rect.top, rect.width, rect.height)

    implicit def rectIntToFloat(rect: IntRect): FloatRect =
        return FloatRect(rect.left, rect.top, rect.width, rect.height)

    implicit def tupleFloatToFloatRect(tuple: (Float, Float, Float, Float)): FloatRect =
        return FloatRect(tuple._1, tuple._2, tuple._3, tuple._4)

    implicit def tupleIntToFloatRect(tuple: (Int, Int, Int, Int)): FloatRect =
        return FloatRect(tuple._1, tuple._2, tuple._3, tuple._4)
