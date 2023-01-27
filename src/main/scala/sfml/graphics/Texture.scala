package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.Texture.*

import graphics.Rect
import stdlib.String.stringToStdString

class Texture private[sfml] (private[sfml] val texture: Ptr[sfTexture]) extends Resource:

    override def close(): Unit =
        Resource.close(dtor)(texture)

    def this() =
        this(Resource { (r: Ptr[sfTexture]) =>
            ctor(r)
        })

    final def loadFromFile(filename: String, area: Rect[Int] = Rect()): Boolean =
        Zone { implicit z =>
            sfTexture_loadFromFile(texture, filename, area.intRect)
        }
