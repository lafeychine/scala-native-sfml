---
title: Text and fonts
layout: sfml
refurl: https://www.sfml-dev.org/tutorials/2.5/graphics-text.php
---

## Loading a font

Before drawing any text, you need to have an available font, just like any other
program that prints text. Fonts are encapsulated in the
[`Font`](sfml.graphics.Font) class, which provides three main features: loading
a font, getting glyphs (i.e. visual characters) from it, and reading its
attributes. In a typical program, you'll only have to make use of the first
feature, loading the font, so let's focus on that first.

The most common way of loading a font is from a file on disk, which is done with
the [`loadFromFile`](sfml.graphics.Font.loadFromFile) function.
```scala sc-name:Texture.scala
//{
import sfml.graphics.{Color, Font, RenderWindow, Text}
import sfml.window.VideoMode

val window = RenderWindow(VideoMode(800, 600), "My window")
//}
val font = Font()

if !(font.loadFromFile("arial.ttf")) then
    // error...
//{
    ()
//}
```

Note that SFML won't load your system fonts automatically, i.e.
`font.loadFromFile("Courier New")` won't work. Firstly, because SFML requires
file names, not font names, and secondly because SFML doesn't have magical
access to your system's font folder. If you want to load a font, you will need
to include the font file with your application, just like every other resource
(images, sounds, ...).

<div class="warning">
The <code>loadFromFile</code> function can sometimes fail with no obvious
reason. First, check the error message that SFML prints to the standard output
(check the console). If the message is "unable to open file", make sure that the
working directory (which is the directory that any file path will be interpreted
relative to) is what you think it is: When you run the application from your
desktop environment, the working directory is the executable folder. However,
when you launch your program from your IDE (Visual Studio, Code::Blocks, ...)
the working directory might sometimes be set to the project directory instead.
This can usually be changed quite easily in the project settings.
</div>

<!-- TODO: Side functions for Font -->

SFML supports most common font formats. The full list is available in the API
documentation.

That's all you need to do. Once your font is loaded, you can start drawing text.


## Drawing text

To draw text, you will be using the [`Text`](sfml.graphics.Text) class. It's
very simple to use:
```scala sc-compile-with:Texture.scala sc-name:Texture.scala
val text = Text()

// select the font
text.font = font;

// set the string to display
text.string = "Hello world"

// set the character size
text.characterSize = 24   // in pixels, not points!

// set the color
text.fillColor = Color.Red()


// ...

// inside the main loop, between window.clear() and window.display()
window.draw(text);
```
<!-- TODO: Missing sf::Text::setStyle -->

<img src="https://www.sfml-dev.org/tutorials/2.5/images/graphics-text-draw.png"/>

Text can also be transformed: They have a position, an orientation and a scale.
The functions involved are the same as for the [`Sprite`](sfml.graphics.Sprite)
class and other SFML entities. They are explained in the Transforming entities
tutorial. (feature not ported yet)


<!-- TODO: Non-ASCII characters section -->

<!-- TODO: Making your own text class section -->
