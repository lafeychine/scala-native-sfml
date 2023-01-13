package sfml
package internal
package graphics

import scalanative.unsafe.*

object Rect:
    type sfIntRect = CStruct4[CInt, CInt, CInt, CInt]
