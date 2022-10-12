import scala.scalanative.build.*

name := "scala-native-sfml"
organization := "io.github.lafeychine"

scalaVersion := "3.2.0"
version := "0.4.0"

enablePlugins(ScalaNativePlugin)

nativeConfig ~= {
    _.withLTO(LTO.thin)
        .withMode(Mode.releaseFull)
}

/* Documentation */
enablePlugins(SiteScaladocPlugin)

Compile / doc / scalacOptions ++= Seq("-skip-by-id:sfml.internal")
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
    ContribWart.ExposedTuples,
    ContribWart.MissingOverride,
    ContribWart.NoNeedForMonad,
    ContribWart.SealedCaseClass,
    ContribWart.SymbolicName,
    ContribWart.UnsafeInheritance
)

/* Testing */
Test / mainClass := Some("main")
Test / test := (Test / nativeLink).value

Test / nativeCompileOptions := Seq("-fsanitize=leak")
Test / nativeLinkingOptions := Seq("-fsanitize=leak")

/* Publishing */
githubOwner := "lafeychine"
githubRepository := "scala-native-sfml"
