package sfml
package internal
package graphics

import scalanative.unsafe.*

import system.{String, Vector2}

@link("sfml-graphics")
@extern private[sfml] object Text:
    type sfText = CStruct3[Drawable.sfDrawable, Transformable.sfTransformable, CArray[Byte, Nat.Digit3[Nat._1, Nat._8, Nat._4]]]

    @name("_ZN2sf4TextC2Ev")
    def ctor(self: Ptr[sfText]): Unit = extern

    @name("_ZN2sf4TextC2ERKNS_6StringERKNS_4FontEj")
    def ctor(self: Ptr[sfText], string: Ptr[String.sfString], font: Ptr[Font.sfFont], characterSize: CUnsignedInt): Unit = extern

    @name("_ZNK2sf4Text4drawERNS_12RenderTargetENS_12RenderStatesE")
    def sfText_draw(self: Ptr[sfText], target: Ptr[RenderTarget.sfRenderTarget], states: RenderStates.sfRenderStates): Unit = extern

    // TODO: sfText_findCharacterPos
    // TODO: sfText_getLocalBounds
    // TODO: sfText_getGlobalBounds

    /* Getter / Setter */

    @name("_ZNK2sf4Text16getCharacterSizeEv")
    def sfText_getCharacterSize(self: Ptr[sfText]): CUnsignedInt = extern

    @name("_ZN2sf4Text16setCharacterSizeEj")
    def sfText_setCharacterSize(self: Ptr[sfText], size: CUnsignedInt): Unit = extern

    @name("_ZNK2sf4Text8getColorEv")
    def sfText_getColor(self: Ptr[sfText]): Ptr[Color.sfColor] = extern

    @name("_ZN2sf4Text8setColorERKNS_5ColorE")
    def sfText_setColor(self: Ptr[sfText], color: Ptr[Color.sfColor]): Unit = extern

    @name("_ZNK2sf4Text12getFillColorEv")
    def sfText_getFillColor(self: Ptr[sfText]): Ptr[Color.sfColor] = extern

    @name("_ZN2sf4Text12setFillColorERKNS_5ColorE")
    def sfText_setFillColor(self: Ptr[sfText], color: Ptr[Color.sfColor]): Unit = extern

    // TODO: sfText_getFont

    @name("_ZN2sf4Text7setFontERKNS_4FontE")
    def sfText_setFont(self: Ptr[sfText], font: Ptr[Font.sfFont]): Unit = extern

    @name("_ZNK2sf4Text16getLetterSpacingEv")
    def sfText_getLetterSpacing(self: Ptr[sfText]): CFloat = extern

    @name("_ZN2sf4Text16setLetterSpacingEf")
    def sfText_setLetterSpacing(self: Ptr[sfText], spacingFactor: CFloat): Unit = extern

    @name("_ZNK2sf4Text14getLineSpacingEv")
    def sfText_getLineSpacing(self: Ptr[sfText]): CFloat = extern

    @name("_ZN2sf4Text14setLineSpacingEf")
    def sfText_setLineSpacing(self: Ptr[sfText], spacingFactor: CFloat): Unit = extern

    @name("_ZNK2sf4Text15getOutlineColorEv")
    def sfText_getOutlineColor(self: Ptr[sfText]): Ptr[Color.sfColor] = extern

    @name("_ZN2sf4Text15setOutlineColorERKNS_5ColorE")
    def sfText_setOutlineColor(self: Ptr[sfText], color: Ptr[Color.sfColor]): Unit = extern

    @name("_ZNK2sf4Text19getOutlineThicknessEv")
    def sfText_getOutlineThickness(self: Ptr[sfText]): CFloat = extern

    @name("_ZN2sf4Text19setOutlineThicknessEf")
    def sfText_setOutlineThickness(self: Ptr[sfText], thickness: CFloat): Unit = extern

    // TODO: sfText_getString

    @name("_ZN2sf4Text9setStringERKNS_6StringE")
    def sfText_setString(self: Ptr[sfText], string: Ptr[String.sfString]): Unit = extern
