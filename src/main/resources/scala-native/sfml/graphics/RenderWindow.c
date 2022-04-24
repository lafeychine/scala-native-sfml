#include <SFML/Graphics.h>

sfRenderWindow* sfRenderWindow_create_fix(const sfVideoMode *mode, const char* title, sfUint32 style, const sfContextSettings* settings) {
    return sfRenderWindow_create(*mode, title, style, settings);
}

void sfRenderWindow_setSize_fix(sfRenderWindow* renderWindow, const sfVector2u* size) {
    sfRenderWindow_setSize(renderWindow, *size);
}

void sfRenderWindow_clear_fix(sfRenderWindow* renderWindow, const sfColor* color) {
    sfRenderWindow_clear(renderWindow, *color);
}
