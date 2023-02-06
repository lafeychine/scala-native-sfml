package sfml
package internal
package window

import scalanative.unsafe.*

object Event:
    type sfEvent = CInt
    type sfKeyEvent = CStruct6[sfEvent, CInt, CBool, CBool, CBool, CBool]
    type sfTextEvent = CStruct2[sfEvent, CUnsignedInt]
    type sfMouseMoveEvent = CStruct3[sfEvent, CInt, CInt]
    type sfMouseButtonEvent = CStruct4[sfEvent, CInt, CInt, CInt]
    type sfMouseWheelScrollEvent = CStruct5[sfEvent, CInt, CFloat, CInt, CInt]
    type sfJoystickMoveEvent = CStruct4[sfEvent, CUnsignedInt, CInt, CFloat]
    type sfJoystickButtonEvent = CStruct3[sfEvent, CUnsignedInt, CUnsignedInt]
    type sfJoystickConnectEvent = CStruct2[sfEvent, CUnsignedInt]
    type sfSizeEvent = CStruct3[sfEvent, CUnsignedInt, CUnsignedInt]
    type sfTouchEvent = CStruct4[sfEvent, CUnsignedInt, CInt, CInt]
    type sfSensorEvent = CStruct5[sfEvent, CInt, CFloat, CFloat, CFloat]
