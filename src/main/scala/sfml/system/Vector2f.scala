package sfml
package system

import scala.language.implicitConversions
import scalanative.unsafe.*

import internal.system.Vector2.*

final case class Vector2f(val x: Float, val y: Float):

    private[sfml] def vector(implicit z: Zone): Ptr[sfVector2f] =
        val vector2 = alloc[sfVector2f]()

        vector2._1 = x
        vector2._2 = y
        vector2

    def +(rhs: Vector2f) =
        Vector2f(x + rhs.x, y + rhs.y)

    def *(rhs: Vector2f) =
        Vector2f(x * rhs.x, y * rhs.y)

    def *(rhs: Float) =
        Vector2f(x * rhs, y * rhs)

object Vector2f:
    def apply(): Vector2f =
        Vector2f(0, 0)

    def apply(vector: Vector2f): Vector2f =
        Vector2f(vector.x, vector.y)

    implicit def vectorIntToFloat(vector: Vector2i): Vector2f =
        Vector2f(vector.x.toFloat, vector.y.toFloat)

    implicit def tupleFloatToVectorFloat(tuple: (Float, Float)): Vector2f =
        Vector2f(tuple._1, tuple._2)

    implicit def tupleIntToVectorFloat(tuple: (Int, Int)): Vector2f =
        Vector2f(tuple._1.toFloat, tuple._2.toFloat)
