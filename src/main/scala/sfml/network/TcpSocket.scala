package sfml
package network

import scalanative.unsafe.*
import scalanative.unsigned.{UnsignedRichInt, UShort}

import internal.Type.{booleanToSfBool, sfBoolToBoolean}
import internal.network.{fromCArray, toCArray}
import internal.network.TcpSocket.*
import internal.network.IpAddress.sfIpAddress

import system.Time

case class TcpSocket private[sfml] (socket: Ptr[sfTcpSocket]) extends Socket with SFMLBind[sfTcpSocket] with AutoCloseable:

    private[sfml] def bind(implicit z: Zone): Ptr[sfTcpSocket] = socket

    def close(): Unit =
        sfTcpSocket_disconnect(socket)
        sfTcpSocket_destroy(socket)

    def localPort: UShort =
        sfTcpSocket_getLocalPort(socket)

    def remoteAddress: IpAddress =
        Zone { implicit z =>
            val ipAddress = alloc[sfIpAddress]()

            sfTcpSocket_getRemoteAddress_fix(socket, ipAddress)
            IpAddress(ipAddress)
        }

    def remotePort: UShort =
        sfTcpSocket_getRemotePort(socket)

    def blocking: Boolean =
        sfTcpSocket_isBlocking(socket)

    def blocking_=(blockingMode: Boolean) =
        sfTcpSocket_setBlocking(socket, blockingMode)

    def connect(address: IpAddress, remotePort: UShort, timeout: Time = Time.Zero()): SocketStatus[Unit] =
        SocketStatus(Zone { implicit z => sfTcpSocket_connect_fix(socket, address.bind(z), remotePort, timeout.bind(z)) })(())

    def receive(): SocketStatus[Array[Byte]] =
        Zone { implicit z =>
            val size = alloc[CSize]()
            val data = alloc[Byte](1024)

            SocketStatus(sfTcpSocket_receive(socket, data, 1024.toULong, size))(fromCArray(data, !size))
        }

    def send(data: Array[Byte]): SocketStatus[Unit] =
        Zone { implicit z =>
            SocketStatus(sfTcpSocket_send(socket, toCArray(data), data.length.toULong))(())
        }

    def sendPartial(data: Array[Byte]): SocketStatus[Long] =
        Zone { implicit z =>
            val size = alloc[CSize]()

            SocketStatus(sfTcpSocket_sendPartial(socket, toCArray(data), data.length.toULong, size))(((!size).toLong))
        }

object TcpSocket:
    def apply(): TcpSocket =
        TcpSocket(sfTcpSocket_create())
