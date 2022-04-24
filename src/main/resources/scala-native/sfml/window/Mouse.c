#include <SFML/Window.h>

void sfMouse_getPosition_fix(const sfWindow* relativeTo, sfVector2i* position) {
    *position = sfMouse_getPosition(relativeTo);
}

void sfMouse_setPosition_fix(const sfVector2i* position, const sfWindow* relativeTo) {
    sfMouse_setPosition(*position, relativeTo);
}
