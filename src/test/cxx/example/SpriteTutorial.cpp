#include "GraphicalTest.hpp"

#include <SFML/Graphics.hpp>

snGraphicalTest(SpriteTutorial)
{
    // Setup
    sf::RenderWindow window(sf::VideoMode(1024, 768), "My window");

    sf::Texture texture;
    texture.loadFromFile("src/test/resources/sfml.png");

    sf::Sprite sprite(texture);

    window.isOpen();

    // Control test
    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Texture::setSmooth
    texture.setSmooth(true);

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Texture::setRepeated
    texture.setRepeated(true);

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Sprite::setTextureRect
    sprite.setTextureRect(sf::IntRect(100, 100, 600, 400));

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Sprite::setColor
    sprite.setColor(sf::Color(0, 255, 0));

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Sprite::setColor
    sprite.setColor(sf::Color(255, 255, 255, 128));

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Sprite::setPosition
    sprite.setPosition(100, 50);

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Sprite::move
    sprite.move(50, 20);

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Sprite::setRotation
    sprite.setRotation(90);

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Sprite::rotate
    sprite.rotate(15);

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Sprite::setScale
    sprite.setScale(0.5, 2);

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Sprite::scale
    sprite.scale(1.5, 3);

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Sprite::setOrigin
    sprite.setOrigin(25, 25);

    window.clear();
    window.draw(sprite);
    window.display();
    snTestScreen.takeScreenshot();
}
