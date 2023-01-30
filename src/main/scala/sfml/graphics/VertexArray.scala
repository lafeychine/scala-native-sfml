package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.VertexArray.*

private[sfml] object VertexArray:
    private[sfml] def close(vertexArray: Ptr[sfVertexArray]): Unit =
        Resource.close(vertexArray._2)
        Resource.close(vertexArray)
