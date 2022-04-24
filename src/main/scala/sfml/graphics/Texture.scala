package sfml
package graphics

import scalanative.unsafe.*

import internal.Type.{booleanToSfBool, sfBoolToBoolean}
import internal.graphics.Texture.*

case class Texture private[sfml] (texture: Ptr[sfTexture]) extends SFMLBind[sfTexture] with AutoCloseable:

    private[sfml] def bind(implicit z: Zone) = texture

    def close(): Unit =
        sfTexture_destroy(texture)

    def repeated: Boolean =
        sfTexture_isRepeated(texture)

    def repeated_=(repeated: Boolean) =
        sfTexture_setRepeated(texture, repeated)

    def smooth: Boolean =
        sfTexture_isSmooth(texture)

    def smooth_=(repeated: Boolean) =
        sfTexture_setSmooth(texture, repeated)

object Texture:
    def apply(filename: String, area: IntRect = IntRect()): Texture =
        Texture(Zone { implicit z => sfTexture_createFromFile(toCString(filename), area.bind(z)) })
