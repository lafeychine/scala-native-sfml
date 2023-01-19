package sfml
package internal
package graphics

import scalanative.unsafe.*

@link("sfml-graphics")
@extern object RenderStates:
    type sfRenderStates = CStruct4[BlendMode.sfBlendMode, Transform.sfTransform, Ptr[Texture.sfTexture], Ptr[Byte]]
