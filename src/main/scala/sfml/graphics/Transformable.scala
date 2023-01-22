package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.Transformable.*
import internal.system.Vector2.sfVector2f
import system.Vector2f

class Transformable private[sfml] (private[sfml] val transformable: Ptr[sfTransformable]) extends Resource:

    override def close(): Unit =
        Resource.close(dtor)(transformable)

    final def position: Vector2f =
        val pos = sfTransformable_getPosition(transformable)

        Vector2f(pos._1, pos._2)

    final def position_=(pos: Vector2f) =
        Zone { implicit z => sfTransformable_setPosition(transformable, pos.vector) }
