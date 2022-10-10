override SRC			=	src/main/cxx/Main.cpp					\
					src/main/cxx/TestScreen.cpp				\
					$(shell find src/main/cxx/tests -name '*.cpp')

BUILDDIR			=	target

override OBJ			=	$(SRC:%.cpp=$(BUILDDIR)/cxx/%.o)

CXX				=	clang++
override CPPFLAGS		+=	-I src/main/cxx
override CXXFLAGS		+=	-std=gnu++17 -W -Wall
override RMFLAGS		+=	-r

LD				=	$(CXX)
override LDFLAGS		+=	-no-pie
override LDLIBS			+=	-lsfml-graphics -lsfml-system -lsfml-window

NAME				=	$(BUILDDIR)/cxx/tests-out
PATCH				=	$(BUILDDIR)/libpatch.so


all:				$(NAME) $(PATCH)

$(NAME):			$(OBJ)
				$(LD) $(LDFLAGS) $(LDLIBS) $^ -o $@

$(PATCH):			patch.cpp
				$(LD) -shared $^ -o $@

$(BUILDDIR)/cxx/%.o:		override CPPFLAGS += -MT $@ -MMD -MP -MF $(@:.o=.d)
$(BUILDDIR)/cxx/%.o:		%.cpp
				@mkdir -p $(@D)
		            	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $< -o $@

clean:
				$(RM) $(RMFLAGS) $(BUILDDIR) $(NAME)

.PHONY:				all clean

-include $(OBJ:.o=.d)
