package sfml
package internal
package window

import scalanative.unsafe.*

import graphics.RenderWindow
import system.Vector2

@link("sfml-window")
@extern object Mouse:
    def sfMouse_isButtonPressed(button: MouseButton.sfMouseButton): Type.sfBool = extern
    def sfMouse_getPosition_fix(relativeTo: Ptr[RenderWindow.sfRenderWindow], position: Ptr[Vector2.sfVector2i]): Unit = extern
    def sfMouse_setPosition_fix(position: Ptr[Vector2.sfVector2i], relativeTo: Ptr[RenderWindow.sfRenderWindow]): Unit = extern
