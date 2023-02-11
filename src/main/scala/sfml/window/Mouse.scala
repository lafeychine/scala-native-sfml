package sfml
package window

import scalanative.unsafe.*

import internal.Type.sfBoolToBoolean
import internal.window.Mouse.*

import graphics.RenderWindow
import system.Vector2

object Mouse:
    enum Button:
        def isPressed(): Boolean =
            sfMouse_isButtonPressed(ordinal)

        case Left
        case Right
        case Middle
        case XButton1
        case XButton2

    enum Wheel:
        case VerticalWheel
        case HorizontalWheel

    def position: Vector2[Int] =
        Vector2.toVector2Int(sfMouse_getPosition())()

    def position(relativeTo: Window): Vector2[Int] =
        Zone { implicit z => Vector2.toVector2Int(sfMouse_getPosition(relativeTo.toNativeWindow))() }

    def position_=(position: Vector2[Int]): Unit =
        Zone { implicit z => sfMouse_setPosition(position.toNativeVector2) }

    def position_=(position: Vector2[Int])(relativeTo: Window): Unit =
        Zone { implicit z => sfMouse_setPosition(position.toNativeVector2, relativeTo.toNativeWindow) }
