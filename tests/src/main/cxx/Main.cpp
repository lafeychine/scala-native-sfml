#include "Test.hpp"

#include <cstring>
#include <fcntl.h>
#include <iostream>
#include <link.h>
#include <sys/mman.h>
#include <unistd.h>

#define PROGRAM_PATH "/proc/self/exe"

#define assert(cond)                                 \
    if (!(cond)) {                                   \
        std::perror("Assertion failed (" #cond ")"); \
        return EXIT_FAILURE;                         \
    }

int main(int argc, char * argv[])
{
    assert(argc == 3);

    TestScreen snTestScreen(argv[1]);

    int fd = open(PROGRAM_PATH, O_RDONLY);
    assert(fd != -1);

    off_t size = lseek(fd, 0, SEEK_END);
    void * data = mmap(NULL, size, PROT_READ, MAP_PRIVATE, fd, 0);
    assert(data != MAP_FAILED);

    ElfW(Ehdr) * elf = (ElfW(Ehdr) *)data;
    ElfW(Shdr) * shdr = (ElfW(Shdr) *)((char *)data + elf->e_shoff);
    char const * strtab = (char *)data + shdr[elf->e_shstrndx].sh_offset;

    for (int i = 0; i < elf->e_shnum; i++) {
        char const * name = &(strtab[shdr[i].sh_name]);

        if (strcmp(name, SECTION_NAME)) {
            continue;
        }

        const ElfW(Shdr) * hdr = shdr + i;
        sn_data_t * sn_data = (sn_data_t *)hdr->sh_addr;

        for (size_t j = 0; j < hdr->sh_size / sizeof(sn_data_t); j++) {
            if (strcmp(sn_data[j].name, argv[2])) {
                continue;
            }

            sn_data[j].fptr(snTestScreen);
        }
    }

    munmap(data, size);
    close(fd);
    return EXIT_SUCCESS;
}
