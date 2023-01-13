package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.Drawable.*

trait Drawable private[sfml] (private[sfml] val drawable: Ptr[sfDrawable])
