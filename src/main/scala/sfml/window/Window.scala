package sfml
package window

object Window:
    enum WindowStyle(val value: Int):
        case None extends WindowStyle(0)
        case Titlebar extends WindowStyle(1 << 0)
        case Resize extends WindowStyle(1 << 1)
        case Close extends WindowStyle(1 << 2)
        case Fullscreen extends WindowStyle(1 << 3)
        case DefaultStyle extends WindowStyle(Titlebar.value | Resize.value | Close.value)
