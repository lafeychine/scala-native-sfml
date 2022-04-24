package sfml
package internal
package graphics

import scalanative.unsafe.*

@link("csfml-graphics")
@extern object Texture:
    type sfTexture

    def sfTexture_createFromFile(filename: CString, area: Ptr[Rect.sfIntRect]): Ptr[sfTexture] = extern
    def sfTexture_destroy(texture: Ptr[sfTexture]): Unit = extern

    def sfTexture_isRepeated(texture: Ptr[sfTexture]): Type.sfBool = extern
    def sfTexture_setRepeated(texture: Ptr[sfTexture], repeated: Type.sfBool): Unit = extern

    def sfTexture_isSmooth(texture: Ptr[sfTexture]): Type.sfBool = extern
    def sfTexture_setSmooth(texture: Ptr[sfTexture], smooth: Type.sfBool): Unit = extern
