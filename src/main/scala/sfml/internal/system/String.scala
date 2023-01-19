package sfml
package internal
package system

import scalanative.unsafe.*

import stdlib.String.stdString

@link("sfml-system")
@extern private[sfml] object String:
    type sfString = stdString
