package sfml
package graphics

import scalanative.unsafe.*

import internal.Type.sfBoolToBoolean
import internal.graphics.Rect.*

import system.Vector2i

case class IntRect(var left: Int, var top: Int, var width: Int, var height: Int) extends SFMLBind[sfIntRect]:

    private[sfml] def bind(implicit z: Zone) =
        val intRect = alloc[sfIntRect]()

        intRect._1 = left
        intRect._2 = top
        intRect._3 = width
        intRect._4 = height
        return intRect

    def :=(rhs: IntRect) =
        left = rhs.left
        top = rhs.top
        width = rhs.width
        height = rhs.height

    def contains(x: Int, y: Int): Boolean =
        Zone { implicit z => sfIntRect_contains(bind(z), x, y) }

    def intersects(rhs: IntRect): Option[IntRect] =
        val rect = IntRect()

        Zone { implicit z => Option.when(sfIntRect_intersects(bind(z), rhs.bind(z), rect.bind(z)))(rect) }

object IntRect:
    def apply(): IntRect =
        IntRect(0, 0, 0, 0)

    def apply(origin: Vector2i, size: Vector2i): IntRect =
        IntRect(origin.x, origin.y, size.x, size.y)

    def apply(rect: IntRect): IntRect =
        IntRect(rect.left, rect.top, rect.width, rect.height)

    implicit def rectFloatToInt(rect: FloatRect): IntRect =
        return IntRect(rect.left.toInt, rect.top.toInt, rect.width.toInt, rect.height.toInt)

    implicit def tupleIntToIntRect(tuple: (Int, Int, Int, Int)): IntRect =
        return IntRect(tuple._1, tuple._2, tuple._3, tuple._4)
