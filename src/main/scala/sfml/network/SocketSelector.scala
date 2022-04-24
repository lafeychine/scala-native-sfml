package sfml
package network

import scalanative.unsafe.*

import internal.Type.sfBoolToBoolean
import internal.network.SocketSelector.*

import system.Time

case class SocketSelector private[sfml] (selector: Ptr[sfSocketSelector]) extends SFMLBind[sfSocketSelector] with AutoCloseable:

    private[sfml] def bind(implicit z: Zone): Ptr[sfSocketSelector] = selector

    def close(): Unit =
        sfSocketSelector_destroy(selector)

    def add(listener: TcpListener): Unit =
        Zone { implicit z => sfSocketSelector_addTcpListener(selector, listener.bind(z)) }

    def isReady(listener: TcpListener): Boolean =
        Zone { implicit z => sfSocketSelector_isTcpListenerReady(selector, listener.bind(z)) }

    def remove(listener: TcpListener): Unit =
        Zone { implicit z => sfSocketSelector_removeTcpListener(selector, listener.bind(z)) }

    def add(socket: TcpSocket): Unit =
        Zone { implicit z => sfSocketSelector_addTcpSocket(selector, socket.bind(z)) }

    def isReady(socket: TcpSocket): Boolean =
        Zone { implicit z => sfSocketSelector_isTcpSocketReady(selector, socket.bind(z)) }

    def remove(socket: TcpSocket): Unit =
        Zone { implicit z => sfSocketSelector_removeTcpSocket(selector, socket.bind(z)) }

    def clear(): Unit =
        sfSocketSelector_clear(selector)

    def wait(timeout: Time = Time.Zero()): Boolean =
        Zone { implicit z => sfSocketSelector_wait_fix(selector, timeout.bind(z)) }

object SocketSelector:
    def apply(): SocketSelector =
        SocketSelector(sfSocketSelector_create())
