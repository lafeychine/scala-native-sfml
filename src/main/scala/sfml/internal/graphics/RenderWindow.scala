package sfml
package internal
package graphics

import scalanative.unsafe.*

import system.Vector2
import window.{Event, VideoMode, Window}

@link("sfml-graphics")
@extern object RenderWindow:
    type sfRenderWindow

    def sfRenderWindow_create_fix(mode: Ptr[VideoMode.sfVideoMode], title: CString, style: Type.sfUint32, settings: Ptr[Window.sfContextSettings]): Ptr[sfRenderWindow] = extern
    def sfRenderWindow_close(renderWindow: Ptr[sfRenderWindow]): Unit = extern
    def sfRenderWindow_clear_fix(renderWindow: Ptr[sfRenderWindow], color: Ptr[Color.sfColor]): Unit = extern
    def sfRenderWindow_destroy(renderWindow: Ptr[sfRenderWindow]): Unit = extern
    def sfRenderWindow_display(renderWindow: Ptr[sfRenderWindow]): Unit = extern
    def sfRenderWindow_drawSprite(renderWindow: Ptr[sfRenderWindow], sprite: Ptr[Sprite.sfSprite], states: Ptr[RenderStates.sfRenderStates]): Unit = extern
    def sfRenderWindow_drawText(renderWindow: Ptr[sfRenderWindow], text: Ptr[Text.sfText], states: Ptr[RenderStates.sfRenderStates]): Unit = extern
    def sfRenderWindow_isOpen(renderWindow: Ptr[sfRenderWindow]): Type.sfBool = extern
    def sfRenderWindow_pollEvent(renderWindow: Ptr[sfRenderWindow], event: Ptr[Event.sfEvent]): Type.sfBool = extern
    def sfRenderWindow_setFramerateLimit(renderWindow: Ptr[sfRenderWindow], limit: CUnsignedInt): Unit = extern
    def sfRenderWindow_setMouseCursorVisible(renderWindow: Ptr[sfRenderWindow], show: Type.sfBool): Unit = extern
    def sfRenderWindow_setSize_fix(sfRenderWindow: Ptr[sfRenderWindow], size: Ptr[Vector2.sfVector2u]): Unit = extern
    def sfRenderWindow_setVerticalSyncEnabled(renderWindow: Ptr[sfRenderWindow], enabled: Type.sfBool): Unit = extern
