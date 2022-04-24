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

    enum sfEventType:
        case sfEvtClosed
        case sfEvtResized
        case sfEvtLostFocus
        case sfEvtGainedFocus
        case sfEvtTextEntered
        case sfEvtKeyPressed
        case sfEvtKeyReleased
        case sfEvtMouseWheelMoved
        case sfEvtMouseWheelScrolled
        case sfEvtMouseButtonPressed
        case sfEvtMouseButtonReleased
        case sfEvtMouseMoved
        case sfEvtMouseEntered
        case sfEvtMouseLeft
        case sfEvtJoystickButtonPressed
        case sfEvtJoystickButtonReleased
        case sfEvtJoystickMoved
        case sfEvtJoystickConnected
        case sfEvtJoystickDisconnected
        case sfEvtTouchBegan
        case sfEvtTouchMoved
        case sfEvtTouchEnded
        case sfEvtSensorChanged
