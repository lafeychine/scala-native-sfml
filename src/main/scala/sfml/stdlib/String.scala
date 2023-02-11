package sfml
package stdlib

import scalanative.libc.stdlib.free
import scalanative.libc.string.memcpy
import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.stdlib.String.*

private[sfml] object String:

    extension (string: Ptr[stdString])
        private[sfml] def close(): Unit =
            free(string._1)

    private inline def convert(ansiString: java.lang.String, charSize: Int)(using Zone): Ptr[stdString] =
        val string = alloc[stdString]()
        val length = ansiString.length
        val payload = alloc[Byte](length.toULong + charSize.toULong).asInstanceOf[CString]

        string._1 = payload
        string._2 = (length / charSize).toULong
        string._3 = (length / charSize).toULong

        memcpy(payload, toCString(ansiString), length.toULong)

        string

    extension (string: java.lang.String)
        private[sfml] def toNativeStdString(using Zone): Ptr[stdString] =
            convert(string, 1)

        private[sfml] def toNativeWideStdString(using Zone): Ptr[stdString] =
            convert(string, 4)
