package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.View.*
import system.Vector2

class View private[sfml] (private val view: Resource[sfView]):

    private[sfml] inline def toNativeView: Ptr[sfView] = view.ptr

    def this() =
        this(Resource { (r: Ptr[sfView]) => ctor(r) })

    def this(center: Vector2[Float], size: Vector2[Float]) =
        this(Resource { (r: Ptr[sfView]) =>
            Zone { implicit z =>
                ctor(r, center.toNativeVector2, size.toNativeVector2)
            }
        })

    def this(rectangle: Rect[Float]) =
        this(Resource { (r: Ptr[sfView]) =>
            Zone { implicit z =>
                ctor(r, rectangle.toNativeRect)
            }
        })

    final def move(offsetX: Float, offsetY: Float): Unit =
        sfView_move(toNativeView, offsetX, offsetY)

    final def move(offset: Vector2[Float]): Unit =
        Zone { implicit z => sfView_move(toNativeView, offset.toNativeVector2) }

    final def reset(rect: Rect[Float]): Unit =
        Zone { implicit z => sfView_reset(toNativeView, rect.toNativeRect) }

    final def rotate(angle: Float): Unit =
        sfView_rotate(toNativeView, angle)

    final def zoom(factor: Float): Unit =
        sfView_zoom(toNativeView, factor)

    /* Getter / Setter */

    final def center: Vector2[Float] =
        Vector2.toVector2Float(sfView_getCenter(toNativeView))()

    final def center_=(x: Float, y: Float) =
        sfView_setCenter(toNativeView, x, y)

    final def center_=(center: Vector2[Float]) =
        Zone { implicit z => sfView_setCenter(toNativeView, center.toNativeVector2) }

    final def rotation: Float =
        sfView_getRotation(toNativeView)

    final def rotation_=(angle: Float) =
        sfView_setRotation(toNativeView, angle)

    final def size: Vector2[Float] =
        Vector2.toVector2Float(sfView_getSize(toNativeView))()

    final def size_=(width: Float, height: Float) =
        sfView_setSize(toNativeView, width, height)

    final def size_=(size: Vector2[Float]) =
        Zone { implicit z => sfView_setSize(toNativeView, size.toNativeVector2) }

    final def transform: Transform =
        Transform.toTransform(sfView_getTransform(toNativeView))()

    final def inverseTransform(): Transform =
        Transform.toTransform(sfView_getInverseTransform(toNativeView))()

    final def viewport: Rect[Float] =
        Rect.toRectFloat(sfView_getViewport(toNativeView))()

    final def viewport_=(viewport: Rect[Float]): Unit =
        Zone { implicit z => sfView_setViewport(toNativeView, viewport.toNativeRect) }
