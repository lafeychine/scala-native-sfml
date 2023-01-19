package sfml
package stdlib

import scala.language.implicitConversions

import scalanative.libc.stdlib.malloc
import scalanative.libc.string.memcpy
import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.stdlib.String.*

object String:
    implicit private[sfml] def stringToStdString(ansiString: java.lang.String)(implicit z: Zone): Ptr[stdString] =
        val string = alloc[stdString]()
        val length = ansiString.length
        val payload = alloc[Byte](length.toULong + 1.toULong).asInstanceOf[CString]

        string._1 = payload
        string._2 = ansiString.length().toULong
        string._3 = ansiString.length().toULong

        memcpy(payload, toCString(ansiString), length.toULong)

        string
