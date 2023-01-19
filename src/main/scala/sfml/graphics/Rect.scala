package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.Rect.*

final case class IntRect(val left: Int, val top: Int, val width: Int, val height: Int):

    private[sfml] final def intRect(implicit z: Zone): Ptr[sfIntRect] =
        val rect = alloc[sfIntRect]()

        rect._1 = left
        rect._2 = top
        rect._3 = width
        rect._4 = height
        rect

object IntRect:
    def apply(): IntRect =
        IntRect(0, 0, 0, 0)

    implicit def tupleIntToIntRect(tuple: (Int, Int, Int, Int)): IntRect =
        IntRect(tuple._1, tuple._2, tuple._3, tuple._4)
