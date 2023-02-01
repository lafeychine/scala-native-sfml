package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.Transformable.*
import system.Vector2

class Transformable private[sfml] (private[sfml] val transformable: Ptr[sfTransformable]) extends Resource:

    override def close(): Unit =
        Transformable.close(transformable)()
        Resource.close(transformable)

    def this() =
        this(Resource { (r: Ptr[sfTransformable]) => ctor(r) })

    final def move(offsetX: Float, offsetY: Float): Unit =
        sfTransformable_move(transformable, offsetX, offsetY)

    final def move(offset: Vector2[Float]): Unit =
        Zone { implicit z => sfTransformable_move(transformable, offset.vector2f) }

    final def rotate(angle: Float): Unit =
        sfTransformable_rotate(transformable, angle)

    final def scale(factorX: Float, factorY: Float): Unit =
        sfTransformable_scale(transformable, factorX, factorY)

    final def scale(factor: Vector2[Float]): Unit =
        Zone { implicit z => sfTransformable_scale(transformable, factor.vector2f) }

    /* Getter / Setter */

    final def origin: Vector2[Float] =
        Vector2.toVector2Float(sfTransformable_getOrigin(transformable))()

    final def origin_=(x: Float, y: Float) =
        sfTransformable_setOrigin(transformable, x, y)

    final def origin_=(origin: Vector2[Float]) =
        Zone { implicit z => sfTransformable_setOrigin(transformable, origin.vector2f) }

    final def position: Vector2[Float] =
        Vector2.toVector2Float(sfTransformable_getPosition(transformable))()

    final def position_=(x: Float, y: Float) =
        sfTransformable_setPosition(transformable, x, y)

    final def position_=(position: Vector2[Float]) =
        Zone { implicit z => sfTransformable_setPosition(transformable, position.vector2f) }

    final def rotation: Float =
        sfTransformable_getRotation(transformable)

    final def rotation_=(angle: Float) =
        Zone { implicit z => sfTransformable_setRotation(transformable, angle) }

    final def scale: Vector2[Float] =
        Vector2.toVector2Float(sfTransformable_getScale(transformable))()

    final def scale_=(x: Float, y: Float) =
        sfTransformable_setScale(transformable, x, y)

    final def scale_=(factors: Vector2[Float]) =
        Zone { implicit z => sfTransformable_setScale(transformable, factors.vector2f) }

    final def transform: Transform =
        Transform.toTransform(sfTransformable_getTransform(transformable))()

    final def inverseTransform(): Transform =
        Transform.toTransform(sfTransformable_getInverseTransform(transformable))()

object Transformable:
    extension (transformable: Ptr[sfTransformable])
        private[sfml] def close(): Unit =
            dtor(transformable)
