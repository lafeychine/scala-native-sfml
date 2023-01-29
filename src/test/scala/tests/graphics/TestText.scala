package tests

import sfml.graphics.*
import sfml.window.*

object TestText extends SNTest:
    override def snTest(snTestScreen: TestScreen): Unit =
        scala.util.Using.Manager { use =>
            // Setup
            val window = use(RenderWindow(VideoMode(1024, 768), "Test"))

            val font = use(Font())
            font.loadFromFile("src/test/resources/tuffy.ttf")

            val text = use(Text("Hello World", font, 50))

            window.isOpen()

            // Control test
            window.clear()
            window.draw(text)
            window.display()
            snTestScreen.takeScreenshot()

            // sf::Text::setPosition
            text.position = (100, 100)

            window.clear()
            window.draw(text)
            window.display()
            snTestScreen.takeScreenshot()

            // Teardown
            window.closeWindow()
        }
