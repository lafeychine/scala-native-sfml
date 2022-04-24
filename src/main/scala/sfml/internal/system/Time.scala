package sfml
package internal
package system

import scalanative.unsafe.*

object Time:
    type sfTime = CStruct1[Type.sfInt64]
