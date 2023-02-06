package sfml
package internal
package graphics

import scalanative.unsafe.*

import system.Vector2

@link("sfml-graphics")
@extern private[sfml] object RenderTarget:
    type sfRenderTarget = CStruct3[
        View.sfView,
        View.sfView,
        CArray[Byte, Nat.Digit3[Nat._1, Nat._4, Nat._4]]
    ]

    @name("_ZN2sf12RenderTargetD2Ev")
    def dtor(self: Ptr[sfRenderTarget]): Unit = extern

    @name("_ZN2sf12RenderTarget5clearERKNS_5ColorE")
    def sfRenderTarget_clear(self: Ptr[sfRenderTarget], color: Ptr[Color.sfColor]): Unit = extern

    @name("_ZN2sf12RenderTarget4drawERKNS_8DrawableERKNS_12RenderStatesE")
    def sfRenderTarget_draw(self: Ptr[sfRenderTarget], drawable: Ptr[Drawable.sfDrawable], states: Ptr[RenderStates.sfRenderStates]): Unit = extern

    @name("_ZNK2sf12RenderTarget7getViewEv")
    def sfRenderTarget_getView(self: Ptr[sfRenderTarget]): Ptr[View.sfView] = extern

    @name("_ZNK2sf12RenderTarget16mapPixelToCoordsERKNS_7Vector2IiEE")
    def sfRenderTarget_mapPixelToCoords(self: Ptr[sfRenderTarget], point: Ptr[Vector2.sfVector2i]): Type.sfSplit[Vector2.sfVector2f] = extern
