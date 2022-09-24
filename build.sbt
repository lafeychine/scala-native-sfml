import scala.scalanative.build.*

name := "scala-native-sfml"
organization := "io.github.lafeychine"
version := "0.3.0"

ThisBuild / scalaVersion := "3.2.0"

ThisBuild / nativeConfig ~= {
    _.withLTO(LTO.thin)
        .withMode(Mode.releaseFull)
}

/* Linting options */
ThisBuild / wartremoverErrors := Warts.allBut(
    Wart.AsInstanceOf,
    Wart.DefaultArguments,
    Wart.ImplicitConversion,
    Wart.ImplicitParameter,
    Wart.Overloading
) ++ Seq(
    ContribWart.Apply,
    ContribWart.ExposedTuples,
    ContribWart.MissingOverride,
    ContribWart.NoNeedForMonad,
    ContribWart.SealedCaseClass,
    ContribWart.SymbolicName,
    ContribWart.UnsafeInheritance
)

/* Project definitions */
lazy val sfml = (project in file("sfml"))
    .settings(
        githubOwner := "lafeychine",
        githubRepository := "scala-native-sfml"
    )
    .enablePlugins(ScalaNativePlugin)

lazy val tests = (project in file("tests"))
    .enablePlugins(ScalaNativePlugin)
    .dependsOn(sfml)

tests / Compile / nativeCompileOptions := Seq("-fsanitize=leak")
tests / Compile / nativeLinkingOptions := Seq("-fsanitize=leak")
