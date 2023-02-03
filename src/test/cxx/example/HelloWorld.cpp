#include "GraphicalTest.hpp"

#include <SFML/Graphics.hpp>

snGraphicalTest(HelloWorld)
{
    // Create the main window
    sf::RenderWindow window(sf::VideoMode(1024, 768), "Test");

    // Load a sprite to display
    sf::Texture texture;
    texture.loadFromFile("src/test/resources/sfml.png");

    sf::Sprite sprite(texture);

    // Start the game loop
    window.isOpen();

    // Process events
    sf::Event event;
    while (window.pollEvent(event)) {}

    // Clear screen
    window.clear(sf::Color(0x01, 0x23, 0x45));
    window.display();
    snTestScreen.takeScreenshot();

    // Draw the sprite
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // Close window
    window.close();
}
