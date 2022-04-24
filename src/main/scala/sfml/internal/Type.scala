package sfml
package internal

import scalanative.unsafe.*

object Type:
    type sfBool = CInt
    type sfInt8 = CChar
    type sfUint8 = CUnsignedChar
    type sfInt16 = CShort
    type sfUint16 = CUnsignedShort
    type sfInt32 = CInt
    type sfUint32 = CUnsignedInt
    type sfInt64 = CLongLong
    type sfUint64 = CUnsignedLongLong

    val sfFalse: sfBool = 0
    val sfTrue: sfBool = 1

    implicit def booleanToSfBool(bool: Boolean): sfBool =
        if bool then sfTrue else sfFalse

    implicit def sfBoolToBoolean(bool: sfBool): Boolean =
        bool == sfTrue
