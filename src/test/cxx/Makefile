override SRC			=	Main.cpp				\
					TestScreen.cpp				\
					$(shell find tests -name '*.cpp')

BUILDDIR			=	../../../target

override OBJ			=	$(SRC:%.cpp=$(BUILDDIR)/cxx/%.o)

CXX				=	clang++
override CPPFLAGS		+=	-I .
override CXXFLAGS		+=	-std=gnu++17 -W -Wall
override RMFLAGS		+=	-r

LD				=	$(CXX)
override LDFLAGS		+=	-no-pie
override LDLIBS			+=	-lsfml-graphics -lsfml-system -lsfml-window

NAME				=	$(BUILDDIR)/cxx/scala-native-sfml-test-out
PATCH				=	$(BUILDDIR)/libpatch.so


all:				$(NAME) $(PATCH)

$(NAME):			$(OBJ)
				$(LD) $(LDFLAGS) $(LDLIBS) $^ -o $@

$(PATCH):			../patch.cpp
				$(LD) -shared $^ -o $@

$(BUILDDIR)/cxx/%.o:		override CPPFLAGS += -MT $@ -MMD -MP -MF $(@:.o=.d)
$(BUILDDIR)/cxx/%.o:		%.cpp
				@mkdir -p $(@D)
		            	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $< -o $@

clean:
				$(RM) $(RMFLAGS) $(BUILDDIR)/cxx $(BUILDDIR)/libpatch.so $(NAME)

.PHONY:				all clean

-include $(OBJ:.o=.d)
