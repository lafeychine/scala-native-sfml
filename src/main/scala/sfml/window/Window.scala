package sfml
package window

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.Type.{booleanToSfBool, split, sfBoolToBoolean}
import internal.window.Event.sfEvent
import internal.window.Window.*

import system.String.toNativeString

class Window private[sfml] (private val window: Resource[sfWindow]) extends AutoCloseable:

    private[sfml] inline def toNativeWindow: Ptr[sfWindow] = window.ptr

    override def close(): Unit =
        sfWindow_close(toNativeWindow)

    def this(mode: VideoMode, title: String, style: Style, settings: ContextSettings) =
        this(Resource { (r: Ptr[sfWindow]) =>
            Zone { implicit z =>
                val modeSplit = split(mode.toNativeVideoMode)

                ctor(r, modeSplit(0), modeSplit(1), toNativeString(title), style.value.toUInt, settings.toNativeContextSettings);
            }
        })

    def this(mode: VideoMode, title: String, style: Style) =
        this(mode, title, style, ContextSettings())

    def this(mode: VideoMode, title: String) =
        this(mode, title, Style.Default)

    final def display(): Unit =
        sfWindow_display(toNativeWindow)

    // NOTE: To be able to use [`framerateLimit_=`]
    final def framerateLimit = ()

    final def framerateLimit_=(limit: Int) =
        sfWindow_setFramerateLimit(toNativeWindow, limit.toUInt)

    final def isOpen(): Boolean =
        sfWindow_isOpen(toNativeWindow)

    // NOTE: To be able to use [`mouseCursorVisible_=`]
    final def mouseCursorVisible = ()

    /** Show or hide the mouse cursor.
      *
      * The mouse cursor is visible by default.
      *
      * @param visible
      *   True to show the mouse cursor, false to hide it
      */
    final def mouseCursorVisible_=(visible: Boolean) =
        sfWindow_setMouseCursorVisible(toNativeWindow, visible)

    @SuppressWarnings(Array("org.wartremover.warts.OptionPartial"))
    final def pollEvent(): LazyList[Event] =
        def polling(event: Ptr[sfEvent]): Option[Event] =
            if sfWindow_pollEvent(toNativeWindow, event) then { Event(event) }
            else { None }

        Zone { implicit z =>
            val event = alloc[sfEvent]()

            LazyList.continually(polling(event)).takeWhile(_.isDefined).map(_.get)
        }

    // NOTE: To be able to use [`verticalSync_=`]
    final def verticalSync = ()

    final def verticalSync_=(enabled: Boolean) =
        sfWindow_setVerticalSyncEnabled(toNativeWindow, enabled)
