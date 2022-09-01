package sfml
package window

import scalanative.unsafe.*

import internal.Type.sfBoolToBoolean
import internal.window.Window.*

class Window private[sfml] (self: Ptr[sfWindowFields])
        extends Resource[Ptr[sfWindow]]:

    private[sfml] def bind = self

    def close(): Unit =
      Resource.close(dtor)(bind)

    def display(): Unit =
        sfWindow_display(bind)

    def isOpen(): Boolean =
        sfWindow_isOpen(bind)


object Window:
    enum WindowStyle(val value: Int):
        case None extends WindowStyle(0)
        case Titlebar extends WindowStyle(1 << 0)
        case Resize extends WindowStyle(1 << 1)
        case Close extends WindowStyle(1 << 2)
        case Fullscreen extends WindowStyle(1 << 3)
        case DefaultStyle extends WindowStyle(Titlebar.value | Resize.value | Close.value)
