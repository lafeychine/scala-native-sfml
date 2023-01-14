import scala.util.Using

import sfml.graphics.*
import sfml.window.*

import scalanative.unsafe.*

@main def main =
    Using.Manager { use =>
        val title = use(sfml.system.String("Test"))
        val videoMode = VideoMode(1024, 768)
        val window = use(RenderWindow(videoMode, title, Window.WindowStyle.DefaultStyle))

        val texture = use(Texture())
        texture.loadFromFile("src/test/resources/sfml.png")

        val sprite = use(Sprite(texture))

        while window.isOpen() do
            for event <- window.pollEvent() do
                println(event)
                event match {
                    case _: Event.Closed => window.closeWindow()
                    case _               => ()
                }

            window.clear(use(Color(0x01, 0x23, 0x45, 0x67)))

            window.draw(sprite)

            window.display()
    }
