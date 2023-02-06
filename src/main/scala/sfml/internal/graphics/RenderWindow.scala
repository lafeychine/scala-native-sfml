package sfml
package internal
package graphics

import scalanative.unsafe.*

import system.{String, Vector2}
import window.{ContextSettings, VideoMode, Window}

@link("sfml-graphics")
@extern private[sfml] object RenderWindow:
    type sfRenderWindow = CStruct2[Window.sfWindow, RenderTarget.sfRenderTarget]

    @name("_ZN2sf12RenderWindowC2ENS_9VideoModeERKNS_6StringEjRKNS_15ContextSettingsE")
    def ctor(self: Ptr[sfRenderWindow], modeHigh: Type.sfSplit[VideoMode.sfVideoMode], modeLow: Type.sfSplit[VideoMode.sfVideoMode], title: Ptr[String.sfString], style: Type.sfUint32, settings: Ptr[ContextSettings.sfContextSettings]): Unit = extern

    @name("_ZN2sf12RenderWindowD2Ev")
    def dtor(self: Ptr[sfRenderWindow]): Unit = extern

    @name("_ZNK2sf12RenderWindow7getSizeEv")
    def sfRenderWindow_getSize(self: Ptr[sfRenderWindow]): Type.sfSplit[Vector2.sfVector2u] = extern
