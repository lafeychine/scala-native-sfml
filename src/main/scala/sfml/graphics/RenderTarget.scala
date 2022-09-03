package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.RenderTarget.*
import internal.window.Window.sfWindow

import system.String
import window.{ContextSettings, VideoMode, Window}

trait RenderTarget private[sfml] (private[sfml] val renderTarget: Ptr[sfRenderTarget]) extends Resource:

    final def clear(color: Color): Unit =
        Zone { implicit z =>
            sfRenderTarget_clear(renderTarget, color.color)
        }
