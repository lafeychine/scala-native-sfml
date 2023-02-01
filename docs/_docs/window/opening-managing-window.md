---
title: Opening and managing a SFML window
---

# Opening and managing a SFML window

Original content can be found [on the SFML website](https://www.sfml-dev.org/tutorials/2.5/window-window.php) (only for C++)


## Introduction

This tutorial only explains how to open and manage a window. Drawing stuff is
beyond the scope of the [sfml-window module](sfml.window): it is handled by the
[sfml-graphics module](sfml.graphics). However, the window management remains
exactly the same so reading this tutorial is important in any case.


## Opening a window

Windows in SFML are defined by the [`Window`](sfml.window.Window) class. A
window can be created an opened directly upon construction:
```scala
import sfml.window.{VideoMode, Window}

@main def main =
    scala.util.Using.Manager { use =>
        val window = use(Window(VideoMode(800, 600), "My window"))
    }
```

The first argument, the _video mode_, defines the size of the window (the inner
size, without the title bar and borders). Here, we create a window with a size
of 800x600 pixels. The [`VideoMode`](sfml.window.VideoMode) class has some
interesting static functions to get the desktop resolution, or the list of valid
video modes for fullscreen mode. Don't hesitate to have a look at its
documentation.

The second argument is simply the title of the window.

This constructor accepts a third optional argument: a style, which allows you to
choose which decorations and features you want. You can use any combination of
the following styles:

| Style              | Description                                                                                                        |
|--------------------|--------------------------------------------------------------------------------------------------------------------|
| `Style.None`       | No decoration at all (useful for splash screens, for example); this style cannot be combined with others           |
| `Style.Titlebar`   | The window has a titlebar                                                                                          |
| `Style.Resize`     | The window can be resized and has a maximize button                                                                |
| `Style.Close`      | The window has a close button                                                                                      |
| `Style.Fullscreen` | The window is shown in fullscreen mode; this style cannot be combined with others, and requires a valid video mode |
| `Style.Default`    | The default style, which is a shortcut for `Titlebar | Resize | Close`                                             |

There's also a fourth optional argument, which defines OpenGL specific options
which are explained in the dedicated OpenGL tutorial (feature not ported yet).

<!-- TODO: Delayed window -->


## Bringing the window to life

If you try to execute the code above with nothing in place of the "...", you
will hardly see something. First, because the program ends immediately. Second,
because there's no event handling -- so even if you added an endless loop to
this code, you would see a dead window, unable to be moved, resized, or closed.

Let's add some code to make this program a bit more interesting:
```scala
import sfml.window.{Event, VideoMode, Window}

@main def main =
    scala.util.Using.Manager { use =>
        val window = use(Window(VideoMode(800, 600), "My window"))

        while window.isOpen() do
            for event <- window.pollEvent() do
                event match {
                    case _: Event.Closed => window.closeWindow()
                    case _               => ()
                }
    }
```

The above code will open a window, and terminate when the user closes it. Let's
see how it works in detail.

First, we added a loop that ensures that the application will be
refreshed/updated until the window is closed. Most (if not all) SFML programs
will have this kind of loop, sometimes called the _main loop_ or _game loop_.

Then, the first thing that we want to do inside our game loop is check for any
events that occurred. Note that we use a `while` loop so that all pending events
are processed in case there were several.
The [`pollEvent`](sfml.window.Window.pollEvent) function returns `true` if an
event was pending, or `false` if there was none.

Whenever we get an event, we must check its type (window closed? key pressed?
mouse moved? joystick connected? ...), and react accordingly if we are
interested in it. In this case, we only care about the
[`Event::Closed`](sfml.window.Event.Closed) event, which is triggered when the
user wants to close the window. At this point, the window is still open and we
have to close it explicitly with the
[`closeWindow`](sfml.window.Window.closeWindow) function.  This enables you to
do something before the window is closed, such as saving the current state of
the application, or displaying a message.

A mistake that people often make is to forget the event loop, simply because
they don't yet care about handling events (they use real-time inputs instead).
Without an event loop, the window will become unresponsive. It is important to
note that the event loop has two roles: in addition to providing events to the
user, it gives the window a chance to process its internal events too, which is
required so that it can react to move or resize user actions.

After the window has been closed, the main loop exits and the program
terminates.

At this point, you probably noticed that we haven't talked about _drawing
something_ to the window yet. As stated in the introduction, this is not the job
of the [sfml-window module](sfml.window), and you'll have to jump to the
[sfml-graphics tutorials](../graphics/index.html) if you want to draw things
such as sprites, text or shapes.

<!-- TODO: OpenGL drawing -->

Don't expect to see something interesting in this window: you may see a uniform
color (black or white), or the last contents of the previous application that
used OpenGL, or... something else.


<!-- TODO: Playing with the window section -->

## Controlling the framerate

Sometimes, when your application runs fast, you may notice visual artifacts such
as tearing. The reason is that your application's refresh rate is not
synchronized with the vertical frequency of the monitor, and as a result, the
bottom of the previous frame is mixed with the top of the next one.

The solution to this problem is to activate vertical synchronization. It is
automatically handled by the graphics card, and can easily be switched on and
off with [`verticalSync`](sfml.window.Window.verticalSync_=):
```scala
//{
import sfml.window.{VideoMode, Window}

val window = Window(VideoMode(800, 600), "My window")
//}
window.verticalSync = true  // call it once, after creating the window
```

After this call, your application will run at the same frequency as the
monitor's refresh rate.

Sometimes [`verticalSync`](sfml.window.Window.verticalSync_=) will have no
effect: this is most likely because vertical synchronization is forced to "off"
in your graphics driver's settings. It should be set to "controlled by
application" instead.

In other situations, you may also want your application to run at a given
framerate, instead of the monitor's frequency. This can be done by setting
[`framerateLimit`](sfml.window.Window.framerateLimit_=):
```scala
//{
import sfml.window.{VideoMode, Window}

val window = Window(VideoMode(800, 600), "My window")
//}
window.framerateLimit = 60  // call it once, after creating the window
```

Unlike `verticalSync`, this feature is implemented by SFML itself.
<!-- , using a combination of sf::Clock and sf::sleep. -->
An important consequence is that it is not 100% reliable, especially for high
framerates: <!-- sf::sleep's resolution --> it depends on the underlying
operating system and hardware, and can be as high as 10 or 15 milliseconds.
Don't rely on this feature to implement precise timing.

Never use both [`verticalSync`](sfml.window.Window.verticalSync_=) and
[`framerateLimit`](sfml.window.Window.framerateLimit_=) at the same time! They
would badly mix and make things worse.

<!-- TODO: Things to know about windows -->
