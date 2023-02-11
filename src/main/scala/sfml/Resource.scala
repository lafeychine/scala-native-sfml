package sfml

import scalanative.runtime.*
import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

@SuppressWarnings(scala.Array("org.wartremover.warts.Nothing"))
private[sfml] final class Resource[T: Tag] private (buffer: Either[Ptr[T], ByteArray]):

    private[sfml] def this(ctor: Ptr[T] => Unit) =
        this(Right(ByteArray.alloc(sizeof[T].toInt)))
        ctor(ptr)

    private[sfml] def this(inner: Ptr[T]) =
        this(Left(inner))

    private[sfml] inline def ptr: Ptr[T] =
        buffer match
            case Left(ptr)    => ptr
            case Right(array) => fromRawPtr(array.atRaw(0))
