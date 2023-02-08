---
title: Sprites and textures
layout: sfml
refurl: https://www.sfml-dev.org/tutorials/2.5/graphics-sprite.php
---

## Vocabulary

Most (if not all) of you are already familiar with these two very common
objects, so let's define them very briefly.

A texture is an image. But we call it "texture" because it has a very specific
role: being mapped to a 2D entity.

A sprite is nothing more than a textured rectangle.
<img src="https://www.sfml-dev.org/tutorials/2.5/images/graphics-sprites-definition.png"/>

Ok, that was short but if you really don't understand what sprites and textures
are, then you'll find a much better description on Wikipedia.


## Loading a texture

Before creating any sprite, we need a valid texture. The class that encapsulates
textures in SFML is, surprisingly, [`Texture`](sfml.graphics.Texture). Since the
only role of a texture is to be loaded and mapped to graphical entities, almost
all its functions are about loading and updating it.

The most common way of loading a texture is from an image file on disk, which is
done with the [`loadFromFile`](sfml.graphics.Texture.loadFromFile) function.
```scala
//{
import sfml.graphics.Texture

//}
val texture = Texture()

if !(texture.loadFromFile("image.png")) then
    // error...
//{
    ()
//}
```

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

<!-- TODO: Side functions for Texture -->

SFML supports most common image file formats. The full list is available in the
API documentation.

All these loading functions have an optional argument, which can be used if you
want to load a smaller part of the image.
```scala sc-name:Texture.scala
//{
import sfml.graphics.{Color, Rect, RenderWindow, Texture, Sprite}
import sfml.window.VideoMode

val window = RenderWindow(VideoMode(800, 600), "My window")
val texture = Texture()

//}
// load a 32x32 rectangle that starts at (10, 10)
if !texture.loadFromFile("image.png", (10, 10, 32, 32)) then
    // error...
//{
    ()
//}
```

The [`Rect`](sfml.graphics.Rect) class is a simple utility type that represents
a rectangle. Its constructor takes the coordinates of the top-left corner, and
the size of the rectangle.

<!-- TODO: Update image from pixels -->

Additionally, a texture has two properties that change how it is rendered.

The first property allows one to smooth the texture. Smoothing a texture makes
pixel boundaries less visible (but the image a little more blurry), which can be
desirable if it is up-scaled.
```scala sc-compile-with:Texture.scala sc-name:Texture.scala
texture.smooth = true
```
<img src="https://www.sfml-dev.org/tutorials/2.5/images/graphics-sprites-smooth.png"/>

<div class="warning">
Since smoothing samples from adjacent pixels in the texture as well, it can lead
to the unwanted side effect of factoring in pixels outside the selected texture
area. This can happen when your sprite is located at non-integer coordinates.
</div>

The second property allows a texture to be repeatedly tiled within a single sprite.
```scala sc-compile-with:Texture.scala sc-name:Texture.scala
texture.repeated = true
```
<img src="https://www.sfml-dev.org/tutorials/2.5/images/graphics-sprites-repeated.png"/>

This only works if your sprite is configured to show a rectangle which is larger
than the texture, otherwise this property has no effect.


## Ok, can I have my sprite now?

Yes, you can now create your sprite.
```scala sc-compile-with:Texture.scala sc-name:Texture.scala
val sprite = Sprite(texture)
```

... and finally draw it.
```scala sc-compile-with:Texture.scala sc-name:Texture.scala
window.draw(sprite)
```

If you don't want your sprite to use the entire texture, you can set its texture
rectangle.
```scala sc-compile-with:Texture.scala sc-name:Texture.scala
sprite.textureRect = (10, 10, 32, 32)
```

You can also change the color of a sprite. The color that you set is modulated
(multiplied) with the texture of the sprite. This can also be used to change the
global transparency (alpha) of the sprite.
```scala sc-compile-with:Texture.scala sc-name:Texture.scala
sprite.color = Color(0, 255, 0)           // green
sprite.color = Color(255, 255, 255, 128)  // half transparent
```

These sprites all use the same texture, but have a different color:
<img src="https://www.sfml-dev.org/tutorials/2.5/images/graphics-sprites-color.png"/>

Sprites can also be transformed: They have a position, an orientation and a scale.
```scala sc-compile-with:Texture.scala sc-name:Texture.scala
// position
sprite.position = (10, 50)  // absolute position
sprite.move((5, 10))        // offset relative to the current position

// rotation
sprite.rotation = 90        // absolute angle
sprite.rotate(15)           // offset relative to the current angle

// scale
sprite.scale = (0.5, 2.0)   // absolute scale factor
sprite.scale((1.5, 3.0))    // factor relative to the current scale
```

By default, the origin for these three transformations is the top-left corner of
the sprite. If you want to set the origin to a different point (for example the
center of the sprite, or another corner), you can use the
[`origin`](sfml.graphics.Transformable.origin_=) function.
```scala sc-compile-with:Texture.scala sc-name:Texture.scala
sprite.origin = (25, 25)
```

Since transformation functions are common to all SFML entities, they are
explained in a separate tutorial: [Transforming
entities](transforming-entities.html).


## The white square problem

You successfully loaded a texture, constructed a sprite correctly, and... all
you see on your screen now is a white square. What happened?

This is a common mistake. When you set the texture of a sprite, all it does
internally is store a pointer to the texture instance. Therefore, if the texture
is destroyed or moves elsewhere in memory, the sprite ends up with an invalid
texture pointer.

This problem occurs when you write this kind of function:
```scala
//{
import sfml.graphics.{Sprite, Texture}

//}
def loadSprite(filename: String): Sprite =
    scala.util.Using(Texture()) { texture =>
        texture.loadFromFile(filename)

        Sprite(texture)
    } // error: the texture is destroyed here
//{
     .get
//}
```

You must correctly manage the lifetime of your textures and make sure that they
live as long as they are used by any sprite.


## The importance of using as few textures as possible

Using as few textures as possible is a good strategy, and the reason is simple:
Changing the current texture is an expensive operation for the graphics card.
Drawing many sprites that use the same texture will yield the best performance.

Additionally, using a single texture allows you to group static geometry into a
single entity (you can only use one texture per draw call), which will be much
faster to draw than a set of many entities. Batching static geometry involves
other classes and is therefore beyond the scope of this tutorial, for further
details see the vertex array tutorial (feature not ported yet).

Try to keep this in mind when you create your animation sheets or your tilesets:
Use as little textures as possible.


<!-- Using sf::Texture with OpenGL code section -->
