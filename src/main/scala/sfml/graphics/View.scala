package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.View.*
import system.Vector2

class View private[sfml] (private val view: Ptr[sfView]) extends Resource:

    private[sfml] inline def toNativeView: Ptr[sfView] = view

    override def close(): Unit =
        Resource.close(view)

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
        sfView_move(view, offsetX, offsetY)

    final def move(offset: Vector2[Float]): Unit =
        Zone { implicit z => sfView_move(view, offset.toNativeVector2) }

    final def reset(rect: Rect[Float]): Unit =
        Zone { implicit z => sfView_reset(view, rect.toNativeRect) }

    final def rotate(angle: Float): Unit =
        sfView_rotate(view, angle)

    final def zoom(factor: Float): Unit =
        sfView_zoom(view, factor)

    /* Getter / Setter */

    final def center: Vector2[Float] =
        Vector2.toVector2Float(sfView_getCenter(view))()

    final def center_=(x: Float, y: Float) =
        sfView_setCenter(view, x, y)

    final def center_=(center: Vector2[Float]) =
        Zone { implicit z => sfView_setCenter(view, center.toNativeVector2) }

    final def rotation: Float =
        sfView_getRotation(view)

    final def rotation_=(angle: Float) =
        sfView_setRotation(view, angle)

    final def size: Vector2[Float] =
        Vector2.toVector2Float(sfView_getSize(view))()

    final def size_=(width: Float, height: Float) =
        sfView_setSize(view, width, height)

    final def size_=(size: Vector2[Float]) =
        Zone { implicit z => sfView_setSize(view, size.toNativeVector2) }

    final def transform: Transform =
        Transform.toTransform(sfView_getTransform(view))()

    final def inverseTransform(): Transform =
        Transform.toTransform(sfView_getInverseTransform(view))()

    final def viewport: Rect[Float] =
        Rect.toRectFloat(sfView_getViewport(view))()

    final def viewport_=(viewport: Rect[Float]): Unit =
        Zone { implicit z => sfView_setViewport(view, viewport.toNativeRect) }
