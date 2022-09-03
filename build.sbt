import scala.scalanative.build.*

name := "scala-native-sfml"
organization := "io.github.lafeychine"
version := "0.3.0"

scalaVersion := "3.2.0"
enablePlugins(ScalaNativePlugin)

nativeConfig ~= {
    _.withLTO(LTO.thin)
        .withMode(Mode.releaseFull)
}

nativeCompileOptions := Seq("-fsanitize=leak")
nativeLinkingOptions := Seq("-fsanitize=leak")

githubOwner := "lafeychine"
githubRepository := "scala-native-sfml"

wartremoverErrors := Warts.allBut(
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
