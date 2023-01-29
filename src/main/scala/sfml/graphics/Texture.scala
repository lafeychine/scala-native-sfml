package sfml
package graphics

import scalanative.unsafe.*

import internal.Type.{booleanToSfBool, sfBoolToBoolean}
import internal.graphics.Texture.*

import graphics.Rect
import stdlib.String.stringToStdString

class Texture private[sfml] (private[sfml] val texture: Ptr[sfTexture]) extends Resource:

    override def close(): Unit =
        Resource.close(dtor)(texture)

    def this() =
        this(Resource { (r: Ptr[sfTexture]) => ctor(r) })

    final def loadFromFile(filename: String, area: Rect[Int] = Rect()): Boolean =
        Zone { implicit z => sfTexture_loadFromFile(texture, filename, area.intRect) }

    final def smooth: Boolean =
        sfTexture_isSmooth(texture)

    final def smooth_=(smooth: Boolean) =
        sfTexture_setSmooth(texture, smooth)

    final def repeated: Boolean =
        sfTexture_isRepeated(texture)

    final def repeated_=(repeated: Boolean) =
        sfTexture_setRepeated(texture, smooth)
