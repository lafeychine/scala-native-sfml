package sfml
package internal
package graphics

import scalanative.unsafe.*

@link("csfml-graphics")
@extern object Rect:
    type sfIntRect = CStruct4[CInt, CInt, CInt, CInt]
    type sfFloatRect = CStruct4[CFloat, CFloat, CFloat, CFloat]

    def sfIntRect_contains(rect: Ptr[sfIntRect], x: CInt, y: CInt): Type.sfBool = extern
    def sfFloatRect_contains(rect: Ptr[sfFloatRect], x: CFloat, y: CFloat): Type.sfBool = extern
    def sfIntRect_intersects(rect_1: Ptr[sfIntRect], rect_2: Ptr[sfIntRect], intersection: Ptr[sfIntRect]): Type.sfBool = extern
    def sfFloatRect_intersects(rect_1: Ptr[sfFloatRect], rect_2: Ptr[sfFloatRect], intersection: Ptr[sfFloatRect]): Type.sfBool = extern
