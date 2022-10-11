package tests

import scalanative.reflect.annotation.EnableReflectiveInstantiation

@EnableReflectiveInstantiation
trait SNTest:
    def snTest(snTestScreen: TestScreen): Unit
