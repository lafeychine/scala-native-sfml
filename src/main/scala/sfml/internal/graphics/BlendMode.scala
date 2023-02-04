package sfml
package internal
package graphics

import scalanative.unsafe.*

object BlendMode:
    type sfFactor = CInt
    type sfEquation = CInt
    type sfBlendMode = CStruct6[sfFactor, sfFactor, sfEquation, sfFactor, sfFactor, sfEquation]
