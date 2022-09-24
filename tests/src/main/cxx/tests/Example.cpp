#include "Test.hpp"

#include <SFML/Graphics.hpp>
#include <iostream>

snTest(Example)
{
    sf::RenderWindow window(sf::VideoMode(1024, 768), "Test");

    window.isOpen();

    sf::Event event;

    while (window.pollEvent(event)) {
        // std::cout << event << std::endl;
    }

    window.clear(sf::Color(0x01, 0x23, 0x45, 0x67));
    window.display();
    snTestScreen.takeScreenshot();
    snTestScreen.takeScreenshot();

    window.close();
}
