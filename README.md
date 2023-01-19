# [WIP] SFML bindings for Scala Native 3

This repository is a safe [Scala Native](https://scala-native.org/en/stable/) binding of the [SFML graphic library](https://www.sfml-dev.org/).


## Example

```scala
import scala.util.Using

import sfml.graphics.*
import sfml.window.*

@main def main =
    Using.Manager { use =>
        val window = use(RenderWindow(VideoMode(1024, 768), "Hello world"))

        val texture = use(Texture())
        texture.loadFromFile("cat.png")

        val sprite = use(Sprite(texture))

        while window.isOpen() do
            for event <- window.pollEvent() do
                event match {
                    case _: Event.Closed => window.closeWindow()
                    case _               => ()
                }

            window.clear(Color.Black())

            window.draw(sprite)

            window.display()
    }
```


## Differences from SFML

### Resource management

When an object manipulates system resources, it must extend the [`AutoCloseable`](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html) trait and has to be explicitly [`close`](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html#close--)d.

Since the SFML library has such objects, this binding adds a custom trait [`Resource`](https://lafeychine.github.io/scala-native-sfml/sfml/Resource.html), which enables either of the following paradigms:
 - manipulate these objects through the [`Using`](https://www.scala-lang.org/api/3.x/scala/util/Using$.html) manager, as shown in the example above;
 - explicitly call the [`close`](https://lafeychine.github.io/scala-native-sfml/sfml/Resource.html#close:Unit) method when appropriate.


## Installation

### Requirements

This library requires [Scala Native 0.4.9](https://scala-native.org/en/stable/changelog/0.4.9.html) and therefore [Scala 3.2.1](https://www.scala-lang.org/download/3.2.1.html).

It is recommended to use [sbt](https://www.scala-sbt.org/download.html) to build a project, by setting up `project/plugins.sbt` and `build.sbt` as follows:

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

Please refer to [`sbt` settings and tasks](https://scala-native.org/en/stable/user/sbt.html#sbt-settings-and-tasks) for additional customisation.


### Fetching the library

The library is not published on Maven, as it is not mature yet.

Nonetheless, packages are accessible via [GitHub Packages](https://github.com/features/packages) and can be found [here](https://github.com/lafeychine?tab=packages&repo_name=scala-native-sfml).

Access to GitHub Packages requires to [generate a token](https://github.com/settings/tokens) with the `read:packages` scope and add it to `~/.config/git/config`:
```gitconfig
[github]
  token = TOKEN_DATA
```

The project [sbt-github-packages](https://github.com/djspiewak/sbt-github-packages) enables to fetch the project. To use it, add the following to `project/plugins.sbt`:

```scala
// project/plugins.sbt
addSbtPlugin("com.codecommit" % "sbt-github-packages" % "0.5.3")
```

The setup of the [sbt-github-packages](https://github.com/djspiewak/sbt-github-packages) plugin is done in `build.sbt`, for example:

```scala
// build.sbt
githubSuppressPublicationWarning := true
githubTokenSource := TokenSource.GitConfig("github.token")

resolvers += Resolver.githubPackages("lafeychine")
libraryDependencies += "io.github.lafeychine" %%% "scala-native-sfml" % "0.2.1"
```


## Notes

The typical way to use SFML in a foreign language is via the C ABI with [CSFML](https://github.com/SFML/CSFML). However, this project makes the choice to use the C++ ABI directly.

The use of the C++ API can be motivated by the fact that both C++ and Scala revolve around an object-oriented type system; binding the two directly is in that sense more straightforward than using the C API indirection, and allows to transport more features of the library into Scala.

On the other hand, the C++ ABI is largely unstable, and this project is also a pretext to experiment with the internals of the language.

As a result, this library is, for now, only compatible with the `x86_64-linux-gnu` platform.
