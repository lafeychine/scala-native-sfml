package sfml
package internal
package network

import scalanative.unsafe.*

import system.Time

@link("sfml-network")
@extern object TcpSocket:
    type sfTcpSocket

    def sfTcpSocket_create(): Ptr[sfTcpSocket] = extern
    def sfTcpSocket_destroy(socket: Ptr[sfTcpSocket]): Unit = extern

    def sfTcpSocket_getLocalPort(socket: Ptr[sfTcpSocket]): CUnsignedShort = extern
    def sfTcpSocket_getRemoteAddress_fix(socket: Ptr[sfTcpSocket], address: Ptr[IpAddress.sfIpAddress]): Unit = extern
    def sfTcpSocket_getRemotePort(socket: Ptr[sfTcpSocket]): CUnsignedShort = extern

    def sfTcpSocket_isBlocking(socket: Ptr[sfTcpSocket]): Type.sfBool = extern
    def sfTcpSocket_setBlocking(socket: Ptr[sfTcpSocket], blocking: Type.sfBool): Unit = extern

    def sfTcpSocket_connect_fix(socket: Ptr[sfTcpSocket], remoteAddress: Ptr[IpAddress.sfIpAddress], remotePort: CUnsignedShort, timeout: Ptr[Time.sfTime]): SocketStatus.sfSocketStatus = extern
    def sfTcpSocket_disconnect(socket: Ptr[sfTcpSocket]): Unit = extern

    def sfTcpSocket_receive(socket: Ptr[sfTcpSocket], data: Ptr[Byte], size: CSize, received: Ptr[CSize]): SocketStatus.sfSocketStatus = extern
    def sfTcpSocket_send(socket: Ptr[sfTcpSocket], data: Ptr[Byte], size: CSize): SocketStatus.sfSocketStatus = extern
    def sfTcpSocket_sendPartial(socket: Ptr[sfTcpSocket], data: Ptr[Byte], size: CSize, sent: Ptr[CSize]): SocketStatus.sfSocketStatus = extern
