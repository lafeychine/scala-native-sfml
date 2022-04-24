#include <SFML/Graphics.h>

void sfText_getColor_fix(const sfText* text, sfColor* color) {
    *color = sfText_getColor(text);
}

void sfText_setColor_fix(sfText* text, const sfColor* color) {
    sfText_setColor(text, *color);
}

void sfText_getGlobalBounds_fix(const sfText* text, sfFloatRect* rect) {
    *rect = sfText_getGlobalBounds(text);
}

void sfText_getPosition_fix(const sfText* text, sfVector2f* position) {
    *position = sfText_getPosition(text);
}

void sfText_setPosition_fix(sfText* text, const sfVector2f* position) {
    sfText_setPosition(text, *position);
}
