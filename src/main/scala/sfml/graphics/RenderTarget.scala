package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.RenderTarget.*
import internal.window.Window.sfWindow

import system.{String, Vector2}
import window.{ContextSettings, VideoMode, Window}

trait RenderTarget private[sfml] (private val renderTarget: Resource[sfRenderTarget]):

    private[sfml] inline def toNativeRenderTarget: Ptr[sfRenderTarget] = renderTarget.ptr

    final def clear(color: Color = Color.Black()): Unit =
        Zone { implicit z =>
            sfRenderTarget_clear(toNativeRenderTarget, color.toNativeColor)
        }

    final def draw(drawable: Drawable, states: RenderStates = RenderStates()): Unit =
        drawable.draw(this, states)

    final def mapPixelToCoords(point: Vector2[Int]): Vector2[Float] =
        mapPixelToCoords(point, View(Resource(sfRenderTarget_getView(toNativeRenderTarget))))

    final def mapPixelToCoords(point: Vector2[Int], view: View): Vector2[Float] =
        val viewport_rect = viewport(view)
        val normalized = Vector2(
            -1f + 2f * (point.x - viewport_rect.left) / viewport_rect.width,
            1f - 2f * (point.y - viewport_rect.top) / viewport_rect.height
        )

        view.inverseTransform().transformPoint(normalized)

    final def view: Unit = ()

    final def view_=(view: View): Unit =
        sfRenderTarget_setView(toNativeRenderTarget, view.toNativeView)

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

    private[sfml] def patch_draw(self: Ptr[sfDrawable], target: RenderTarget, states: RenderStates)(using Zone): Unit =
        // NOTE: Use this endpoint to avoid us splitting `states` in the stack
        sfRenderTarget_draw(target.toNativeRenderTarget, self, states.toNativeRenderStates)
