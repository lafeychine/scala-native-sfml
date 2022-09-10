package sfml
package internal
package window

import scalanative.unsafe.*

object MouseWheel:
    type sfMouseWheel = CInt

    enum sfMouseWheelType:
        case sfMouseVerticalWheel
        case sfMouseHorizontalWheel
