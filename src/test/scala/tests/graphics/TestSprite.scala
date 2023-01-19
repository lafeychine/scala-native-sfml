package tests

import sfml.graphics.*
import sfml.window.*

object TestSprite extends SNTest:
    override def snTest(snTestScreen: TestScreen): Unit =
        scala.util.Using.Manager { use =>
            // Setup
            val window = use(RenderWindow(VideoMode(1024, 768), "Test"))

            val texture = use(Texture())
            texture.loadFromFile("src/test/resources/sfml.png")

            val sprite = use(Sprite(texture))

            window.isOpen()

            // Control test
            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Sprite::setPosition
            sprite.position = (100, 100)

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // Teardown
            window.closeWindow()
        }
