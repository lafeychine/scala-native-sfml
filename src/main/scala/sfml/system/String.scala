package sfml
package system

import scala.language.implicitConversions
import scalanative.unsafe.*

import internal.system.String.*

private[sfml] object String:
    extension (string: Ptr[sfString])
        def close(): Unit =
            Resource.close(string._1)

    implicit def stringToSfString(ansiString: java.lang.String)(using Zone): Ptr[sfString] =
        val utf32Bytes = ansiString.toCharArray.foldLeft(Array[Char]())((x, y) => x :+ y :+ 0.toChar :+ 0.toChar :+ 0.toChar)

        stdlib.String.wideStringToStdString(utf32Bytes.mkString)
