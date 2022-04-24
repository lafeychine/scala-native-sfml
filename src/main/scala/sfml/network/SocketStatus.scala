package sfml
package network

import scalanative.unsafe.*

import internal.network.SocketStatus.*

sealed trait SocketStatus[T]

object SocketStatus:
    case class Done[T](val result: T) extends SocketStatus[T]
    case class NotReady[T]() extends SocketStatus[T]
    case class Partial[T]() extends SocketStatus[T]
    case class Disconnected[T]() extends SocketStatus[T]
    case class Error[T]() extends SocketStatus[T]

    def apply[T](status: sfSocketStatus)(result: T): SocketStatus[T] =
        sfSocketStatusType.fromOrdinal(status) match
            case sfSocketStatusType.sfSocketDone         => Done(result)
            case sfSocketStatusType.sfSocketNotReady     => NotReady()
            case sfSocketStatusType.sfSocketPartial      => Partial()
            case sfSocketStatusType.sfSocketDisconnected => Disconnected()
            case _                                       => Error()
