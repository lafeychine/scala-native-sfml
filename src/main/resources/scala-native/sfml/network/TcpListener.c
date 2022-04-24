#include <SFML/Network.h>

sfTcpSocket* sfTcpListener_accept_fix(sfTcpListener* listener)
{
    sfTcpSocket* connected;

    if (sfTcpListener_accept(listener, &connected) != sfSocketDone) {
        return NULL;
    }
    return connected;
}

sfSocketStatus sfTcpListener_listen_fix(sfTcpListener* listener, unsigned short port, sfIpAddress* address) {
    return sfTcpListener_listen(listener, port, *address);
}
