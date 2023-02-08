package sfml
package graphics

import math.Numeric.Implicits.infixNumericOps

import scala.language.implicitConversions
import scalanative.unsafe.*

import internal.graphics.Rect.*

import system.Vector2

final case class Rect[T: Numeric](val left: T = 0, val top: T = 0, val width: T = 0, val height: T = 0):

    def contains(x: T, y: T)(implicit num: Numeric[T]): Boolean =
        import num.*

        val minX = min(left, left + width)
        val maxX = max(left, left + width)
        val minY = min(top, top + height)
        val maxY = max(top, top + height)

        x >= minX && x < maxX && y >= minY && y < maxY

    def contains(point: Vector2[T])(implicit num: Numeric[T]): Boolean =
        contains(point.x, point.y)

    def intersects(other: Rect[T])(implicit num: Numeric[T]): Boolean =
        import num.*

        val r1MinX = min(left, left + width)
        val r1MaxX = max(left, left + width)
        val r1MinY = min(top, top + height)
        val r1MaxY = max(top, top + height)

        val r2MinX = min(other.left, other.left + other.width)
        val r2MaxX = max(other.left, other.left + other.width)
        val r2MinY = min(other.top, other.top + other.height)
        val r2MaxY = max(other.top, other.top + other.height)

        val interLeft = max(r1MinX, r2MinX)
        val interTop = max(r1MinY, r2MinY)
        val interRight = min(r1MaxX, r2MaxX)
        val interBottom = min(r1MaxY, r2MaxY)

        interLeft < interRight && interTop < interBottom

    private[sfml] final def intRect(using Zone): Ptr[sfIntRect] =
        val rect = alloc[sfIntRect]()

        rect._1 = left.toInt
        rect._2 = top.toInt
        rect._3 = width.toInt
        rect._4 = height.toInt
        rect

object Rect:
    extension (rect: Ptr[sfIntRect])
        private[sfml] def toRectInt(): Rect[Int] =
            Rect(rect._1, rect._2, rect._3, rect._4)

    extension (rect: Ptr[sfFloatRect])
        private[sfml] def toRectFloat(): Rect[Float] =
            Rect(rect._1, rect._2, rect._3, rect._4)

    implicit def tupleToRectFloat[T: Numeric](tuple: (T, T, T, T)): Rect[Float] =
        Rect(tuple._1.toFloat, tuple._2.toFloat, tuple._3.toFloat, tuple._4.toFloat)

    implicit def tupleToRectInt[T: Numeric](tuple: (T, T, T, T)): Rect[Int] =
        Rect(tuple._1.toInt, tuple._2.toInt, tuple._3.toInt, tuple._4.toInt)
