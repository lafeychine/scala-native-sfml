package sfml
package window

import scalanative.unsafe.*

import internal.Type.sfBoolToBoolean
import internal.window.Mouse.*
import internal.window.MouseButton.sfMouseButtonType
import internal.system.Vector2.sfVector2i

import graphics.RenderWindow
import system.Vector2i

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

    def position: Vector2i = position(None)

    def position(window: Option[RenderWindow]): Vector2i =
        Zone { implicit z =>
            val vect = alloc[sfVector2i]()

            window match
                case None         => sfMouse_getPosition_fix(null, vect)
                case Some(window) => sfMouse_getPosition_fix(window.bind(z), vect)

            Vector2i(vect._1, vect._2)
        }

    def position_=(position: Vector2i) = setPosition(position, None)

    def setPosition(position: Vector2i, window: Option[RenderWindow]) =
        Zone { implicit z =>
            window match
                case None         => sfMouse_setPosition_fix(position.bind(z), null)
                case Some(window) => sfMouse_setPosition_fix(position.bind(z), window.bind(z))
        }
