package sfml
package internal
package graphics

import scalanative.unsafe.*

object Color:
    type sfColor = CStruct4[Type.sfUint8, Type.sfUint8, Type.sfUint8, Type.sfUint8]
