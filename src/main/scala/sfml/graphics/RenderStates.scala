package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.RenderStates.*

class RenderStates private[sfml] (val renderStates: Ptr[sfRenderStates]) extends Resource:

    override def close(): Unit =
        Resource.close(renderStates)

    def this() =
        this(Resource { (r: Ptr[sfRenderStates]) => ctor(r) })

object RenderStates:
    def Default(): RenderStates =
        RenderStates(sfRenderStates_Default)
