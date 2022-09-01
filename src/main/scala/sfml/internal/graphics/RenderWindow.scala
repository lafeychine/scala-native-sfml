package sfml
package internal
package graphics

import scalanative.unsafe.*

import system.String
import window.{ContextSettings, VideoMode, Window}

@link("sfml-graphics")
@extern private[this] object externRenderWindow:
    type sfRenderWindow
    type sfRenderWindowFields = CStruct2[Window.sfWindowFields, RenderTarget.sfRenderTargetFields]

    @name("_ZN2sf12RenderWindowC2ENS_9VideoModeERKNS_6StringEjRKNS_15ContextSettingsE")
    def ctor(self: Ptr[sfRenderWindow], modeHigh: Type.sfSplit[VideoMode.sfVideoMode], modeLow: Type.sfSplit[VideoMode.sfVideoMode], title: Ptr[String.sfString], style: Type.sfUint32, settings: Ptr[ContextSettings.sfContextSettings]): Unit = extern

    @name("_ZN2sf12RenderWindowD2Ev")
    def dtor(self: Ptr[sfRenderWindow]): Unit = extern

private[sfml] object RenderWindow:

    import scala.language.implicitConversions

    export externRenderWindow.*

    implicit def fieldsToType(fields: Ptr[RenderWindow.sfRenderWindowFields]): Ptr[RenderWindow.sfRenderWindow] & Ptr[Window.sfWindow] & Ptr[RenderTarget.sfRenderTarget] =
        return fields.asInstanceOf[
            Ptr[RenderWindow.sfRenderWindow] & Ptr[Window.sfWindow] & Ptr[RenderTarget.sfRenderTarget]
        ]
