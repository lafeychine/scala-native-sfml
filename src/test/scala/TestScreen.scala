package tests

import scalanative.libc.stdlib.system
import scalanative.unsafe.*

class TestScreen(path: String):
    @SuppressWarnings(Array("org.wartremover.warts.Var"))
    private var idScreenshot = 0

    final def takeScreenshot(): Unit =
        val cmd = s"xwd -root -silent | dd bs=3184 iflag=fullblock skip=1 status=none of=${path}/screenshot_${idScreenshot}"

        Zone { implicit z => system(toCString(cmd)) }
        idScreenshot = idScreenshot + 1
