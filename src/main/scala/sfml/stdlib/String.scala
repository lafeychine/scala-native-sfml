package sfml
package stdlib

import scalanative.libc.stdlib.malloc
import scalanative.libc.string.strcpy
import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.stdlib.String.*

class String private[sfml] (private[sfml] val string: Ptr[stdString]) extends Resource:

    def this(ansiString: java.lang.String) =
        this(malloc(sizeof[stdString] + ansiString.length().toULong).asInstanceOf[Ptr[stdString]])

        string._1 = (string + 1).asInstanceOf[Ptr[CString]]
        string._2 = ansiString.length().toULong
        string._3 = ansiString.length().toULong

        Zone { implicit z =>
            strcpy((string + 1).asInstanceOf[CString], toCString(ansiString))
        }

    final override def close(): Unit =
        Resource.close(string)
