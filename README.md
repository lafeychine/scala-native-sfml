# [WIP] Scala Native 3 bindings for SFML

This repository is a safe-[Scala Native](https://scala-native.org/en/stable/) binding of [SFML graphic library](https://www.sfml-dev.org/).


## Installation

### Requirements

This library requires [Scala Native 0.4.9](https://scala-native.org/en/stable/changelog/0.4.9.html) and therefore [Scala 3.2.1](https://www.scala-lang.org/download/3.2.1.html).

We recommend using [sbt](https://www.scala-sbt.org/download.html) to build your project, by setting up `project/plugins.sbt` and `build.sbt` as follows:

```scala
// project/plugins.sbt
addSbtPlugin("org.scala-native" % "sbt-scala-native" % "0.4.9")
```

```scala
// build.sbt
import scala.scalanative.build.*

name := "project"

scalaVersion := "3.2.1"
version := "0.1.0"

enablePlugins(ScalaNativePlugin)
```

We recommend you to check out to [`sbt` settings and tasks](https://scala-native.org/en/stable/user/sbt.html#sbt-settings-and-tasks) to tune settings according to your usage.


### Fetching the library

The library is not yet published on Maven, as this library is not mature yet.

However, packages are published to [GitHub Packages](https://github.com/features/packages) and can be found [here](https://github.com/lafeychine?tab=packages&repo_name=scala-native-sfml).

You have to [generate a token](https://github.com/settings/tokens) with `read:packages` scope and add it to your `~/.config/git/config` file:
```gitconfig
[github]
  token = TOKEN_DATA
```

To be able to fetch the library, you can use [sbt-github-packages](https://github.com/djspiewak/sbt-github-packages) by adding the following to your `project/plugins.sbt`:

```scala
// project/plugins.sbt
addSbtPlugin("com.codecommit" % "sbt-github-packages" % "0.5.3")
```

Finally, you can set up the [sbt-github-packages](https://github.com/djspiewak/sbt-github-packages) plugin by adding the following to your `build.sbt`:

```scala
// build.sbt
githubSuppressPublicationWarning := true
githubTokenSource := TokenSource.GitConfig("github.token")

resolvers += Resolver.githubPackages("lafeychine")
libraryDependencies += "io.github.lafeychine" %%% "scala-native-sfml" % "0.2.1"
```


## Notes

The typical way to use SFML in a foreign language is use the C ABI with [CSFML](https://github.com/SFML/CSFML).
However, this repository takes the choice to use the C++ ABI directly.

This choice is made because the C++ API is more complete than the C API, and for experiment reasons.

Therefore, this library is, for now, only compatible with `x86_64-linux-gnu` platform.
