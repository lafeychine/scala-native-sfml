package sfml
package system

import scalanative.unsafe.*

import internal.system.Vector2.*

case class Vector2i(var x: Int, var y: Int) extends SFMLBind[sfVector2i]:

    private[sfml] def bind(implicit z: Zone) =
        val vector2i = alloc[sfVector2i]()

        vector2i._1 = x
        vector2i._2 = y
        return (vector2i)

    def :=(rhs: Vector2i) =
        x = rhs.x
        y = rhs.y

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
        return Vector2i(vector.x.toInt, vector.y.toInt)

    implicit def tupleIntToVectorInt(tuple: (Int, Int)): Vector2i =
        return Vector2i(tuple._1, tuple._2)
