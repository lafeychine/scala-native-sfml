package sfml
package graphics

import scalanative.unsafe.*

trait Drawable:
    def draw(target: RenderTarget, states: RenderStates): Unit
