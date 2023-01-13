package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.RenderStates.*

class RenderStates private[sfml] (val renderStates: Ptr[sfRenderStates]) extends Resource:

    override def close(): Unit =
        Resource.close(renderStates)

    def this() =
        this(Resource { (r: Ptr[sfRenderStates]) => ctor(r) })

@extern object Test:
    def toto(self: Ptr[sfml.internal.graphics.RenderStates.sfRenderStates]): Unit = extern

object RenderStates:
    def Default(): RenderStates = {
        Test.toto(sfRenderStates_Default)
        RenderStates(sfRenderStates_Default)
    }
