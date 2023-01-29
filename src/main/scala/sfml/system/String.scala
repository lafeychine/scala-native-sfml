package sfml
package system

import scala.language.implicitConversions
import scalanative.unsafe.*

import internal.system.String.*
import stdlib.String.wideStringToStdString

object String:
    private[sfml] implicit def stringToSfString(ansiString: java.lang.String)(implicit z: Zone): Ptr[sfString] =
        val utf32Bytes = ansiString.toCharArray.foldLeft(Array[Char]())((x, y) => x :+ y :+ 0.toChar :+ 0.toChar :+ 0.toChar)

        wideStringToStdString(utf32Bytes.mkString)
