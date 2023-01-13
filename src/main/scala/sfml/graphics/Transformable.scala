package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.Transformable.*

class Transformable private[sfml] (private[sfml] val transformable: Ptr[sfTransformable]) extends Resource:

    override def close(): Unit =
        Resource.close(dtor)(transformable)
