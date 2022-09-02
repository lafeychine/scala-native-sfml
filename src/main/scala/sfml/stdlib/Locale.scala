package sfml
package stdlib

import scalanative.unsafe.*

import internal.stdlib.Locale.*

class Locale private[sfml] (private[sfml] val locale: Ptr[stdLocale]) extends Resource:

    def this() =
        this(Resource { ctor(_) })

    def close(): Unit =
        Resource.close(dtor)(locale)
