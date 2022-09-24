override SRC			=	src/main/cxx/Main.cpp					\
					src/main/cxx/TestScreen.cpp				\
					$(shell find src/main/cxx/tests -name '*.cpp')

BUILDDIR			=	target/cxx

override OBJ			=	$(SRC:%.cpp=$(BUILDDIR)/%.o)

CXX				=	clang++
override CPPFLAGS		+=	-Iinclude
override CXXFLAGS		+=	-std=gnu++17 -W -Wall
override RMFLAGS		+=	-r

LD				=	$(CXX)
override LDFLAGS		+=	-lsfml-graphics -lsfml-system -lsfml-window

NAME				=	target/cxx/tests-out


all:				$(NAME)

$(NAME):			$(OBJ)
				$(LD) $(LDFLAGS) $(LDLIBS) $^ -o $@


.SECONDEXPANSION:
$(BUILDDIR)/%.o:		override CPPFLAGS += -MT $@ -MMD -MP -MF $(@:.o=.d)
$(BUILDDIR)/%.o:		%.cpp | $$(@D)/.
		            	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -c $< -o $@

$(BUILDDIR)/.:
				mkdir -p $@

$(BUILDDIR)%/.:
				mkdir -p $@

clean:
				$(RM) $(RMFLAGS) $(BUILDDIR) $(NAME)

.PRECIOUS:			$(BUILDDIR)/. $(BUILDDIR)%/.

.PHONY:				all clean

-include $(OBJ:.o=.d)
