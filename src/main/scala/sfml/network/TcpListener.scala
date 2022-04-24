package sfml
package network

import scalanative.unsafe.*
import scalanative.unsigned.UShort

import internal.network.TcpListener.*
import internal.network.TcpSocket.sfTcpSocket

case class TcpListener private[sfml] (listener: Ptr[sfTcpListener]) extends Socket with SFMLBind[sfTcpListener] with AutoCloseable:

    private[sfml] def bind(implicit z: Zone): Ptr[sfTcpListener] = listener

    def close(): Unit =
        sfTcpListener_destroy(listener)

    def localPort: UShort =
        sfTcpListener_getLocalPort(listener)

    def accept(): SocketStatus[TcpSocket] =
        val socket = sfTcpListener_accept_fix(listener)

        if socket == null then SocketStatus.Error()
        else SocketStatus.Done(TcpSocket(socket))

    def listen(port: UShort, address: IpAddress = IpAddress.Any()): SocketStatus[Unit] =
        SocketStatus(Zone { implicit z => sfTcpListener_listen_fix(listener, port, address.bind(z)) })(())

object TcpListener:
    def apply(): TcpListener =
        TcpListener(sfTcpListener_create())
