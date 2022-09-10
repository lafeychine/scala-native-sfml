package sfml
package internal
package window

import scalanative.unsafe.*

object MouseButton:
    type sfMouseButton = CInt

    enum sfMouseButtonType:
        case sfMouseLeft
        case sfMouseRight
        case sfMouseMiddle
        case sfMouseXButton1
        case sfMouseXButton2
