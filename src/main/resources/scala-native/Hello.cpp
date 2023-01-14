#include <SFML/Graphics.hpp>
#include <SFML/System.hpp>

extern "C" void test(const sf::RenderStates &);

extern "C" void toto(void * ptr)
{
    printf("Should: %p\n", sf::RenderStates::Default);
    printf("Got: %p\n", ptr);
    test(sf::RenderStates::Default);
}
