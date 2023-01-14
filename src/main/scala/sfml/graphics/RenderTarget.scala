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

    final def clear(color: Color): Unit =
        Zone { implicit z =>
            sfRenderTarget_clear(renderTarget, color.color)
        }

    final def draw(drawable: Drawable): Unit =
        Zone { implicit z =>
            // TODO: Use RenderStates::Default()
            scala.util.Using.Manager { use =>
                sfRenderTarget_draw(renderTarget, drawable.drawable, use(RenderStates()).renderStates)
            }
        }
