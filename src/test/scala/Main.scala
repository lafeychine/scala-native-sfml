import scala.scalanative.reflect.Reflect
import scala.scalanative.reflect.annotation.EnableReflectiveInstantiation

@main def main(args: String*) =
    if args.length != 2 then System.exit(0)

    val path = args(0)
    val testName = args(1)

    Reflect
        .lookupLoadableModuleClass("tests." + testName + "$")
        .map(cls =>
            val obj = cls.loadModule().asInstanceOf[tests.SNTest]

            obj.snTest(tests.TestScreen(path))
        )
