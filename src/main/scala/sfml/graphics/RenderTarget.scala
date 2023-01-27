package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.RenderTarget.*
import internal.window.Window.sfWindow

import system.String
import window.{ContextSettings, VideoMode, Window}

trait RenderTarget private[sfml] (private[sfml] val renderTarget: Ptr[sfRenderTarget]) extends Resource:

    override def close(): Unit =
        Resource.close(dtor)(renderTarget)

    final def clear(color: Color = Color.Black()): Unit =
        Zone { implicit z =>
            sfRenderTarget_clear(renderTarget, color.color)
        }

    final def draw(drawable: Drawable, states: RenderStates = RenderStates()): Unit =
        drawable.draw(this, states)

object RenderTarget:
    import internal.graphics.Drawable.sfDrawable

    private[sfml] def patch_draw(self: Ptr[sfDrawable], target: RenderTarget, states: RenderStates)(implicit z: Zone): Unit =
        // NOTE: Use this endpoint to avoid us splitting `states` in the stack
        sfRenderTarget_draw(target.renderTarget, self, states.renderStates)
