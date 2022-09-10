package sfml
package window

import scalanative.unsafe.*

import internal.Type.sfBoolToBoolean
import internal.window.Mouse.*
import internal.window.MouseButton.sfMouseButtonType
import internal.system.Vector2.sfVector2i

import graphics.RenderWindow

object Mouse:
    enum Button(button: sfMouseButtonType):
        def isPressed(): Boolean =
            sfMouse_isButtonPressed(button.ordinal)

        case Left extends Button(sfMouseButtonType.sfMouseLeft)
        case Right extends Button(sfMouseButtonType.sfMouseRight)
        case Middle extends Button(sfMouseButtonType.sfMouseMiddle)
        case XButton1 extends Button(sfMouseButtonType.sfMouseXButton1)
        case XButton2 extends Button(sfMouseButtonType.sfMouseXButton2)

    enum Wheel:
        case VerticalWheel
        case HorizontalWheel
