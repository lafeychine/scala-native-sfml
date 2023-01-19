package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.RenderStates.*

final case class RenderStates(val blendMode: BlendMode):

    private[sfml] final def renderStates(implicit z: Zone): Ptr[sfRenderStates] =
        val renderStates = alloc[sfRenderStates]()

        renderStates._1 = blendMode.blendMode
        renderStates._2(0) = 1
        renderStates._2(5) = 1
        renderStates._2(10) = 1
        renderStates._2(15) = 1
        renderStates

object RenderStates:
    def apply(): RenderStates =
        RenderStates(BlendMode.Alpha())
