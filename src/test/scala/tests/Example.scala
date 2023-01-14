package tests

import scala.util.Using

import sfml.graphics.*
import sfml.window.*

object Example extends SNTest:
    override def snTest(snTestScreen: TestScreen): Unit =
        Using.Manager { use =>
            // Create the main window
            val title = use(sfml.system.String("Test"))
            val videoMode = VideoMode(1024, 768)
            val window = use(RenderWindow(videoMode, title, Window.WindowStyle.DefaultStyle))

            // Load a sprite to display
            val texture = use(Texture())
            texture.loadFromFile("src/test/resources/sfml.png")

            val sprite = use(Sprite(texture))

            // Start the game loop
            window.isOpen()

            // Process events
            window.pollEvent()

            // Clear screen
            window.clear(use(Color(0x01, 0x23, 0x45, 0x67)))
            window.display()
            snTestScreen.takeScreenshot()

            // Draw the sprite
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // Close window
            window.closeWindow()
        }
