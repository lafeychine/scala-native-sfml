package sfml
package internal
package graphics

import scalanative.unsafe.*

@link("sfml-graphics")
@extern private[sfml] object Color:
    type sfColor = CStruct4[Type.sfUint8, Type.sfUint8, Type.sfUint8, Type.sfUint8]

    @name("_ZN2sf5ColorC2Ehhhh")
    def ctor(color: Ptr[sfColor], r: Type.sfUint8, g: Type.sfUint8, b: Type.sfUint8, a: Type.sfUint8): Unit = extern
