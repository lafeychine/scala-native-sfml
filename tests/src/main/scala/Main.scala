import scala.util.Using
import sfml.graphics.*
import sfml.system.*
import sfml.window.*

import java.lang.String

import scalanative.unsafe.*
import scalanative.libc.stdlib.system

// import utest.*

class TestScreen(path: String):
    @SuppressWarnings(Array("org.wartremover.warts.Var"))
    private var id_screenshot = 0;

    @SuppressWarnings(Array("org.wartremover.warts.StringPlusAny"))
    final def takeScreenshot(): Unit =
        Zone { implicit z =>
            system(toCString("xwd -root -silent > " + path + "/scala/" + id_screenshot))
            id_screenshot = id_screenshot + 1
        }

@main def main(path: String) =
    test(TestScreen(path))

@SuppressWarnings(Array("org.wartremover.warts.All"))
def test(snTestScreen: TestScreen) =
    Using.Manager { use =>
        val title = use(sfml.system.String("Test"))
        val videoMode = VideoMode(1024, 768, 32)
        val window = use(RenderWindow(videoMode, title, Window.WindowStyle.DefaultStyle))

        // window.framerateLimit = 30

        window.isOpen()

        for event <- window.pollEvent() do println(event)

        window.clear(use(Color(0x01, 0x23, 0x45, 0x67)))
        window.display()

        snTestScreen.takeScreenshot()
        snTestScreen.takeScreenshot()

        window.close()
    }
