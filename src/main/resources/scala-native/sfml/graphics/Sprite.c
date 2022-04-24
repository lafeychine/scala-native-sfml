#include <SFML/Graphics.h>

void sfSprite_getColor_fix(const sfSprite* sprite, sfColor* color) {
    *color = sfSprite_getColor(sprite);
}

void sfSprite_setColor_fix(sfSprite* sprite, const sfColor* color) {
    sfSprite_setColor(sprite, *color);
}

void sfSprite_getGlobalBounds_fix(const sfSprite* sprite, sfFloatRect *rect) {
    *rect = sfSprite_getGlobalBounds(sprite);
}

void sfSprite_getOrigin_fix(const sfSprite* sprite, sfVector2f* origin) {
    *origin = sfSprite_getOrigin(sprite);
}

void sfSprite_setOrigin_fix(sfSprite* sprite, const sfVector2f* origin) {
    sfSprite_setOrigin(sprite, *origin);
}

void sfSprite_getPosition_fix(const sfSprite* sprite, sfVector2f* position) {
    *position = sfSprite_getPosition(sprite);
}

void sfSprite_setPosition_fix(sfSprite* sprite, const sfVector2f* position) {
    sfSprite_setPosition(sprite, *position);
}

void sfSprite_getScale_fix(const sfSprite* sprite, sfVector2f* scale) {
    *scale = sfSprite_getScale(sprite);
}

void sfSprite_setScale_fix(sfSprite* sprite, const sfVector2f* scale) {
    sfSprite_setScale(sprite, *scale);
}

void sfSprite_getTextureRect_fix(const sfSprite* sprite, sfIntRect* rectangle) {
    *rectangle = sfSprite_getTextureRect(sprite);
}

void sfSprite_setTextureRect_fix(sfSprite* sprite, const sfIntRect* rectangle) {
    sfSprite_setTextureRect(sprite, *rectangle);
}
