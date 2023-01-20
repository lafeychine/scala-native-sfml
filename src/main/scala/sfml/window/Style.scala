package sfml
package window

enum Style(val value: Int):
    case None extends Style(0)
    case Titlebar extends Style(1 << 0)
    case Resize extends Style(1 << 1)
    case Close extends Style(1 << 2)
    case Fullscreen extends Style(1 << 3)
    case Default extends Style(Titlebar.value | Resize.value | Close.value)
