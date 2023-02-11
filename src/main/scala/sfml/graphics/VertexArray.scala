package sfml
package graphics

import scalanative.libc.stdlib.free
import scalanative.unsafe.*

import internal.graphics.VertexArray.*

object VertexArray:
    extension (vertexArray: Ptr[sfVertexArray])
        private[sfml] def close(): Unit =
            free(vertexArray._2)
