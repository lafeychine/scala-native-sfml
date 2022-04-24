package sfml
package system

import scalanative.unsafe.*

import internal.system.Vector2.*

case class Vector2f(var x: Float, var y: Float) extends SFMLBind[sfVector2f]:

    private[sfml] def bind(implicit z: Zone) =
        val vector2f = alloc[sfVector2f]()

        vector2f._1 = x
        vector2f._2 = y
        return (vector2f)

    def :=(rhs: Vector2f) =
        x = rhs.x
        y = rhs.y

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
        return Vector2f(vector.x, vector.y)

    implicit def tupleFloatToVectorFloat(tuple: (Float, Float)): Vector2f =
        return Vector2f(tuple._1, tuple._2)

    implicit def tupleIntToVectorFloat(tuple: (Int, Int)): Vector2f =
        return Vector2f(tuple._1, tuple._2)
