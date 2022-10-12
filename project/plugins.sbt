addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.4.7")

/* Documentation */
addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "1.4.1")

/* Linting */
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.6")
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "3.0.6")
addSbtPlugin("org.wartremover" % "sbt-wartremover-contrib" % "2.0.1")

/* Publishing */
addSbtPlugin("com.codecommit" % "sbt-github-packages" % "0.5.2")
