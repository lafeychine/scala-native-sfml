import scala.scalanative.reflect.Reflect
import scala.scalanative.reflect.annotation.EnableReflectiveInstantiation

@main def main(path: String, testName: String) =
    Reflect
        .lookupLoadableModuleClass("tests." + testName + "$")
        .map(cls =>
            val obj = cls.loadModule().asInstanceOf[tests.SNTest]

            obj.snTest(tests.TestScreen(path))
        )
