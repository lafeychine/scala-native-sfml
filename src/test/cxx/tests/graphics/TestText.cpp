#include "Test.hpp"

#include <SFML/Graphics.hpp>
#include <iomanip>
#include <iostream>

snTest(TestText)
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

    // sf::Text::getGlobalBounds
    auto globalBounds = text.getGlobalBounds();

    std::cout << std::fixed << std::setprecision(1)
              << "Global bounds: ("
              << globalBounds.left << ", "
              << globalBounds.top << ", "
              << globalBounds.width << ", "
              << globalBounds.height << ")" << std::endl;

    // Teardown
    window.close();
}
