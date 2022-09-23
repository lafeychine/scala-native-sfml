#include "Test.hpp"

#include <csignal>

TestScreen::TestScreen(pid_t pid)
    : _pid(pid)
{
    signal(SIGUSR2, [](int) {});
    sigfillset(&(_mask));
    sigdelset(&(_mask), SIGUSR2);
}

void TestScreen::takeScreenshot()
{
    kill(_pid, SIGUSR1);
    sigsuspend(&(_mask));
}
