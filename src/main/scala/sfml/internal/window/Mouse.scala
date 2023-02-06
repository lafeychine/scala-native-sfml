package sfml
package internal
package window

import scalanative.unsafe.*

import graphics.RenderWindow
import system.Vector2

@link("sfml-window")
@extern object Mouse:
    @name("_ZN2sf5Mouse15isButtonPressedENS0_6ButtonE")
    def sfMouse_isButtonPressed(button: CInt): Type.sfBool = extern

    @name("_ZN2sf5Mouse11getPositionEv")
    def sfMouse_getPosition(): Type.sfSplit[Vector2.sfVector2i] = extern

    @name("_ZN2sf5Mouse11getPositionERKNS_6WindowE")
    def sfMouse_getPosition(relativeTo: Ptr[Window.sfWindow]): Type.sfSplit[Vector2.sfVector2i] = extern

    @name("_ZN2sf5Mouse11setPositionERKNS_7Vector2IiEE")
    def sfMouse_setPosition(position: Vector2.sfVector2i): Unit = extern

    @name("_ZN2sf5Mouse11setPositionERKNS_7Vector2IiEERKNS_6WindowE")
    def sfMouse_setPosition(position: Vector2.sfVector2i, relativeTo: Ptr[Window.sfWindow]): Unit = extern
