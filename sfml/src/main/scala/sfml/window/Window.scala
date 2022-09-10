package sfml
package window

import scalanative.unsafe.*

import internal.Type.sfBoolToBoolean
import internal.window.Event.sfEvent
import internal.window.Window.*

import window.Event

class Window private[sfml] (private[sfml] val window: Ptr[sfWindow]) extends Resource:

    @SuppressWarnings(Array("org.wartremover.contrib.warts.UnsafeInheritance"))
    override def close(): Unit = () // TODO

    final def display(): Unit =
        sfWindow_display(window)

    final def isOpen(): Boolean =
        sfWindow_isOpen(window)

    @SuppressWarnings(Array("org.wartremover.warts.OptionPartial"))
    final def pollEvent(): LazyList[Event] =
        def polling(event: Ptr[sfEvent]): Option[Event] =
            if sfWindow_pollEvent(window, event) then { Event(event) }
            else { None }

        Zone { implicit z =>
            val event = alloc[sfEvent]()

            LazyList.continually(polling(event)).takeWhile(_.isDefined).map(_.get)
        }

object Window:
    enum WindowStyle(val value: Int):
        case None extends WindowStyle(0)
        case Titlebar extends WindowStyle(1 << 0)
        case Resize extends WindowStyle(1 << 1)
        case Close extends WindowStyle(1 << 2)
        case Fullscreen extends WindowStyle(1 << 3)
        case DefaultStyle extends WindowStyle(Titlebar.value | Resize.value | Close.value)
