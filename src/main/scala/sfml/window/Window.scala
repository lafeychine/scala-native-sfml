package sfml
package window

import scalanative.unsafe.*

import internal.Type.sfBoolToBoolean
import internal.window.Event.sfEvent
import internal.window.Window.*

import window.Event

class Window private[sfml] (private[sfml] val window: Ptr[sfWindow]) extends Resource:

    def close(): Unit = () // TODO

    def display(): Unit =
        sfWindow_display(window)

    def isOpen(): Boolean =
        sfWindow_isOpen(window)

    def pollEvent(): LazyList[Event] =
        def polling(event: Ptr[sfEvent]): Event =
            if sfWindow_pollEvent(window, event) then {
                return Event(event).getOrElse(polling(event))
            }
            return null

        Zone { implicit z =>
            val event = alloc[sfEvent]()

            return LazyList.continually(polling(event)).takeWhile(_ != null)
        }

object Window:
    enum WindowStyle(val value: Int):
        case None extends WindowStyle(0)
        case Titlebar extends WindowStyle(1 << 0)
        case Resize extends WindowStyle(1 << 1)
        case Close extends WindowStyle(1 << 2)
        case Fullscreen extends WindowStyle(1 << 3)
        case DefaultStyle extends WindowStyle(Titlebar.value | Resize.value | Close.value)
