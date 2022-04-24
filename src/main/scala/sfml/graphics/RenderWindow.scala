package sfml
package graphics

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.Type.{booleanToSfBool, sfBoolToBoolean}
import internal.graphics.RenderWindow.*
import internal.window.Event.sfEvent
import window.{Event, VideoMode, Window}

case class RenderWindow private[sfml] (renderWindow: Ptr[sfRenderWindow]) extends SFMLBind[sfRenderWindow] with AutoCloseable:

    private[sfml] def bind(implicit z: Zone): Ptr[sfRenderWindow] = renderWindow

    private var _framerateLimit: Int = 0
    private var _verticalSync: Boolean = false

    def clear(color: Color): Unit =
        Zone { implicit z => sfRenderWindow_clear_fix(renderWindow, color.bind(z)) }

    def close(): Unit =
        sfRenderWindow_close(renderWindow)
        sfRenderWindow_destroy(renderWindow)

    def display(): Unit =
        sfRenderWindow_display(renderWindow)

    def draw(sprite: Sprite): Unit =
        Zone { implicit z => sfRenderWindow_drawSprite(renderWindow, sprite.bind(z), null) }

    def draw(text: Text): Unit =
        Zone { implicit z => sfRenderWindow_drawText(renderWindow, text.bind(z), null) }

    def isOpen(): Boolean =
        sfRenderWindow_isOpen(renderWindow)

    def pollEvent(): LazyList[Event] =
        def polling(event: Ptr[sfEvent]): Event =
            if sfRenderWindow_pollEvent(renderWindow, event) then {
                return Event(event).getOrElse(polling(event))
            }
            return null

        Zone { implicit z =>
            val event = alloc[sfEvent]()

            return LazyList.continually(polling(event)).takeWhile(_ != null)
        }

    def framerateLimit: Int = _framerateLimit

    def framerateLimit_=(limit: Int) =
        _framerateLimit = limit
        sfRenderWindow_setFramerateLimit(renderWindow, limit.toUInt)

    def verticalSync: Boolean = _verticalSync

    def verticalSync_=(enabled: Boolean): Unit =
        _verticalSync = enabled
        sfRenderWindow_setVerticalSyncEnabled(renderWindow, enabled)

object RenderWindow:
    def apply(mode: VideoMode, title: String, style: Window.WindowStyle): RenderWindow =
        RenderWindow(Zone { implicit z => sfRenderWindow_create_fix(mode.bind(z), toCString(title), style.value.toUInt, null) })
