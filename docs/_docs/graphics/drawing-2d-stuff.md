---
title: Drawing 2D stuff
layout: sfml
url: https://www.sfml-dev.org/tutorials/2.5/graphics-draw.php
---

## Introduction

As you learnt in the previous tutorials, SFML's window module provides an easy
way to open an OpenGL window and handle its events, but it doesn't help when it
comes to drawing something. The only option which is left to you is to use the
powerful, yet complex and low level OpenGL API.

Fortunately, SFML provides a graphics module which will help you draw 2D
entities in a much simpler way than with OpenGL. 


## The drawing window

To draw the entities provided by the graphics module, you must use a specialized
window class: [`RenderWindow`](sfml.graphics.RenderWindow). This class is
derived from [`Window`](sfml.window.Window), and inherits all its functions.
Everything that you've learnt about [`Window`](sfml.window.Window) (creation,
event handling, controlling the framerate, mixing with OpenGL, etc.) is
applicable to [`RenderWindow`](sfml.graphics.RenderWindow) as well.

On top of that, [`RenderWindow`](sfml.graphics.RenderWindow) adds high-level
functions to help you draw things easily. In this tutorial we'll focus on two of
these functions: [`clear`](sfml.graphics.RenderWindow.clear) and
[`draw`](sfml.graphics.RenderWindow.draw). They are as simple as their name
implies: clear clears the whole window with the chosen color, and draw draws
whatever object you pass to it.

Here is what a typical main loop looks like with a render window: 
```scala
import sfml.graphics.*
import sfml.window.*

@main def main =
    // handle resources properly with Using manager
    scala.util.Using.Manager { use =>
        // create the window
        val window = use(RenderWindow(VideoMode(800, 600), "My window"))

        // run the program as long as the window is open
        while window.isOpen() do
            // check all the window's events that were triggered since the last iteration of the loop
            for event <- window.pollEvent() do
                event match
                    // "close requested" event: we close the window
                    case _: Event.Closed => window.closeWindow()
                    case _               => ()

            // clear the window with black color
            window.clear(Color.Black())

            // draw everything here...
            // window.draw(...)

            // end the current frame
            window.display()
    }
```

Calling [`clear`](sfml.graphics.RenderWindow.clear) before drawing anything is
mandatory, otherwise the contents from previous frames will be present behind
anything you draw. The only exception is when you cover the entire window with
what you draw, so that no pixel is not drawn to. In this case you can avoid
calling clear (although it won't have a noticeable impact on performance).

Calling [`display`](sfml.graphics.RenderWindow.display) is also mandatory, it
takes what was drawn since the last call to display and displays it on the
window. Indeed, things are not drawn directly to the window, but to a hidden
buffer. This buffer is then copied to the window when you call display -- this
is called double-buffering. 

This `clear`/`draw`/`display` cycle is the only good way to draw things. Don't
try other strategies, such as keeping pixels from the previous frame, "erasing"
pixels, or drawing once and calling display multiple times. You'll get strange
results due to double-buffering. Modern graphics hardware and APIs are really
made for repeated `clear`/`draw`/`display` cycles where everything is completely
refreshed at each iteration of the main loop. Don't be scared to draw 1000
sprites 60 times per second, you're far below the millions of triangles that
your computer can handle. 


## What can I draw now?

Now that you have a main loop which is ready to draw, let's see what, and how,
you can actually draw there.

SFML provides four kinds of drawable entities: three of them are ready to be
used (sprites, text and shapes), the last one is the building block that will
help you create your own drawable entities (vertex arrays).

Although they share some common properties, each of these entities come with
their own nuances and are therefore explained in dedicated tutorials:
  - [Sprite tutorial](sprites-and-textures.html)
  - Text tutorial (feature not ported yet)
  - Shape tutorial (feature not ported yet)
  - Vertex array tutorial (feature not ported yet)
