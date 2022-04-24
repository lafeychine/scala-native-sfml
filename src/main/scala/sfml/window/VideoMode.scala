package sfml
package window

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.window.VideoMode.*

class VideoMode(val width: Int, val height: Int, val bitsPerPixel: Int) extends SFMLBind[sfVideoMode]:

    private[sfml] def bind(implicit z: Zone) =
        val videoMode = alloc[sfVideoMode]()

        videoMode._1 = width.toUInt
        videoMode._2 = height.toUInt
        videoMode._3 = bitsPerPixel.toUInt
        return videoMode
