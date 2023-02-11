package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.Type.split
import internal.graphics.RenderWindow.*
import internal.window.Window.sfWindow

import system.String.toNativeString
import system.Vector2
import window.{ContextSettings, Style, VideoMode, Window}

class RenderWindow private[sfml] (private val renderWindow: Ptr[sfRenderWindow])
    extends Window(renderWindow.at1)
    with RenderTarget(renderWindow.at2)
    with Resource:

    private[sfml] inline def toNativeRenderWindow: Ptr[sfRenderWindow] = renderWindow

    override def close(): Unit =
        RenderWindow.close(renderWindow)()
        Resource.close(renderWindow)

    def this(mode: VideoMode, title: String, style: Style, settings: ContextSettings) =
        this(Resource { (r: Ptr[sfRenderWindow]) =>
            Zone { implicit z =>
                val modeSplit = split(mode.toNativeVideoMode)

                ctor(r, modeSplit(0), modeSplit(1), toNativeString(title), style.value.toUInt, settings.toNativeContextSettings)
            }
        })

    def this(mode: VideoMode, title: String, style: Style) =
        this(mode, title, style, ContextSettings())

    def this(mode: VideoMode, title: String) =
        this(mode, title, Style.Default)

    override def size: Vector2[Int] =
        Zone { implicit z => Vector2.toVector2Int(sfRenderWindow_getSize(renderWindow))() }

object RenderWindow:
    extension (renderWindow: Ptr[sfRenderWindow])
        private[sfml] def close(): Unit =
            dtor(renderWindow)
