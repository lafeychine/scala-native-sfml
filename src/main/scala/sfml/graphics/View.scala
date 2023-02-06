package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.View.*
import system.Vector2

class View private[sfml] (private[sfml] val view: Ptr[sfView]) extends Resource:

    override def close(): Unit =
        Resource.close(view)

    def this() =
        this(Resource { (r: Ptr[sfView]) => ctor(r) })

    final def transform: Transform =
        Transform.toTransform(sfView_getTransform(view))()

    final def inverseTransform(): Transform =
        Transform.toTransform(sfView_getInverseTransform(view))()

    final def viewport: Rect[Float] =
        Rect.toRectFloat(sfView_getViewport(view))()
