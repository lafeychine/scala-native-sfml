#include <SFML/Network.h>

sfBool sfSocketSelector_wait_fix(sfSocketSelector* selector, sfTime* timeout) {
    return sfSocketSelector_wait(selector, *timeout);
}
