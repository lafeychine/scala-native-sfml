import scala.scalanative.build.*

name := "scala-native-sfml"
organization := "io.github.lafeychine"
version := "0.3.0"

scalaVersion := "3.1.3"
enablePlugins(ScalaNativePlugin)

nativeConfig ~= {
    _.withLTO(LTO.thin)
        .withMode(Mode.releaseFull)
}

nativeCompileOptions := Seq("-fsanitize=leak")
nativeLinkingOptions := Seq("-fsanitize=leak")

githubOwner := "lafeychine"
githubRepository := "scala-native-sfml"
