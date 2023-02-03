package sfml
package window

import scalanative.unsafe.*

import internal.window.Event.*

enum Event:
    case Closed()
    case Resized(val width: Int, val height: Int)
    case LostFocus()
    case GainedFocus()
    case TextEntered(val unicode: Int)
    case KeyPressed(val code: Keyboard.Key, val alt: Boolean, val control: Boolean, val shift: Boolean, val system: Boolean)
    case KeyReleased(val code: Keyboard.Key, val alt: Boolean, val control: Boolean, val shift: Boolean, val system: Boolean)
    case MouseWheelScrolled(val wheel: Mouse.Wheel, val delta: Float, val x: Int, val y: Int)
    case MouseButtonPressed(val button: Mouse.Button, val x: Int, val y: Int)
    case MouseButtonReleased(val button: Mouse.Button, val x: Int, val y: Int)
    case MouseMoved(val x: Int, val y: Int)
    case MouseEntered()
    case MouseLeft()
    case JoystickButtonPressed(val joystickId: Int, val button: Int)
    case JoystickButtonReleased(val joystickId: Int, val button: Int)
    case JoystickMoved(val joystickId: Int, val axis: Joystick.Axis, val position: Float)
    case JoystickConnected(val joystickId: Int)
    case JoystickDisconnected(val joystickId: Int)
    case TouchBegan(val finger: Int, val x: Int, val y: Int)
    case TouchMoved(val finger: Int, val x: Int, val y: Int)
    case TouchEnded(val finger: Int, val x: Int, val y: Int)
    case SensorChanged(val sensor: Sensor.Type, val x: Float, val y: Float, val z: Float)

object Event:
    private[sfml] def apply(event: Ptr[sfEvent]): Option[Event] =
        EventType.fromOrdinal(!event) match
            case EventType.Closed                 => Option(Closed())
            case EventType.Resized                => Option(Resized(event.asInstanceOf[Ptr[sfSizeEvent]]))
            case EventType.LostFocus              => Option(LostFocus())
            case EventType.GainedFocus            => Option(GainedFocus())
            case EventType.TextEntered            => Option(TextEntered(event.asInstanceOf[Ptr[sfTextEvent]]))
            case EventType.KeyPressed             => Option(KeyPressed(event.asInstanceOf[Ptr[sfKeyEvent]]))
            case EventType.KeyReleased            => Option(KeyReleased(event.asInstanceOf[Ptr[sfKeyEvent]]))
            case EventType.MouseWheelScrolled     => Option(MouseWheelScrolled(event.asInstanceOf[Ptr[sfMouseWheelScrollEvent]]))
            case EventType.MouseButtonPressed     => Option(MouseButtonPressed(event.asInstanceOf[Ptr[sfMouseButtonEvent]]))
            case EventType.MouseButtonReleased    => Option(MouseButtonReleased(event.asInstanceOf[Ptr[sfMouseButtonEvent]]))
            case EventType.MouseMoved             => Option(MouseMoved(event.asInstanceOf[Ptr[sfMouseMoveEvent]]))
            case EventType.MouseEntered           => Option(MouseEntered())
            case EventType.MouseLeft              => Option(MouseLeft())
            case EventType.JoystickButtonPressed  => Option(JoystickButtonPressed(event.asInstanceOf[Ptr[sfJoystickButtonEvent]]))
            case EventType.JoystickButtonReleased => Option(JoystickButtonReleased(event.asInstanceOf[Ptr[sfJoystickButtonEvent]]))
            case EventType.JoystickMoved          => Option(JoystickMoved(event.asInstanceOf[Ptr[sfJoystickMoveEvent]]))
            case EventType.JoystickConnected      => Option(JoystickConnected(event.asInstanceOf[Ptr[sfJoystickConnectEvent]]))
            case EventType.JoystickDisconnected   => Option(JoystickDisconnected(event.asInstanceOf[Ptr[sfJoystickConnectEvent]]))
            case EventType.TouchBegan             => Option(TouchBegan(event.asInstanceOf[Ptr[sfTouchEvent]]))
            case EventType.TouchMoved             => Option(TouchMoved(event.asInstanceOf[Ptr[sfTouchEvent]]))
            case EventType.TouchEnded             => Option(TouchEnded(event.asInstanceOf[Ptr[sfTouchEvent]]))
            case EventType.SensorChanged          => Option(SensorChanged(event.asInstanceOf[Ptr[sfSensorEvent]]))
            case _                                => None

    object Resized:
        private[sfml] def apply(event: Ptr[sfSizeEvent]): Resized =
            Resized(event._2.toInt, event._3.toInt)

    object TextEntered:
        private[sfml] def apply(event: Ptr[sfTextEvent]): TextEntered =
            TextEntered(event._2.toInt)

    object KeyPressed:
        private[sfml] def apply(event: Ptr[sfKeyEvent]): KeyPressed =
            KeyPressed(Keyboard.Key.fromOrdinal(event._2 + 1), event._3, event._4, event._5, event._6)

    object KeyReleased:
        private[sfml] def apply(event: Ptr[sfKeyEvent]): KeyReleased =
            KeyReleased(Keyboard.Key.fromOrdinal(event._2 + 1), event._3, event._4, event._5, event._6)

    object MouseWheelScrolled:
        private[sfml] def apply(event: Ptr[sfMouseWheelScrollEvent]): MouseWheelScrolled =
            MouseWheelScrolled(Mouse.Wheel.fromOrdinal(event._2), event._3, event._4, event._5)

    object MouseButtonPressed:
        private[sfml] def apply(event: Ptr[sfMouseButtonEvent]): MouseButtonPressed =
            MouseButtonPressed(Mouse.Button.fromOrdinal(event._2), event._3, event._4)

    object MouseButtonReleased:
        private[sfml] def apply(event: Ptr[sfMouseButtonEvent]): MouseButtonReleased =
            MouseButtonReleased(Mouse.Button.fromOrdinal(event._2), event._3, event._4)

    object MouseMoved:
        private[sfml] def apply(event: Ptr[sfMouseMoveEvent]): MouseMoved =
            MouseMoved(event._2, event._3)

    object JoystickButtonPressed:
        private[sfml] def apply(event: Ptr[sfJoystickButtonEvent]): JoystickButtonPressed =
            JoystickButtonPressed(event._2.toInt, event._3.toInt)

    object JoystickButtonReleased:
        private[sfml] def apply(event: Ptr[sfJoystickButtonEvent]): JoystickButtonReleased =
            JoystickButtonReleased(event._2.toInt, event._3.toInt)

    object JoystickMoved:
        private[sfml] def apply(event: Ptr[sfJoystickMoveEvent]): JoystickMoved =
            JoystickMoved(event._2.toInt, Joystick.Axis.fromOrdinal(event._3), event._4)

    object JoystickConnected:
        private[sfml] def apply(event: Ptr[sfJoystickConnectEvent]): JoystickConnected =
            JoystickConnected(event._2.toInt)

    object JoystickDisconnected:
        private[sfml] def apply(event: Ptr[sfJoystickConnectEvent]): JoystickDisconnected =
            JoystickDisconnected(event._2.toInt)

    object TouchBegan:
        private[sfml] def apply(event: Ptr[sfTouchEvent]): TouchBegan =
            TouchBegan(event._2.toInt, event._3, event._4)

    object TouchMoved:
        private[sfml] def apply(event: Ptr[sfTouchEvent]): TouchMoved =
            TouchMoved(event._2.toInt, event._3, event._4)

    object TouchEnded:
        private[sfml] def apply(event: Ptr[sfTouchEvent]): TouchEnded =
            TouchEnded(event._2.toInt, event._3, event._4)

    object SensorChanged:
        private[sfml] def apply(event: Ptr[sfSensorEvent]): SensorChanged =
            SensorChanged(Sensor.Type.fromOrdinal(event._2), event._3, event._4, event._5)
