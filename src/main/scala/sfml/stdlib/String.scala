package sfml
package stdlib

import scala.language.implicitConversions

import scalanative.libc.stdlib.malloc
import scalanative.libc.string.strcpy
import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.stdlib.String.*

class String private[sfml] (private[sfml] val string: Ptr[stdString]) extends Resource:

    def this(ansiString: java.lang.String) =
        this(malloc(sizeof[stdString] + ansiString.length().toULong + 1.toULong).asInstanceOf[Ptr[stdString]])

        string._1 = (string + 1).asInstanceOf[CString]
        string._2 = ansiString.length().toULong
        string._3 = ansiString.length().toULong

        Zone { implicit z =>
            strcpy(string._1, toCString(ansiString))
        }

    final override def close(): Unit =
        Resource.close(string)

object String:
    implicit private[sfml] def stringToStdString(ansiString: java.lang.String)(implicit z: Zone): Ptr[stdString] =
        val string = alloc[stdString]()
        val payload = alloc[Byte](ansiString.length().toULong + 1.toULong).asInstanceOf[CString]

        string._1 = payload
        string._2 = ansiString.length().toULong
        string._3 = ansiString.length().toULong

        strcpy(payload, toCString(ansiString))

        string
