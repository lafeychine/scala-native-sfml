package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.Transformable.*
import system.Vector2

class Transformable private[sfml] (private val transformable: Resource[sfTransformable]):

    private[sfml] inline def toNativeTransformable: Ptr[sfTransformable] = transformable.ptr

    def this() =
        this(Resource { (r: Ptr[sfTransformable]) => ctor(r) })

    final def move(offsetX: Float, offsetY: Float): Unit =
        sfTransformable_move(toNativeTransformable, offsetX, offsetY)

    final def move(offset: Vector2[Float]): Unit =
        Zone { implicit z => sfTransformable_move(toNativeTransformable, offset.toNativeVector2) }

    final def rotate(angle: Float): Unit =
        sfTransformable_rotate(toNativeTransformable, angle)

    final def scale(factorX: Float, factorY: Float): Unit =
        sfTransformable_scale(toNativeTransformable, factorX, factorY)

    final def scale(factor: Vector2[Float]): Unit =
        Zone { implicit z => sfTransformable_scale(toNativeTransformable, factor.toNativeVector2) }

    /* Getter / Setter */

    final def origin: Vector2[Float] =
        Vector2.toVector2Float(sfTransformable_getOrigin(toNativeTransformable))()

    final def origin_=(x: Float, y: Float) =
        sfTransformable_setOrigin(toNativeTransformable, x, y)

    final def origin_=(origin: Vector2[Float]) =
        Zone { implicit z => sfTransformable_setOrigin(toNativeTransformable, origin.toNativeVector2) }

    final def position: Vector2[Float] =
        Vector2.toVector2Float(sfTransformable_getPosition(toNativeTransformable))()

    final def position_=(x: Float, y: Float) =
        sfTransformable_setPosition(toNativeTransformable, x, y)

    final def position_=(position: Vector2[Float]) =
        Zone { implicit z => sfTransformable_setPosition(toNativeTransformable, position.toNativeVector2) }

    final def rotation: Float =
        sfTransformable_getRotation(toNativeTransformable)

    final def rotation_=(angle: Float) =
        Zone { implicit z => sfTransformable_setRotation(toNativeTransformable, angle) }

    final def scale: Vector2[Float] =
        Vector2.toVector2Float(sfTransformable_getScale(toNativeTransformable))()

    final def scale_=(x: Float, y: Float) =
        sfTransformable_setScale(toNativeTransformable, x, y)

    final def scale_=(factors: Vector2[Float]) =
        Zone { implicit z => sfTransformable_setScale(toNativeTransformable, factors.toNativeVector2) }

    final def transform: Transform =
        Transform.toTransform(sfTransformable_getTransform(toNativeTransformable))()

    final def inverseTransform(): Transform =
        Transform.toTransform(sfTransformable_getInverseTransform(toNativeTransformable))()
