package sfml
package window

import scalanative.unsafe.*

import internal.window.Event.*

sealed trait Event

object Event:
    final case class Closed() extends Event
    final case class Resized(val width: Int, val height: Int) extends Event
    final case class LostFocus() extends Event
    final case class GainedFocus() extends Event
    final case class TextEntered(val unicode: Int) extends Event
    final case class KeyPressed(val code: Keyboard.Key, val alt: Boolean, val control: Boolean, val shift: Boolean, val system: Boolean) extends Event
    final case class KeyReleased(val code: Keyboard.Key, val alt: Boolean, val control: Boolean, val shift: Boolean, val system: Boolean) extends Event
    final case class MouseWheelScrolled(val wheel: Mouse.Wheel, val delta: Float, val x: Int, val y: Int) extends Event
    final case class MouseButtonPressed(val button: Mouse.Button, val x: Int, val y: Int) extends Event
    final case class MouseButtonReleased(val button: Mouse.Button, val x: Int, val y: Int) extends Event
    final case class MouseMoved(val x: Int, val y: Int) extends Event
    final case class MouseEntered() extends Event
    final case class MouseLeft() extends Event
    final case class JoystickButtonPressed(val joystickId: Int, val button: Int) extends Event
    final case class JoystickButtonReleased(val joystickId: Int, val button: Int) extends Event
    final case class JoystickMoved(val joystickId: Int, val axis: Joystick.Axis, val position: Float) extends Event
    final case class JoystickConnected(val joystickId: Int) extends Event
    final case class JoystickDisconnected(val joystickId: Int) extends Event
    final case class TouchBegan(val finger: Int, val x: Int, val y: Int) extends Event
    final case class TouchMoved(val finger: Int, val x: Int, val y: Int) extends Event
    final case class TouchEnded(val finger: Int, val x: Int, val y: Int) extends Event
    final case class SensorChanged(val sensor: Sensor.Type, val x: Float, val y: Float, val z: Float) extends Event

    def apply(event: Ptr[sfEvent]): Option[Event] =
        sfEventType.fromOrdinal(!event) match
            case sfEventType.sfEvtClosed                 => Some(Closed())
            case sfEventType.sfEvtResized                => Some(Resized(event.asInstanceOf[Ptr[sfSizeEvent]]))
            case sfEventType.sfEvtLostFocus              => Some(LostFocus())
            case sfEventType.sfEvtGainedFocus            => Some(GainedFocus())
            case sfEventType.sfEvtTextEntered            => Some(TextEntered(event.asInstanceOf[Ptr[sfTextEvent]]))
            case sfEventType.sfEvtKeyPressed             => Some(KeyPressed(event.asInstanceOf[Ptr[sfKeyEvent]]))
            case sfEventType.sfEvtKeyReleased            => Some(KeyReleased(event.asInstanceOf[Ptr[sfKeyEvent]]))
            case sfEventType.sfEvtMouseWheelScrolled     => Some(MouseWheelScrolled(event.asInstanceOf[Ptr[sfMouseWheelScrollEvent]]))
            case sfEventType.sfEvtMouseButtonPressed     => Some(MouseButtonPressed(event.asInstanceOf[Ptr[sfMouseButtonEvent]]))
            case sfEventType.sfEvtMouseButtonReleased    => Some(MouseButtonReleased(event.asInstanceOf[Ptr[sfMouseButtonEvent]]))
            case sfEventType.sfEvtMouseMoved             => Some(MouseMoved(event.asInstanceOf[Ptr[sfMouseMoveEvent]]))
            case sfEventType.sfEvtMouseEntered           => Some(MouseEntered())
            case sfEventType.sfEvtMouseLeft              => Some(MouseLeft())
            case sfEventType.sfEvtJoystickButtonPressed  => Some(JoystickButtonPressed(event.asInstanceOf[Ptr[sfJoystickButtonEvent]]))
            case sfEventType.sfEvtJoystickButtonReleased => Some(JoystickButtonReleased(event.asInstanceOf[Ptr[sfJoystickButtonEvent]]))
            case sfEventType.sfEvtJoystickMoved          => Some(JoystickMoved(event.asInstanceOf[Ptr[sfJoystickMoveEvent]]))
            case sfEventType.sfEvtJoystickConnected      => Some(JoystickConnected(event.asInstanceOf[Ptr[sfJoystickConnectEvent]]))
            case sfEventType.sfEvtJoystickDisconnected   => Some(JoystickDisconnected(event.asInstanceOf[Ptr[sfJoystickConnectEvent]]))
            case sfEventType.sfEvtTouchBegan             => Some(TouchBegan(event.asInstanceOf[Ptr[sfTouchEvent]]))
            case sfEventType.sfEvtTouchMoved             => Some(TouchMoved(event.asInstanceOf[Ptr[sfTouchEvent]]))
            case sfEventType.sfEvtTouchEnded             => Some(TouchEnded(event.asInstanceOf[Ptr[sfTouchEvent]]))
            case sfEventType.sfEvtSensorChanged          => Some(SensorChanged(event.asInstanceOf[Ptr[sfSensorEvent]]))
            case _                                       => None

    object Resized:
        def apply(event: Ptr[sfSizeEvent]): Resized =
            Resized(event._2.toInt, event._3.toInt)

    object TextEntered:
        def apply(event: Ptr[sfTextEvent]): TextEntered =
            TextEntered(event._2.toInt)

    object KeyPressed:
        def apply(event: Ptr[sfKeyEvent]): KeyPressed =
            KeyPressed(Keyboard.Key.fromOrdinal(event._2 + 1), event._3, event._4, event._5, event._6)

    object KeyReleased:
        def apply(event: Ptr[sfKeyEvent]): KeyReleased =
            KeyReleased(Keyboard.Key.fromOrdinal(event._2 + 1), event._3, event._4, event._5, event._6)

    object MouseWheelScrolled:
        def apply(event: Ptr[sfMouseWheelScrollEvent]): MouseWheelScrolled =
            MouseWheelScrolled(Mouse.Wheel.fromOrdinal(event._2), event._3, event._4, event._5)

    object MouseButtonPressed:
        def apply(event: Ptr[sfMouseButtonEvent]): MouseButtonPressed =
            MouseButtonPressed(Mouse.Button.fromOrdinal(event._2), event._3, event._4)

    object MouseButtonReleased:
        def apply(event: Ptr[sfMouseButtonEvent]): MouseButtonReleased =
            MouseButtonReleased(Mouse.Button.fromOrdinal(event._2), event._3, event._4)

    object MouseMoved:
        def apply(event: Ptr[sfMouseMoveEvent]): MouseMoved =
            MouseMoved(event._2, event._3)

    object JoystickButtonPressed:
        def apply(event: Ptr[sfJoystickButtonEvent]): JoystickButtonPressed =
            JoystickButtonPressed(event._2.toInt, event._3.toInt)

    object JoystickButtonReleased:
        def apply(event: Ptr[sfJoystickButtonEvent]): JoystickButtonReleased =
            JoystickButtonReleased(event._2.toInt, event._3.toInt)

    object JoystickMoved:
        def apply(event: Ptr[sfJoystickMoveEvent]): JoystickMoved =
            JoystickMoved(event._2.toInt, Joystick.Axis.fromOrdinal(event._3), event._4)

    object JoystickConnected:
        def apply(event: Ptr[sfJoystickConnectEvent]): JoystickConnected =
            JoystickConnected(event._2.toInt)

    object JoystickDisconnected:
        def apply(event: Ptr[sfJoystickConnectEvent]): JoystickDisconnected =
            JoystickDisconnected(event._2.toInt)

    object TouchBegan:
        def apply(event: Ptr[sfTouchEvent]): TouchBegan =
            TouchBegan(event._2.toInt, event._3, event._4)

    object TouchMoved:
        def apply(event: Ptr[sfTouchEvent]): TouchMoved =
            TouchMoved(event._2.toInt, event._3, event._4)

    object TouchEnded:
        def apply(event: Ptr[sfTouchEvent]): TouchEnded =
            TouchEnded(event._2.toInt, event._3, event._4)

    object SensorChanged:
        def apply(event: Ptr[sfSensorEvent]): SensorChanged =
            SensorChanged(Sensor.Type.fromOrdinal(event._2), event._3, event._4, event._5)
