package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.Rect.*

class IntRect(val left: Int = 0, val top: Int = 0, val width: Int = 0, val height: Int = 0):

    private[sfml] final def intRect(implicit z: Zone): Ptr[sfIntRect] =
        val rect = alloc[sfIntRect]()

        rect._1 = left
        rect._2 = top
        rect._3 = width
        rect._4 = height
        rect
