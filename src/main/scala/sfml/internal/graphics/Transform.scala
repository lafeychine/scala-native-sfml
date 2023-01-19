package sfml
package internal
package graphics

import scalanative.unsafe.*

object Transform:
    type sfTransform = CArray[CFloat, Nat.Digit2[Nat._1, Nat._6]]
