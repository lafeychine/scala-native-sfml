package sfml
package system

import scalanative.unsafe.*

import internal.system.String.*
import stdlib.String.stringToStdString

object String:
    implicit def stringToSfString(ansiString: java.lang.String)(implicit z: Zone): Ptr[sfString] =
        val utf32Bytes = ansiString.toCharArray.foldLeft(Array[Char]())((x, y) => x :+ y :+ 0.toChar :+ 0.toChar :+ 0.toChar)

        stringToStdString(utf32Bytes.mkString)
