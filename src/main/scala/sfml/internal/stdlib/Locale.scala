package sfml
package internal
package stdlib

import scalanative.unsafe.*

@extern private[this] object externLocale:
    type stdLocale
    type stdLocaleFields = CArray[Byte, Nat._8]

    @name("_ZNSt6localeC2Ev")
    def ctor(self: Ptr[stdLocale]): Unit = extern

    @name("_ZNSt6localeD2Ev")
    def dtor(self: Ptr[stdLocale]): Unit = extern

private[sfml] object Locale:

    import scala.language.implicitConversions

    export externLocale.*

    implicit def fieldsToType(fields: Ptr[Locale.stdLocaleFields]): Ptr[Locale.stdLocale] =
        return fields.asInstanceOf[Ptr[Locale.stdLocale]]
