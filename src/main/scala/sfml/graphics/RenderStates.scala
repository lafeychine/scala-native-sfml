package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.RenderStates.*

final case class RenderStates(val blendMode: BlendMode, val transform: Transform):

    private[sfml] final def renderStates(using Zone): Ptr[sfRenderStates] =
        import internal.graphics.Transform.sfTransform

        val renderStates = alloc[sfRenderStates]()

        renderStates._1 = blendMode.blendMode
        for i <- 0 until 16 do renderStates._2(i) = transform.matrix(i)
        renderStates

object RenderStates:
    def apply(): RenderStates =
        RenderStates(BlendMode.Alpha(), Transform.Identity())
