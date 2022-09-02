package sfml
package internal
package stdlib

import scalanative.unsafe.*

@extern private[sfml] object Locale:
    type stdLocale = CArray[Byte, Nat._8]

    @name("_ZNSt6localeC2Ev")
    def ctor(self: Ptr[stdLocale]): Unit = extern

    @name("_ZNSt6localeD2Ev")
    def dtor(self: Ptr[stdLocale]): Unit = extern
