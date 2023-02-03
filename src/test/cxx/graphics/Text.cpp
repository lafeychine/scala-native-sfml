#include "GraphicalTest.hpp"

#include <SFML/Graphics.hpp>

snGraphicalTest(Text)
{
    // Setup
    sf::RenderWindow window(sf::VideoMode(1024, 768), "Test");

    sf::Font font;
    font.loadFromFile("src/test/resources/tuffy.ttf");

    sf::Text text("Hello World", font, 50);

    window.isOpen();

    // Control test
    window.clear();
    window.draw(text);
    window.display();
    snTestScreen.takeScreenshot();

    // sf::Text::setPosition
    text.setPosition(100, 100);

    window.clear();
    window.draw(text);
    window.display();
    snTestScreen.takeScreenshot();

    // Teardown
    window.close();
}
