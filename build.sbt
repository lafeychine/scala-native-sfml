name := "scala-native-sfml"

organization := "io.github.lafeychine"

version := "0.2.0"

scalaVersion := "3.1.1"

enablePlugins(ScalaNativePlugin)

nativeLinkingOptions := Seq(
    "-lcsfml-graphics",
    "-lcsfml-network",
    "-lcsfml-system",
    "-lcsfml-window"
)

githubOwner := "lafeychine"
githubRepository := "scala-native-sfml"
