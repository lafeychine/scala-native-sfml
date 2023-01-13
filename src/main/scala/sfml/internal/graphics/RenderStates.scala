package sfml
package internal
package graphics

import scalanative.unsafe.*

@link("sfml-graphics")
@extern object RenderStates:
    type sfRenderStates = CArray[Byte, Nat.Digit3[Nat._1, Nat._0, Nat._4]]

    @name("_ZN2sf12RenderStatesC2Ev")
    def ctor(self: Ptr[sfRenderStates]): Unit = extern

    @name("_ZN2sf12RenderStates7DefaultE")
    val sfRenderStates_Default: Ptr[sfRenderStates] = extern
