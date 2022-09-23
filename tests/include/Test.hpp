#ifndef __TEST_H_
#define __TEST_H_

#include <csignal>

#define SECTION_NAME ".sn_test"

#define SN_Test(name)                                                 \
    static void name(TestScreen &);                                   \
    static const sn_data_t __sn_##name                                \
        __attribute__((section(SECTION_NAME), used)) = {#name, name}; \
    static void name(TestScreen & SN_TestScreen)

class TestScreen
{
  public:
    TestScreen(pid_t pid);
    void takeScreenshot();

  private:
    pid_t _pid;
    sigset_t _mask;
};

typedef struct {
    char const * name;
    void (*fptr)(TestScreen &);
} sn_data_t;

#endif /* __TEST_H_ */
