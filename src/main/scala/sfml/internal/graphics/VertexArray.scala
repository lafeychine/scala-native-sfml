package sfml
package internal
package graphics

import scalanative.unsafe.*

@link("sfml-graphics")
@extern private[sfml] object VertexArray:
    type sfVertexArray = CStruct3[
        CArray[Byte, Nat._8],
        Ptr[Byte],
        CArray[Byte, Nat.Digit2[Nat._2, Nat._4]]
    ]
