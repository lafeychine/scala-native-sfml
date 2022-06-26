package sfml
package internal
package network

import scalanative.unsafe.Nat.*
import scalanative.unsafe.*

@link("sfml-network")
@extern object IpAddress:
    private type _16 = Digit2[_1, _6]
    type sfIpAddress = CArray[Byte, _16]
