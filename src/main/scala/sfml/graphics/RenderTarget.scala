package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.RenderTarget.*
import internal.window.Window.sfWindow

import system.{String, Vector2}
import window.{ContextSettings, VideoMode, Window}

trait RenderTarget private[sfml] (private[sfml] val renderTarget: Ptr[sfRenderTarget]) extends Resource:

    override def close(): Unit =
        RenderTarget.close(renderTarget)()
        Resource.close(renderTarget)

    final def clear(color: Color = Color.Black()): Unit =
        Zone { implicit z =>
            sfRenderTarget_clear(renderTarget, color.color)
        }

    final def draw(drawable: Drawable, states: RenderStates = RenderStates()): Unit =
        drawable.draw(this, states)

    final def mapPixelToCoords(point: Vector2[Int]): Vector2[Float] =
        mapPixelToCoords(point, view)

    final def mapPixelToCoords(point: Vector2[Int], view: View): Vector2[Float] =
        val viewport_rect = viewport(view)
        val normalized = Vector2(
            -1f + 2f * (point.x - viewport_rect.left) / viewport_rect.width,
            1f - 2f * (point.y - viewport_rect.top) / viewport_rect.height
        )

        view.inverseTransform().transformPoint(normalized)

    private[sfml] def view: View =
        View(sfRenderTarget_getView(renderTarget))

    final def viewport(view: View): Rect[Int] =
        val viewport_rect = view.viewport

        Rect(
            (.5f + size.x * viewport_rect.left).toInt,
            (.5f + size.y * viewport_rect.top).toInt,
            (.5f + size.x * viewport_rect.width).toInt,
            (.5f + size.y * viewport_rect.height).toInt
        )

    def size: Vector2[Int]

object RenderTarget:
    import internal.graphics.Drawable.sfDrawable

    extension (renderTarget: Ptr[sfRenderTarget])
        private[sfml] def close(): Unit =
            dtor(renderTarget)

    private[sfml] def patch_draw(self: Ptr[sfDrawable], target: RenderTarget, states: RenderStates)(using Zone): Unit =
        // NOTE: Use this endpoint to avoid us splitting `states` in the stack
        sfRenderTarget_draw(target.renderTarget, self, states.renderStates)
