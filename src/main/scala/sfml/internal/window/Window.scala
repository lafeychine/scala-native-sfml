package sfml
package internal
package window

import scalanative.unsafe.*

import system.String

@link("sfml-window")
@extern private[sfml] object Window:
    type sfWindow = CArray[Byte, Nat.Digit2[Nat._4, Nat._8]]

    @name("_ZN2sf6WindowC2ENS_9VideoModeERKNS_6StringEjRKNS_15ContextSettingsE")
    def ctor(self: Ptr[sfWindow], modeHigh: Type.sfSplit[VideoMode.sfVideoMode], modeLow: Type.sfSplit[VideoMode.sfVideoMode], title: Ptr[String.sfString], style: Type.sfUint32, settings: Ptr[ContextSettings.sfContextSettings]): Unit = extern

    @name("_ZN2sf6WindowD2Ev")
    def dtor(self: Ptr[sfWindow]): Unit = extern

    @name("_ZN2sf6Window5closeEv")
    def sfWindow_close(self: Ptr[sfWindow]): Unit = extern

    @name("_ZN2sf6Window7displayEv")
    def sfWindow_display(self: Ptr[sfWindow]): Unit = extern

    @name("_ZN2sf6Window17setFramerateLimitEj")
    def sfWindow_setFramerateLimit(self: Ptr[sfWindow], limit: CUnsignedInt): Unit = extern

    @name("_ZNK2sf6Window6isOpenEv")
    def sfWindow_isOpen(self: Ptr[sfWindow]): Type.sfBool = extern

    @name("_ZN2sf6Window9pollEventERNS_5EventE")
    def sfWindow_pollEvent(self: Ptr[sfWindow], event: Ptr[Event.sfEvent]): Type.sfBool = extern

    @name("_ZN2sf6Window22setVerticalSyncEnabledEb")
    def sfWindow_setVerticalSyncEnabled(self: Ptr[sfWindow], enabled: Type.sfBool): Unit = extern
