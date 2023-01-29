package sfml
package internal
package graphics

import scalanative.unsafe.*

import stdlib.String
import system.Vector2
import window.{ContextSettings, VideoMode, Window}

@link("sfml-graphics")
@extern private[sfml] object Texture:
    type sfTexture = CStruct10[Vector2.sfVector2u, Vector2.sfVector2u, CUnsignedInt, Type.sfBool, Type.sfBool, Type.sfBool, Type.sfBool, Type.sfBool, Type.sfBool, Type.sfUint64];

    @name("_ZN2sf7TextureC2Ev")
    def ctor(self: Ptr[sfTexture]): Unit = extern

    @name("_ZN2sf7TextureD2Ev")
    def dtor(self: Ptr[sfTexture]): Unit = extern

    @name("_ZN2sf7Texture12loadFromFileERKNSt7__cxx1112basic_stringIcSt11char_traitsIcESaIcEEERKNS_4RectIiEE")
    def sfTexture_loadFromFile(self: Ptr[sfTexture], filename: Ptr[String.stdString], area: Ptr[Rect.sfIntRect]): Type.sfBool = extern

    @name("_ZNK2sf7Texture8isSmoothEv")
    def sfTexture_isSmooth(self: Ptr[sfTexture]): Type.sfBool = extern

    @name("_ZN2sf7Texture9setSmoothEb")
    def sfTexture_setSmooth(self: Ptr[sfTexture], smooth: Type.sfBool): Unit = extern

    @name("_ZNK2sf7Texture10isRepeatedEv")
    def sfTexture_isRepeated(self: Ptr[sfTexture]): Type.sfBool = extern

    @name("_ZN2sf7Texture11setRepeatedEb")
    def sfTexture_setRepeated(self: Ptr[sfTexture], repeated: Type.sfBool): Unit = extern
