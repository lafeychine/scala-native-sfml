package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.Texture.*

import graphics.IntRect
import stdlib.String

class Texture private[sfml] (private[sfml] val texture: Ptr[sfTexture]) extends Resource:

    def this() =
        this(Resource { (r: Ptr[sfTexture]) =>
            ctor(r)
        })

    override def close(): Unit =
        Resource.close(dtor)(texture)

    final def loadFromFile(filename: String, area: IntRect): Boolean =
        Zone { implicit z =>
            sfTexture_loadFromFile(texture, filename.string, area.intRect)
        }
