package sfml
package internal
package window

import scalanative.unsafe.*

@link("sfml-window")
@extern private[sfml] object Window:
    type sfWindow = CArray[Byte, Nat.Digit2[Nat._4, Nat._8]]

    @name("_ZN2sf6WindowD2Ev")
    def dtor(self: Ptr[sfWindow]): Unit = extern

    @name("_ZN2sf6Window5closeEv")
    def sfWindow_closeWindow(self: Ptr[sfWindow]): Unit = extern

    @name("_ZN2sf6Window7displayEv")
    def sfWindow_display(self: Ptr[sfWindow]): Unit = extern

    @name("_ZNK2sf6Window6isOpenEv")
    def sfWindow_isOpen(self: Ptr[sfWindow]): Type.sfBool = extern

    @name("_ZN2sf6Window9pollEventERNS_5EventE")
    def sfWindow_pollEvent(self: Ptr[sfWindow], event: Ptr[Event.sfEvent]): Type.sfBool = extern
