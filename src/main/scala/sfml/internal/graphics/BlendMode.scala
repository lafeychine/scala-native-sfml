package sfml
package internal
package graphics

import scalanative.unsafe.*

object BlendMode:
    type sfFactor = CInt
    type sfEquation = CInt
    type sfBlendMode = CStruct6[sfFactor, sfFactor, sfEquation, sfFactor, sfFactor, sfEquation]

    enum Factor:
        case Zero
        case One
        case SrcColor
        case OneMinusSrcColor
        case DstColor
        case OneMinusDstColor
        case SrcAlpha
        case OneMinusSrcAlpha
        case DstAlpha

    enum Equation:
        case Add
        case Subtract
        case ReverseSubtract
