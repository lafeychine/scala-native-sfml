package sfml
package window

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.Type.{booleanToSfBool, split, sfBoolToBoolean}
import internal.window.Event.sfEvent
import internal.window.Window.*

import system.String.stringToSfString

class Window private[sfml] (private[sfml] val window: Ptr[sfWindow]) extends Resource:

    override def close(): Unit =
        Window.close(window)()
        Resource.close(window)

    def this(mode: VideoMode, title: String, style: Style, settings: ContextSettings) =
        this(Resource { (r: Ptr[sfWindow]) =>
            Zone { implicit z =>
                val modeSplit = split(mode.videoMode)

                ctor(r, modeSplit(0), modeSplit(1), title, style.value.toUInt, settings.contextSettings);
            }
        })

    def this(mode: VideoMode, title: String, style: Style) =
        this(mode, title, style, ContextSettings())

    def this(mode: VideoMode, title: String) =
        this(mode, title, Style.Default)

    final def closeWindow(): Unit =
        sfWindow_closeWindow(window)

    final def display(): Unit =
        sfWindow_display(window)

    // NOTE: To be able to use [`framerateLimit_=`]
    final def framerateLimit = ()

    final def framerateLimit_=(limit: Int) =
        sfWindow_setFramerateLimit(window, limit.toUInt)

    final def isOpen(): Boolean =
        sfWindow_isOpen(window)

    @SuppressWarnings(Array("org.wartremover.warts.OptionPartial"))
    final def pollEvent(): LazyList[Event] =
        def polling(event: Ptr[sfEvent]): Option[Event] =
            if sfWindow_pollEvent(window, event) then { Event(event) }
            else { None }

        Zone { implicit z =>
            val event = alloc[sfEvent]()

            LazyList.continually(polling(event)).takeWhile(_.isDefined).map(_.get)
        }

    // NOTE: To be able to use [`verticalSync_=`]
    final def verticalSync = ()

    final def verticalSync_=(enabled: Boolean) =
        sfWindow_setVerticalSyncEnabled(window, enabled)

object Window:
    extension (window: Ptr[sfWindow])
        private[sfml] def close(): Unit =
            dtor(window)
