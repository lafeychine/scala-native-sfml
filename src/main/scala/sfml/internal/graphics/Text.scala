package sfml
package internal
package graphics

import scalanative.unsafe.*

import system.Vector2

@link("sfml-graphics")
@extern object Text:
    type sfText

    def sfText_create(): Ptr[sfText] = extern
    def sfText_destroy(text: Ptr[sfText]): Unit = extern

    def sfText_getGlobalBounds_fix(text: Ptr[sfText], rect: Ptr[Rect.sfFloatRect]): Unit = extern

    def sfText_getCharacterSize(text: Ptr[sfText]): CUnsignedInt = extern
    def sfText_setCharacterSize(text: Ptr[sfText], size: CUnsignedInt): Unit = extern

    def sfText_getColor_fix(text: Ptr[sfText], color: Ptr[Color.sfColor]): Unit = extern
    def sfText_setColor_fix(text: Ptr[sfText], color: Ptr[Color.sfColor]): Unit = extern

    def sfText_getFont(text: Ptr[sfText]): Ptr[Font.sfFont] = extern
    def sfText_setFont(text: Ptr[sfText], font: Ptr[Font.sfFont]): Unit = extern

    def sfText_getPosition_fix(text: Ptr[sfText], position: Ptr[Vector2.sfVector2f]): Unit = extern
    def sfText_setPosition_fix(text: Ptr[sfText], position: Ptr[Vector2.sfVector2f]): Unit = extern

    def sfText_getString(text: Ptr[sfText]): CString = extern
    def sfText_setString(text: Ptr[sfText], string: CString): Unit = extern
