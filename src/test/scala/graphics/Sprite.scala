import org.junit.Test

import sfml.graphics.*
import sfml.window.*

class GraphicalSprite extends GraphicalTest:
    @Test def graphicalTest(): Unit =
        snTestScreen.testName = "Sprite"

        scala.util.Using.Manager { use =>
            // Setup
            val window = use(RenderWindow(VideoMode(1024, 768), "Test"))

            val texture = use(Texture())
            texture.loadFromFile("src/test/resources/sfml.png")

            val sprite = Sprite(texture)

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
            window.close()
        }
