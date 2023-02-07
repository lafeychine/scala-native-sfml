import org.junit.Assert.*
import org.junit.Test

import sfml.graphics.{Color, Rect, RenderWindow, Texture, Sprite}
import sfml.window.VideoMode

class SpriteTutorial extends GraphicalTest:
    @Test def graphicalTest(): Unit =
        snTestScreen.testName = "SpriteTutorial"

        scala.util.Using.Manager { use =>
            // Setup
            val window = use(RenderWindow(VideoMode(1024, 768), "My window"))

            val texture = use(Texture())
            assertTrue(texture.loadFromFile("src/test/resources/sfml.png"))

            val sprite = use(Sprite(texture))

            assertTrue(window.isOpen())

            // Control test
            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Texture::setSmooth
            texture.smooth = true

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Texture::setRepeated
            texture.repeated = true

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Sprite::setTextureRect
            sprite.textureRect = (100, 100, 600, 400)

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Sprite::setColor
            sprite.color = Color(0, 255, 0)

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Sprite::setColor
            sprite.color = Color(255, 255, 255, 128)

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Sprite::setPosition
            sprite.position = (100, 50)

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Sprite::move
            sprite.move((50, 20))

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Sprite::setRotation
            sprite.rotation = 90

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Sprite::rotate
            sprite.rotate(15)

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Sprite::setScale
            sprite.scale = (0.5, 2.0)

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Sprite::scale
            sprite.scale((1.5, 3.0))

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Sprite::setOrigin
            sprite.origin = (25, 25)

            window.clear()
            window.draw(sprite)
            window.display()
            snTestScreen.takeScreenshot()
        }
