package sfml
package window

import scalanative.unsafe.*
import scalanative.unsigned.{UnsignedRichInt, UnsignedRichLong}

import internal.Type
import internal.window.VideoMode.*

class VideoMode(val width: Int, val height: Int, val bitsPerPixel: Int = 32):

    private[sfml] final def videoMode(implicit z: Zone): Ptr[sfVideoMode] =
        val videoMode = alloc[sfVideoMode]()

        videoMode._1 = width.toUInt
        videoMode._2 = height.toUInt
        videoMode._3 = bitsPerPixel.toUInt
        videoMode
