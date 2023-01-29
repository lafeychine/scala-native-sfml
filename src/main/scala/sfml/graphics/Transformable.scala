package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.Transformable.*
import system.Vector2f

class Transformable private[sfml] (private[sfml] val transformable: Ptr[sfTransformable]) extends Resource:

    override def close(): Unit =
        Resource.close(dtor)(transformable)

    def this() =
        this(Resource { (r: Ptr[sfTransformable]) => ctor(r) })

    final def move(offsetX: Float, offsetY: Float): Unit =
        sfTransformable_move(transformable, offsetX, offsetY)

    final def move(offset: Vector2f): Unit =
        Zone { implicit z => sfTransformable_move(transformable, offset.vector) }

    final def rotate(angle: Float): Unit =
        sfTransformable_rotate(transformable, angle)

    final def scale(factorX: Float, factorY: Float): Unit =
        sfTransformable_scale(transformable, factorX, factorY)

    final def scale(factor: Vector2f): Unit =
        Zone { implicit z => sfTransformable_scale(transformable, factor.vector) }

    /* Getter / Setter */

    final def origin: Vector2f =
        val origin = sfTransformable_getOrigin(transformable)

        Vector2f(origin._1, origin._2)

    final def origin_=(x: Float, y: Float) =
        sfTransformable_setOrigin(transformable, x, y)

    final def origin_=(origin: Vector2f) =
        Zone { implicit z => sfTransformable_setOrigin(transformable, origin.vector) }

    final def position: Vector2f =
        val pos = sfTransformable_getPosition(transformable)

        Vector2f(pos._1, pos._2)

    final def position_=(x: Float, y: Float) =
        sfTransformable_setPosition(transformable, x, y)

    final def position_=(position: Vector2f) =
        Zone { implicit z => sfTransformable_setPosition(transformable, position.vector) }

    final def rotation: Float =
        sfTransformable_getRotation(transformable)

    final def rotation_=(angle: Float) =
        Zone { implicit z => sfTransformable_setRotation(transformable, angle) }

    final def scale: Vector2f =
        val scale = sfTransformable_getScale(transformable)

        Vector2f(scale._1, scale._2)

    final def scale_=(x: Float, y: Float) =
        sfTransformable_setScale(transformable, x, y)

    final def scale_=(factors: Vector2f) =
        Zone { implicit z => sfTransformable_setScale(transformable, factors.vector) }
