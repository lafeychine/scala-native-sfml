#include "Test.hpp"

#include <SFML/Graphics.hpp>
#include <iostream>

snTest(Example)
{
    sf::Event event;
    sf::RenderWindow window(sf::VideoMode(1024, 768), "Test");

    window.isOpen();
    while (window.pollEvent(event)) {}

    window.clear(sf::Color(0x01, 0x23, 0x45, 0x67));
    window.display();
    snTestScreen.takeScreenshot();

    window.close();
}
