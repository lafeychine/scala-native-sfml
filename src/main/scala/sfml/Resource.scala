package sfml

import scalanative.libc.stdlib.{malloc, free}
import scalanative.unsafe.*

trait Resource extends AutoCloseable

private[sfml] object Resource:
    private def apply[T: Tag](ctor: Ptr[T] => Unit, buffer: Ptr[T]): Ptr[T] =
        ctor(buffer); buffer

    final def apply[T: Tag](ctor: Ptr[T] => Unit): Ptr[T] =
        apply(ctor, malloc(sizeof[T]).asInstanceOf[Ptr[T]])

    final def close[T](dtor: Ptr[T] => Unit)(resource: Ptr[T]): Unit =
        dtor(resource)
        free(resource.asInstanceOf[Ptr[Byte]])
