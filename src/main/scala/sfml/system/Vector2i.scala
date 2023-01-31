package sfml
package system

import scala.language.implicitConversions
import scalanative.unsafe.*

import internal.system.Vector2.*

final case class Vector2i(val x: Int, val y: Int):

    private[sfml] def vector(using Zone): Ptr[sfVector2i] =
        val vector2 = alloc[sfVector2i]()

        vector2._1 = x
        vector2._2 = y
        vector2

    def +(rhs: Vector2i) =
        Vector2i(x + rhs.x, y + rhs.y)

    def *(rhs: Vector2i) =
        Vector2i(x * rhs.x, y * rhs.y)

    def *(rhs: Int) =
        Vector2i(x * rhs, y * rhs)

object Vector2i:
    def apply(): Vector2i =
        Vector2i(0, 0)

    def apply(vector: Vector2i): Vector2i =
        Vector2i(vector.x, vector.y)

    implicit def vectorFloatToInt(vector: Vector2f): Vector2i =
        Vector2i(vector.x.toInt, vector.y.toInt)

    implicit def tupleIntToVectorInt(tuple: (Int, Int)): Vector2i =
        Vector2i(tuple._1, tuple._2)
