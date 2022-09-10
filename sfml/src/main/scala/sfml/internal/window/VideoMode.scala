package sfml
package internal
package window

import scalanative.unsafe.*

object VideoMode:
    type sfVideoMode = CStruct3[CUnsignedInt, CUnsignedInt, CUnsignedInt]
