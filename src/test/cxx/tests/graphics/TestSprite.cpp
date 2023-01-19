#include "Test.hpp"

#include <SFML/Graphics.hpp>

snTest(TestSprite)
{
    // Setup
    sf::RenderWindow window(sf::VideoMode(1024, 768), "Test");

    sf::Texture texture;
    texture.loadFromFile("src/test/resources/sfml.png");

    sf::Sprite sprite(texture);

    window.isOpen();

    // Control test
    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Sprite::setPosition
    sprite.setPosition(100, 100);

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // Teardown
    window.close();
}
