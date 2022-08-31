import scala.scalanative.build.*

name := "scala-native-sfml"
organization := "io.github.lafeychine"
version := "0.3.0"

scalaVersion := "3.1.1"
enablePlugins(ScalaNativePlugin)

nativeConfig ~= {
  _.withLTO(LTO.thin)
   .withMode(Mode.releaseFull)
}

githubOwner := "lafeychine"
githubRepository := "scala-native-sfml"
