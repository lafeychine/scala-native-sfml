package sfml
package stdlib

import scala.language.implicitConversions

import scalanative.libc.stdlib.malloc
import scalanative.libc.string.memcpy
import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.stdlib.String.*

private[sfml] object String:
    extension (string: Ptr[stdString])
        def close(): Unit =
            Resource.close(string._1)

    private inline def convert(ansiString: java.lang.String, charSize: Int)(implicit z: Zone): Ptr[stdString] =
        val string = alloc[stdString]()
        val length = ansiString.length
        val payload = alloc[Byte](length.toULong + charSize.toULong).asInstanceOf[CString]

        string._1 = payload
        string._2 = (length / charSize).toULong
        string._3 = (length / charSize).toULong

        memcpy(payload, toCString(ansiString), length.toULong)

        string

    implicit def stringToStdString(ansiString: java.lang.String)(implicit z: Zone): Ptr[stdString] =
        convert(ansiString, 1)

    implicit def wideStringToStdString(ansiString: java.lang.String)(implicit z: Zone): Ptr[stdString] =
        convert(ansiString, 4)
