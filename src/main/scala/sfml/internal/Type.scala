package sfml
package internal

import scala.language.implicitConversions

import scalanative.unsafe.*

private[sfml] object Type:
    type sfBool = CChar
    type sfInt8 = CChar
    type sfUint8 = CUnsignedChar
    type sfInt16 = CShort
    type sfUint16 = CUnsignedShort
    type sfInt32 = CInt
    type sfUint32 = CUnsignedInt
    type sfInt64 = CLongLong
    type sfUint64 = CUnsignedLongLong
    type sfSplit[T] = CUnsignedLongLong

    val sfFalse: sfBool = 0
    val sfTrue: sfBool = 1

    implicit def booleanToSfBool(bool: Boolean): sfBool =
        if bool then sfTrue else sfFalse

    implicit def sfBoolToBoolean(bool: sfBool): Boolean =
        bool != sfFalse

    inline def split[T: Tag](value: Ptr[T]): IndexedSeq[sfSplit[T]] =
        for i <- 0 to sizeof[T].toInt by sizeof[sfSplit[T]].toInt
            yield !(((value.asInstanceOf[Ptr[Byte]]) + i).asInstanceOf[Ptr[sfSplit[T]]])
