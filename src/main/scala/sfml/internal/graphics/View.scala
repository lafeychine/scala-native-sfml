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

    @name("_ZN2sf4ViewC2ERKNS_7Vector2IfEES4_")
    def ctor(self: Ptr[sfView], center: Ptr[Vector2.sfVector2f], size: Ptr[Vector2.sfVector2f]): Unit = extern

    @name("_ZN2sf4ViewC2ERKNS_4RectIfEE")
    def ctor(self: Ptr[sfView], rectangle: Ptr[Rect.sfFloatRect]): Unit = extern

    @name("_ZN2sf4View4moveEff")
    def sfView_move(self: Ptr[sfView], offsetX: Float, offsetY: Float): Unit = extern

    @name("_ZN2sf4View4moveERKNS_7Vector2IfEE")
    def sfView_move(self: Ptr[sfView], offset: Ptr[Vector2.sfVector2f]): Unit = extern

    @name("_ZN2sf4View5resetERKNS_4RectIfEE")
    def sfView_reset(self: Ptr[sfView], rectangle: Ptr[Rect.sfFloatRect]): Unit = extern

    @name("_ZN2sf4View6rotateEf")
    def sfView_rotate(self: Ptr[sfView], angle: Float): Unit = extern

    @name("_ZN2sf4View4zoomEf")
    def sfView_zoom(self: Ptr[sfView], factor: Float): Unit = extern

    /* Getter / Setter */

    @name("_ZNK2sf4View9getCenterEv")
    def sfView_getCenter(self: Ptr[sfView]): Ptr[Vector2.sfVector2f] = extern

    @name("_ZN2sf4View9setCenterEff")
    def sfView_setCenter(self: Ptr[sfView], x: Float, y: Float): Unit = extern

    @name("_ZN2sf4View9setCenterERKNS_7Vector2IfEE")
    def sfView_setCenter(self: Ptr[sfView], center: Ptr[Vector2.sfVector2f]): Unit = extern

    @name("_ZNK2sf4View11getRotationEv")
    def sfView_getRotation(self: Ptr[sfView]): Float = extern

    @name("_ZN2sf4View11setRotationEf")
    def sfView_setRotation(self: Ptr[sfView], angle: Float): Unit = extern

    @name("_ZNK2sf4View7getSizeEv")
    def sfView_getSize(self: Ptr[sfView]): Ptr[Vector2.sfVector2f] = extern

    @name("_ZN2sf4View7setSizeEff")
    def sfView_setSize(self: Ptr[sfView], width: Float, height: Float): Unit = extern

    @name("_ZN2sf4View7setSizeERKNS_7Vector2IfEE")
    def sfView_setSize(self: Ptr[sfView], size: Ptr[Vector2.sfVector2f]): Unit = extern

    @name("_ZNK2sf4View12getTransformEv")
    def sfView_getTransform(self: Ptr[sfView]): Transform.sfTransform = extern

    @name("_ZNK2sf4View19getInverseTransformEv")
    def sfView_getInverseTransform(self: Ptr[sfView]): Transform.sfTransform = extern

    @name("_ZNK2sf4View11getViewportEv")
    def sfView_getViewport(self: Ptr[sfView]): Ptr[Rect.sfFloatRect] = extern

    @name("_ZN2sf4View11setViewportERKNS_4RectIfEE")
    def sfView_setViewport(self: Ptr[sfView], viewport: Ptr[Rect.sfFloatRect]): Unit = extern
