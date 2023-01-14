import scala.util.Using

import sfml.graphics.*
import sfml.window.*

@main def main2(): Unit =
    Using.Manager { use =>
        val title = use(sfml.system.String("Test"))
        val videoMode = VideoMode(1024, 768, 32)
        val window = use(RenderWindow(videoMode, title, Window.WindowStyle.DefaultStyle))

        window.isOpen()
        window.pollEvent()

        window.clear(use(Color(0x01, 0x23, 0x45, 0x67)))
        window.display()

        window.closeWindow()
    }
