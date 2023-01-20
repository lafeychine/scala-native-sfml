import scala.scalanative.build.*

name := "scala-native-sfml"
organization := "io.github.lafeychine"

scalaVersion := "3.2.1"
version := "0.2.2"

enablePlugins(ScalaNativePlugin)

nativeConfig ~= {
    _.withIncrementalCompilation(true)
        .withLTO(LTO.none)
        .withMode(Mode.debug)
}

/* Documentation */
enablePlugins(SiteScaladocPlugin)

Compile / doc / scalacOptions ++= Seq("-siteroot", "docs", "-skip-by-id:sfml.internal")
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
    ContribWart.SymbolicName
)

/* Testing */
Test / mainClass := Some("main")
Test / test := (Test / nativeLink).value

Test / nativeCompileOptions := Seq("-fsanitize=leak")
Test / nativeLinkingOptions := Seq("-fsanitize=leak")

/* Publishing */
githubOwner := "lafeychine"
githubRepository := "scala-native-sfml"
