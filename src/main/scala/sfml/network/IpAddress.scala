package sfml
package network

import scalanative.libc.string.strncpy
import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.network.IpAddress.*

case class IpAddress(private[sfml] address: String) extends SFMLBind[sfIpAddress]:

    private[sfml] def bind(implicit z: Zone) =
        val ipAddress = alloc[sfIpAddress]()

        strncpy(ipAddress.at(0), toCString(address), 16.toULong)

        return ipAddress

    override def toString() = address

object IpAddress:
    private[sfml] def apply(ipAddress: sfIpAddress): IpAddress =
        IpAddress(Zone(implicit z => { fromCString(ipAddress.at(0)) }))

    def Any(): IpAddress = IpAddress("0.0.0.0")
    def LocalHost(): IpAddress = IpAddress("127.0.0.1")
    def Broadcast(): IpAddress = IpAddress("255.255.255.255")
