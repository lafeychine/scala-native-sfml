package sfml
package internal
package graphics

import scalanative.unsafe.*

import system.Vector2

@link("sfml-graphics")
@extern private[sfml] object Transformable:
    type sfTransformable = CArray[Byte, Nat.Digit3[Nat._1, Nat._7, Nat._6]]

    @name("_ZN2sf13TransformableC2Ev")
    def ctor(self: Ptr[sfTransformable]): Unit = extern

    @name("_ZN2sf13TransformableD2Ev")
    def dtor(self: Ptr[sfTransformable]): Unit = extern

    @name("_ZNK2sf13Transformable9getOriginEv")
    def sfTransformable_getOrigin(self: Ptr[sfTransformable]): Ptr[Vector2.sfVector2f] = extern

    @name("_ZN2sf13Transformable9setOriginERKNS_7Vector2IfEE")
    def sfTransformable_setOrigin(self: Ptr[sfTransformable], factors: Ptr[Vector2.sfVector2f]): Unit = extern

    @name("_ZNK2sf13Transformable11getPositionEv")
    def sfTransformable_getPosition(self: Ptr[sfTransformable]): Ptr[Vector2.sfVector2f] = extern

    @name("_ZN2sf13Transformable11setPositionERKNS_7Vector2IfEE")
    def sfTransformable_setPosition(self: Ptr[sfTransformable], pos: Ptr[Vector2.sfVector2f]): Unit = extern

    @name("_ZNK2sf13Transformable11getRotationEv")
    def sfTransformable_getRotation(self: Ptr[sfTransformable]): CFloat = extern

    @name("_ZN2sf13Transformable11setRotationEf")
    def sfTransformable_setRotation(self: Ptr[sfTransformable], angle: CFloat): Unit = extern

    @name("_ZNK2sf13Transformable8getScaleEv")
    def sfTransformable_getScale(self: Ptr[sfTransformable]): Ptr[Vector2.sfVector2f] = extern

    @name("_ZN2sf13Transformable8setScaleERKNS_7Vector2IfEE")
    def sfTransformable_setScale(self: Ptr[sfTransformable], factors: Ptr[Vector2.sfVector2f]): Unit = extern
