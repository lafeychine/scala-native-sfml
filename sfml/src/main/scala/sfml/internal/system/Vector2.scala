package sfml
package internal
package system

import scalanative.unsafe.*

object Vector2:
    type sfVector2f = CStruct2[CFloat, CFloat]
    type sfVector2i = CStruct2[CInt, CInt]
    type sfVector2u = CStruct2[CUnsignedInt, CUnsignedInt]
