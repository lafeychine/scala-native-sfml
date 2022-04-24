package sfml
package internal
package network

import scalanative.unsafe.*

object SocketStatus:
    type sfSocketStatus = CInt

    enum sfSocketStatusType:
        case sfSocketDone
        case sfSocketNotReady
        case sfSocketPartial
        case sfSocketDisconnected
        case sfSocketError
