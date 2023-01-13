package sfml
package internal
package graphics

import scalanative.unsafe.*

@link("sfml-graphics")
@extern private[sfml] object RenderTarget:
    type sfRenderTarget = CArray[Byte, Nat.Digit3[Nat._4, Nat._8, Nat._0]]

    @name("_ZN2sf12RenderTargetD2Ev")
    def dtor(self: Ptr[sfRenderTarget]): Unit = extern

    @name("_ZN2sf12RenderTarget5clearERKNS_5ColorE")
    def sfRenderTarget_clear(self: Ptr[sfRenderTarget], color: Ptr[Color.sfColor]): Unit = extern

    @name("_ZN2sf12RenderTarget4drawERKNS_8DrawableERKNS_12RenderStatesE")
    def sfRenderTarget_draw(self: Ptr[sfRenderTarget], drawable: Ptr[Drawable.sfDrawable], states: Ptr[RenderStates.sfRenderStates]): Unit = extern
