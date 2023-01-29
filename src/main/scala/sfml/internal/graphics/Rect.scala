package sfml
package internal
package graphics

import scalanative.unsafe.*

object Rect:
    type sfFloatRect = CStruct4[CFloat, CFloat, CFloat, CFloat]
    type sfIntRect = CStruct4[CInt, CInt, CInt, CInt]
