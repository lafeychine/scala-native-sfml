package sfml
package internal
package graphics

import scalanative.unsafe.*

@link("sfml-graphics")
@extern private[sfml] object Drawable:
    type sfDrawable = CArray[Byte, Nat._8]
