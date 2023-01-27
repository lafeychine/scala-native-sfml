package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.Type.split
import internal.graphics.RenderWindow.*
import internal.window.Window.sfWindow

import system.String.stringToSfString
import window.{ContextSettings, Style, VideoMode, Window}

class RenderWindow private[sfml] (private[sfml] val renderWindow: Ptr[sfRenderWindow])
    extends Window(renderWindow.at1)
    with RenderTarget(renderWindow.at2)
    with Resource:

    override def close(): Unit =
        Resource.close(dtor)(renderWindow)

    def this(mode: VideoMode, title: String, style: Style, settings: ContextSettings) =
        this(Resource { (r: Ptr[sfRenderWindow]) =>
            Zone { implicit z =>
                val modeSplit = split(mode.videoMode)

                ctor(r, modeSplit(0), modeSplit(1), title, style.value.toUInt, settings.contextSettings);
            }
        })

    def this(mode: VideoMode, title: String, style: Style) =
        this(mode, title, style, ContextSettings())

    def this(mode: VideoMode, title: String) =
        this(mode, title, Style.Default)
