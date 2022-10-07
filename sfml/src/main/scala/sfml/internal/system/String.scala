package sfml
package internal
package system

import scalanative.unsafe.*

import stdlib.Locale.stdLocale

@link("sfml-system")
@extern private[sfml] object String:
    type sfString = CArray[Byte, Nat.Digit2[Nat._3, Nat._2]]

    @name("_ZN2sf6StringC2EPKcRKSt6locale")
    def ctor(self: Ptr[sfString], str: CString, locale: Ptr[stdLocale]): Unit = extern

    @name("_ZdlPv")
    def dtor(self: Ptr[Byte]): Unit = extern
