cmake_minimum_required(VERSION 3.10)
project(lab1)

set(CMAKE_CXX_STANDARD 17)

set(CMAKE_CXX_STANDARD_LIBRARIES "-static-libgcc -static-libstdc++ -lwsock32 -lws2_32 ${CMAKE_CXX_STANDARD_LIBRARIES}")


set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -Wl,-Bstatic,--whole-archive -lwinpthread -Wl,--no-whole-archive")

add_executable(lab1
        model/node.cpp
        model/man.cpp
        view/main.cpp
        view/painter.cpp
        controller/controller.cpp
        model/iter_line.cpp
        view/painter.h
        controller/controller.h
        model/line.h
        model/line.cpp
        model/chaos.cpp
        model/seller.cpp
        model/chaos.h
        model/iter_line.h
        model/seller.h
        model/queue.h
        model/Node.h
        model/logic.cpp
        model/logic.h)
