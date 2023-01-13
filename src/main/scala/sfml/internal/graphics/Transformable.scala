package sfml
package internal
package graphics

import scalanative.unsafe.*

@link("sfml-graphics")
@extern private[sfml] object Transformable:
    type sfTransformable = CArray[Byte, Nat.Digit3[Nat._1, Nat._7, Nat._6]]

    @name("_ZN2sf13TransformableC2Ev")
    def ctor(self: Ptr[sfTransformable]): Unit = extern

    @name("_ZN2sf13TransformableD2Ev")
    def dtor(self: Ptr[sfTransformable]): Unit = extern
