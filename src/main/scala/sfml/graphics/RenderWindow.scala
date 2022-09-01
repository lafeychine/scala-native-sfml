package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.Type.{booleanToSfBool, split, sfBoolToBoolean}
import internal.graphics.RenderWindow.*
import internal.window.Window.sfWindow

import system.String
import window.{ContextSettings, VideoMode, Window}

class RenderWindow private[sfml] (self: Ptr[sfRenderWindowFields])
        extends Window(self.at1)
        with RenderTarget(self.at2)
        with Resource[Ptr[sfRenderWindow]]:

    private[sfml] override def bind = self

    def this(mode: VideoMode, title: String, style: Window.WindowStyle) =
        this(Resource { (r: Ptr[sfRenderWindow]) =>
            Zone { implicit z =>
                val modeSplit = split(mode.bind)

                ctor(r, modeSplit(0), modeSplit(1), title.bind, style.value.toUInt, ContextSettings().bind);
            }
        })

    override def close(): Unit =
        Resource.close(dtor)(bind)
