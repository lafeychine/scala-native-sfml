package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.VertexArray.*

object VertexArray:
    extension (vertexArray: Ptr[sfVertexArray])
        private[sfml] def close(): Unit =
            Resource.close(vertexArray._2)
