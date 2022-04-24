package sfml

import scalanative.unsafe.*

private[sfml] trait SFMLBind[T]:
    private[sfml] def bind(implicit z: Zone): Ptr[T]
