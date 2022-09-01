package sfml

import scalanative.libc.stdlib.{malloc, free}
import scalanative.unsafe.*

private[sfml] trait Resource[+T] extends AutoCloseable:
    private[sfml] def bind: T

    def close(): Unit

private[sfml] object Resource:
    private def apply[T: Tag, U](ctor: Ptr[U] => Unit, buffer: Ptr[Byte]): Ptr[T] =
        ctor(buffer.asInstanceOf[Ptr[U]])
        buffer.asInstanceOf[Ptr[T]]

    final def apply[T: Tag, U](ctor: Ptr[U] => Unit): Ptr[T] =
        apply(ctor, malloc(sizeof[T]))

    final def close[T](dtor: Ptr[T] => Unit)(resource: Ptr[T]): Unit =
        dtor(resource)
        free(resource.asInstanceOf[Ptr[Byte]])
