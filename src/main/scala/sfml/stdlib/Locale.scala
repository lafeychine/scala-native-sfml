package sfml
package stdlib

import scalanative.unsafe.*

import internal.stdlib.Locale.*

class Locale private[sfml] (self: Ptr[stdLocaleFields])
    extends Resource[Ptr[stdLocale]]:

    private[sfml] def bind = self

    def this() =
      this(Resource { ctor(_) })

    def close(): Unit =
      Resource.close(dtor)(bind)
