package sfml
package internal
package window

import scalanative.unsafe.*

@link("sfml-window")
@extern private[this] object externWindow:
    type sfWindow
    type sfWindowFields = CArray[Byte, Nat.Digit2[Nat._4, Nat._8]]

    @name("_ZN2sf6WindowD2Ev")
    def dtor(self: Ptr[sfWindow]): Unit = extern

    @name("_ZN2sf6Window7displayEv")
    def sfWindow_display(self: Ptr[sfWindow]): Unit = extern

    @name("_ZNK2sf6Window6isOpenEv")
    def sfWindow_isOpen(self: Ptr[sfWindow]): Type.sfBool = extern

private[sfml] object Window:

    import scala.language.implicitConversions

    export externWindow.*

    implicit def fieldsToType(fields: Ptr[Window.sfWindowFields]): Ptr[Window.sfWindow] =
        return fields.asInstanceOf[Ptr[Window.sfWindow]]
