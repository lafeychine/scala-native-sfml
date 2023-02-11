package sfml
package graphics

import scalanative.unsafe.*

import internal.Type.{booleanToSfBool, sfBoolToBoolean}
import internal.graphics.Texture.*

import graphics.Rect
import stdlib.String.toNativeStdString

class Texture private[sfml] (private val texture: Resource[sfTexture]) extends AutoCloseable:

    private[sfml] inline def toNativeTexture: Ptr[sfTexture] = texture.ptr

    override def close(): Unit =
        Texture.close(toNativeTexture)()

    def this() =
        this(Resource { (r: Ptr[sfTexture]) => ctor(r) })

    final def loadFromFile(filename: String, area: Rect[Int] = Rect()): Boolean =
        Zone { implicit z => sfTexture_loadFromFile(toNativeTexture, filename.toNativeStdString, area.toNativeRect) }

    final def smooth: Boolean =
        sfTexture_isSmooth(toNativeTexture)

    final def smooth_=(smooth: Boolean) =
        sfTexture_setSmooth(toNativeTexture, smooth)

    final def repeated: Boolean =
        sfTexture_isRepeated(toNativeTexture)

    final def repeated_=(repeated: Boolean) =
        sfTexture_setRepeated(toNativeTexture, repeated)

object Texture:
    extension (texture: Ptr[sfTexture])
        private[sfml] def close(): Unit =
            dtor(texture)
