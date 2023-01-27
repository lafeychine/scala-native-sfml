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

    final def position_=(position: Vector2f) =
        Zone { implicit z => sfTransformable_setPosition(transformable, position.vector) }

    final def rotation: Float =
        sfTransformable_getRotation(transformable)

    final def rotation_=(angle: Float) =
        Zone { implicit z => sfTransformable_setRotation(transformable, angle) }

    final def scale: Vector2f =
        val scale = sfTransformable_getScale(transformable)

        Vector2f(scale._1, scale._2)

    final def scale_=(factors: Vector2f) =
        Zone { implicit z => sfTransformable_setScale(transformable, scale.vector) }

    final def origin: Vector2f =
        val origin = sfTransformable_getOrigin(transformable)

        Vector2f(origin._1, origin._2)

    final def origin_=(origin: Vector2f) =
        Zone { implicit z => sfTransformable_setOrigin(transformable, origin.vector) }
