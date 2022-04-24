#include <SFML/Network.h>

void sfTcpSocket_getRemoteAddress_fix(const sfTcpSocket* socket, sfIpAddress* address) {
    *address = sfTcpSocket_getRemoteAddress(socket);
}

sfSocketStatus sfTcpSocket_connect_fix(sfTcpSocket* socket, sfIpAddress* remoteAddress, unsigned short remotePort, sfTime* timeout) {
    return sfTcpSocket_connect(socket, *remoteAddress, remotePort, *timeout);
}
