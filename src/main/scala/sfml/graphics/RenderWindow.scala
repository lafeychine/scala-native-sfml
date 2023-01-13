package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.Type.{booleanToSfBool, split, sfBoolToBoolean}
import internal.graphics.RenderWindow.*
import internal.window.Window.sfWindow

import system.String
import window.{ContextSettings, VideoMode, Window}

class RenderWindow private[sfml] (private[sfml] val renderWindow: Ptr[sfRenderWindow]) extends Window(renderWindow.at1) with RenderTarget(renderWindow.at2) with Resource:

    override def close(): Unit =
        Resource.close(dtor)(renderWindow)

    def this(mode: VideoMode, title: String, style: Window.WindowStyle) =
        this(Resource { (r: Ptr[sfRenderWindow]) =>
            Zone { implicit z =>
                val modeSplit = split(mode.videoMode)

                ctor(r, modeSplit(0), modeSplit(1), title.string, style.value.toUInt, ContextSettings().contextSettings);
            }
        })
