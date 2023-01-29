package sfml
package internal
package graphics

import scalanative.unsafe.*

import system.String

@link("sfml-graphics")
@extern private[sfml] object Text:
    type sfText = CStruct3[Drawable.sfDrawable, Transformable.sfTransformable, CArray[Byte, Nat.Digit3[Nat._1, Nat._8, Nat._4]]]

    @name("_ZN2sf4TextC2Ev")
    def ctor(self: Ptr[sfText]): Unit = extern

    @name("_ZN2sf4TextC2ERKNS_6StringERKNS_4FontEj")
    def ctor(self: Ptr[sfText], string: Ptr[String.sfString], font: Ptr[Font.sfFont], characterSize: CUnsignedInt): Unit = extern

    @name("_ZNK2sf4Text4drawERNS_12RenderTargetENS_12RenderStatesE")
    def sfText_draw(self: Ptr[sfText], target: Ptr[RenderTarget.sfRenderTarget], states: RenderStates.sfRenderStates): Unit = extern
