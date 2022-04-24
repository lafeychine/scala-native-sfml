package sfml
package internal
package network

import scalanative.unsafe.*

def fromCArray(buffer: Ptr[Byte], length: CSize)(implicit z: Zone): Array[Byte] =
    val data = Array.ofDim[Byte](length.toInt)

    for i <- 0 to length.toInt - 1 do data(i) = !(buffer + i)

    data

def toCArray(data: Array[Byte])(implicit z: Zone): Ptr[Byte] =
    val buffer = alloc[Byte](data.length)

    for i <- 0 to data.length - 1 do !(buffer + i) = data(i)

    buffer
