package sfml
package internal
package graphics

import scalanative.unsafe.*

import system.Vector2

@link("sfml-graphics")
@extern private[sfml] object View:
    type sfView = CArray[Byte, Nat.Digit3[Nat._1, Nat._6, Nat._8]]

    @name("_ZN2sf4ViewC2Ev")
    def ctor(self: Ptr[sfView]): Unit = extern

    /* Getter / Setter */

    @name("_ZNK2sf4View12getTransformEv")
    def sfView_getTransform(self: Ptr[sfView]): Transform.sfTransform = extern

    @name("_ZNK2sf4View19getInverseTransformEv")
    def sfView_getInverseTransform(self: Ptr[sfView]): Transform.sfTransform = extern

    @name("_ZNK2sf4View11getViewportEv")
    def sfView_getViewport(self: Ptr[sfView]): Ptr[Rect.sfFloatRect] = extern
