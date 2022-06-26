import sfml.graphics.*
import sfml.system.*
import sfml.window.*

@main def main =
    val videoMode = VideoMode(1024, 768, 32)
    val window = RenderWindow(videoMode, "Test", Window.WindowStyle.DefaultStyle)

    window.framerateLimit = 30

    while window.isOpen() do
        window.clear(Color.Black())

        for event <- window.pollEvent() do
            println(event)

        if (Mouse.Button.Right.isPressed()) {
            println("HERE")
        }

        window.display()
