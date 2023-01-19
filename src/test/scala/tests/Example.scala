package tests

import scala.util.Using

import sfml.graphics.*
import sfml.window.*

object Example extends SNTest:
    override def snTest(snTestScreen: TestScreen): Unit =
        Using.Manager { use =>
            // Create the main window
            val window = use(RenderWindow(VideoMode(1024, 768), "Test"))

            // Load a sprite to display
            val texture = use(Texture())
            texture.loadFromFile("src/test/resources/sfml.png")

            val sprite = use(Sprite(texture))

            // Start the game loop
            window.isOpen()

            // Process events
            window.pollEvent()

            // Clear screen
            window.clear(Color(0x01, 0x23, 0x45))
            window.display()
            snTestScreen.takeScreenshot()

            // Draw the sprite
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // Close window
            window.closeWindow()
        }
