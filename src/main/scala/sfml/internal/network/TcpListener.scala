package sfml
package internal
package network

import scalanative.unsafe.*

@link("sfml-network")
@extern object TcpListener:
    type sfTcpListener

    def sfTcpListener_create(): Ptr[sfTcpListener] = extern
    def sfTcpListener_destroy(listener: Ptr[sfTcpListener]): Unit = extern

    def sfTcpListener_getLocalPort(listener: Ptr[sfTcpListener]): CUnsignedShort = extern

    def sfTcpListener_accept_fix(listener: Ptr[sfTcpListener]): Ptr[TcpSocket.sfTcpSocket] = extern

    def sfTcpListener_listen_fix(
        listener: Ptr[sfTcpListener],
        port: CUnsignedShort,
        address: Ptr[IpAddress.sfIpAddress]
    ): SocketStatus.sfSocketStatus = extern
