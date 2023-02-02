import scalanative.libc.stdlib.system
import scalanative.unsafe.*

// NOTE: Currently, cannot use JUnit's rules to retrieve the test name -> Use of `testName` variable
@SuppressWarnings(Array("org.wartremover.warts.Var"))
class TestScreen(var folderPath: String = ".", var testName: String = ""):
    private var idScreenshot = 0

    final def takeScreenshot(): Unit =
        val path = s"${folderPath}/scala/screenshot_${idScreenshot}"
        val cmd = s"xwd -root -silent | dd bs=3184 iflag=fullblock skip=1 status=none of=${path}"

        Zone { implicit z => system(toCString(cmd)) }
        idScreenshot = idScreenshot + 1
