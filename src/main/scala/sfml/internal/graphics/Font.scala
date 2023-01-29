package sfml
package internal
package graphics

import scalanative.unsafe.*

import stdlib.String

@link("sfml-graphics")
@extern private[sfml] object Font:
    type sfFont = CArray[Byte, Nat.Digit3[Nat._1, Nat._4, Nat._4]]

    @name("_ZN2sf4FontC2Ev")
    def ctor(self: Ptr[sfFont]): Unit = extern

    @name("_ZN2sf4FontD2Ev")
    def dtor(self: Ptr[sfFont]): Unit = extern

    @name("_ZN2sf4Font12loadFromFileERKNSt7__cxx1112basic_stringIcSt11char_traitsIcESaIcEEE")
    def sfFont_loadFromFile(self: Ptr[sfFont], filename: Ptr[String.stdString]): Type.sfBool = extern
