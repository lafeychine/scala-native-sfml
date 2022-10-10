package tests

import scalanative.libc.stdlib.system
import scalanative.unsafe.*

class TestScreen(path: String):
    @SuppressWarnings(Array("org.wartremover.warts.Var"))
    private var idScreenshot = 0

    final def takeScreenshot(): Unit =
        Zone { implicit z =>
            system(toCString(s"xwd -root -silent | dd bs=3184 iflag=fullblock skip=1 status=none of=${path}/screenshot_${idScreenshot}"))
        }
        idScreenshot = idScreenshot + 1
