import scala.scalanative.build.*

name := "scala-native-sfml"
organization := "io.github.lafeychine"

scalaVersion := "3.2.2"
version := "0.3.6"

enablePlugins(ScalaNativePlugin)

nativeConfig ~= {
    _.withIncrementalCompilation(true)
        .withLTO(LTO.thin)
        .withMode(Mode.releaseFull)
}

/* Documentation */
enablePlugins(SiteScaladocPlugin)

Compile / doc / scalacOptions ++= Seq("-siteroot", "docs", "-skip-by-id:sfml.internal", "-snippet-compiler:docs=compile")
SiteScaladoc / siteSubdirName := ""

/* Linting */
ThisBuild / wartremoverErrors := Warts.allBut(
    Wart.AsInstanceOf,
    Wart.DefaultArguments,
    Wart.ImplicitConversion,
    Wart.ImplicitParameter,
    Wart.Overloading
) ++ Seq(
    ContribWart.Apply,
    ContribWart.MissingOverride,
    ContribWart.NoNeedForMonad,
    ContribWart.SealedCaseClass,
    ContribWart.SomeApply,
    ContribWart.SymbolicName
)

/* Testing */
addCompilerPlugin("org.scala-native" % "junit-plugin" % "0.4.10" cross CrossVersion.full)
libraryDependencies += "org.scala-native" %%% "junit-runtime" % "0.4.10"

Test / envVars := Map("LSAN_OPTIONS" -> "print_suppressions=false:suppressions=src/test/leak.txt")
Test / nativeCompileOptions := Seq("-fsanitize=leak")
Test / nativeLinkingOptions := Seq("-fsanitize=leak")
Test / parallelExecution := false

/* Publishing */
githubOwner := "lafeychine"
githubRepository := "scala-native-sfml"
