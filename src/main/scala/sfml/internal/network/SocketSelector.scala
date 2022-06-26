package sfml
package internal
package network

import scalanative.unsafe.*

import system.Time

@link("sfml-network")
@extern object SocketSelector:
    type sfSocketSelector

    def sfSocketSelector_create(): Ptr[sfSocketSelector] = extern
    def sfSocketSelector_destroy(selector: Ptr[sfSocketSelector]): Unit = extern

    def sfSocketSelector_addTcpListener(selector: Ptr[sfSocketSelector], socket: Ptr[TcpListener.sfTcpListener]): Unit = extern
    def sfSocketSelector_isTcpListenerReady(selector: Ptr[sfSocketSelector], socket: Ptr[TcpListener.sfTcpListener]): Type.sfBool = extern
    def sfSocketSelector_removeTcpListener(selector: Ptr[sfSocketSelector], socket: Ptr[TcpListener.sfTcpListener]): Unit = extern

    def sfSocketSelector_addTcpSocket(selector: Ptr[sfSocketSelector], socket: Ptr[TcpSocket.sfTcpSocket]): Unit = extern
    def sfSocketSelector_isTcpSocketReady(selector: Ptr[sfSocketSelector], socket: Ptr[TcpSocket.sfTcpSocket]): Type.sfBool = extern
    def sfSocketSelector_removeTcpSocket(selector: Ptr[sfSocketSelector], socket: Ptr[TcpSocket.sfTcpSocket]): Unit = extern

    def sfSocketSelector_clear(selector: Ptr[sfSocketSelector]): Unit = extern

    def sfSocketSelector_wait_fix(selector: Ptr[sfSocketSelector], timeout: Ptr[Time.sfTime]): Type.sfBool = extern
