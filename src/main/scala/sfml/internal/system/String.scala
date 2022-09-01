package sfml
package internal
package system

import scalanative.unsafe.*

import stdlib.Locale.stdLocale

@link("sfml-system")
@link("sfml-window")
@extern private[this] object externString:
    type sfString
    type sfStringFields = CArray[Byte, Nat.Digit2[Nat._3, Nat._2]]

    @name("_ZN2sf6StringC2EPKcRKSt6locale")
    def ctor(self: Ptr[sfString], str: CString, locale: Ptr[stdLocale]): Unit = extern

    @name("_ZN2sf6StringD2Ev")
    def dtor(self: Ptr[sfString]): Unit = extern

private[sfml] object String:

    import scala.language.implicitConversions

    export externString.*

    implicit def fieldsToType(fields: Ptr[String.sfStringFields]): Ptr[String.sfString] =
        return fields.asInstanceOf[Ptr[String.sfString]]
