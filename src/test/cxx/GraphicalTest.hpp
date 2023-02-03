#ifndef __GRAPHICAL_TEST_H_
#define __GRAPHICAL_TEST_H_

#include <filesystem>

#define HEADER_SIZE "3184"
#define SCREENSHOT_CMD "xwd -root -silent | dd bs=" HEADER_SIZE " iflag=fullblock skip=1 status=none of="

#define SECTION_NAME ".sn_test"

#define snGraphicalTest(name)                                         \
    static void name(TestScreen &);                                   \
    static const sn_data_t __sn_##name                                \
        __attribute__((section(SECTION_NAME), used)) = {#name, name}; \
    static void name(TestScreen & snTestScreen)

class TestScreen
{
  public:
    TestScreen(char const * path);
    void takeScreenshot();

  private:
    const std::filesystem::path _path;
    int _id_screenshot;
};

typedef struct {
    char const * name;
    void (*fptr)(TestScreen &);
} sn_data_t;

#endif /* __GRAPHICAL_TEST_H_ */
