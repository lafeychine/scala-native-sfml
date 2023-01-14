#include <stdio.h>

extern "C" void test(long * ptr)
{
    printf("???: %p\n", ptr[0]);
}
