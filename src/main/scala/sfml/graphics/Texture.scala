package sfml
package graphics

import scalanative.unsafe.*

import internal.Type.{booleanToSfBool, sfBoolToBoolean}
import internal.graphics.Texture.*

import graphics.Rect
import stdlib.String.toNativeStdString

class Texture private[sfml] (private val texture: Ptr[sfTexture]) extends Resource:

    private[sfml] inline def toNativeTexture: Ptr[sfTexture] = texture

    override def close(): Unit =
        Texture.close(texture)()
        Resource.close(texture)

    def this() =
        this(Resource { (r: Ptr[sfTexture]) => ctor(r) })

    final def loadFromFile(filename: String, area: Rect[Int] = Rect()): Boolean =
        Zone { implicit z => sfTexture_loadFromFile(texture, filename.toNativeStdString, area.toNativeRect) }

    final def smooth: Boolean =
        sfTexture_isSmooth(texture)

    final def smooth_=(smooth: Boolean) =
        sfTexture_setSmooth(texture, smooth)

    final def repeated: Boolean =
        sfTexture_isRepeated(texture)

    final def repeated_=(repeated: Boolean) =
        sfTexture_setRepeated(texture, repeated)

object Texture:
    extension (texture: Ptr[sfTexture])
        private[sfml] def close(): Unit =
            dtor(texture)
