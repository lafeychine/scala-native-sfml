package sfml
package internal
package window

import scalanative.unsafe.*

object ContextSettings:
    type sfContextSettings = CStruct7[CUnsignedInt, CUnsignedInt, CUnsignedInt, CUnsignedInt, CUnsignedInt, CUnsignedInt, CInt]
