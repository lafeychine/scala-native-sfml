#include "GraphicalTest.hpp"

#include <cstdlib>

TestScreen::TestScreen(char const * path)
    : _path(path), _id_screenshot(0)
{
}

void TestScreen::takeScreenshot()
{
    std::stringstream cmd;

    cmd << SCREENSHOT_CMD
        << _path.string() << "/"
        << "screenshot_" << _id_screenshot;

    (void)system(cmd.str().c_str());

    _id_screenshot = _id_screenshot + 1;
}
