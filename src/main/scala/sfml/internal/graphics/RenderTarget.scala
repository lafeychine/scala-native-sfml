package sfml
package internal
package graphics

import scalanative.unsafe.*

import system.String

@link("sfml-graphics")
@extern private[sfml] object RenderTarget:
    type sfRenderTarget = CArray[Byte, Nat.Digit3[Nat._4, Nat._8, Nat._0]]

    @name("_ZN2sf12RenderTarget5clearERKNS_5ColorE")
    def sfRenderTarget_clear(renderWindow: Ptr[sfRenderTarget], color: Ptr[Color.sfColor]): Unit = extern
