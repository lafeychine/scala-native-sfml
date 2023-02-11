import org.junit.Assert.*
import org.junit.{After, AfterClass, Before, Test}

import scala.util.Using

import sfml.graphics.*
import sfml.window.*

class TestText:
    val font = Font()

    @Before def init(): Unit =
        font.loadFromFile("src/test/resources/tuffy.ttf")

    @Test def getGlobalBounds(): Unit =
        val text = Using(Text("Hello, world!", font, 50)) { text =>
            assertEquals(Rect[Float](3, 13, 244, 43), text.globalBounds)
        }

    @After def teardown(): Unit =
        font.close()

class GraphicalText extends GraphicalTest:
    @Test def graphicalTest(): Unit =
        snTestScreen.testName = "Text"

        Using.Manager { use =>
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
            window.close()
        }
