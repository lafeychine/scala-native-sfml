package sfml

import scalanative.unsafe.*

private[sfml] trait StackResource[+T]:
    private[sfml] def bind(implicit z: Zone): T
