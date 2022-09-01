package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.RenderTarget.*
import internal.window.Window.sfWindow

import system.String
import window.{ContextSettings, VideoMode, Window}

trait RenderTarget private[sfml] (self: Ptr[sfRenderTargetFields])
    extends Resource[Ptr[sfRenderTarget]]:

    private[sfml] def bind = self

    def clear(color: Color): Unit =
        Zone { implicit z =>
          sfRenderTarget_clear(bind, color.bind)
        }
