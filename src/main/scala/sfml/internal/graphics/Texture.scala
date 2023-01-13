package sfml
package internal
package graphics

import scalanative.unsafe.*

import stdlib.String
import system.Vector2
import window.{ContextSettings, VideoMode, Window}

@link("sfml-graphics")
@extern private[sfml] object Texture:
    type sfTexture = CStruct10[Vector2.sfVector2u, Vector2.sfVector2u, CUnsignedInt, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Type.sfUint64];

    @name("_ZN2sf7TextureC2Ev")
    def ctor(self: Ptr[sfTexture]): Unit = extern

    @name("_ZN2sf7TextureD2Ev")
    def dtor(self: Ptr[sfTexture]): Unit = extern

    @name("_ZN2sf7Texture12loadFromFileERKNSt7__cxx1112basic_stringIcSt11char_traitsIcESaIcEEERKNS_4RectIiEE")
    def sfTexture_loadFromFile(self: Ptr[sfTexture], filename: Ptr[String.stdString], area: Ptr[Rect.sfIntRect]): Boolean = extern
