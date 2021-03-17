# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

include_directories(third_party)
include_directories(src/main/cpp/)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        audio-native-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        #${audiosupport_sources}
        # main game files
        src/main/cpp/audio-support.cpp
        src/main/cpp/main/AudioPlayer.cpp

        # audio engine
        src/main/cpp/audio/AAssetDataSource.cpp
        src/main/cpp/audio/Player.cpp

        # UI engine
        src/main/cpp/ui/OpenGLFunctions.cpp

        # utility functions
        src/main/cpp/utils/logging.h
        src/main/cpp/utils/UtilityFunctions.cpp
        )


set (TARGET_LIBS log android oboe GLESv2)
MESSAGE(STATUS "Using NDK media extractor")
add_definitions(-DUSE_FFMPEG=0)
target_sources( audio-native-lib PRIVATE src/main/cpp/audio/NDKExtractor.cpp )
set (TARGET_LIBS ${TARGET_LIBS} mediandk)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
target_link_libraries( audio-native-lib ${TARGET_LIBS} )

# Set the path to the Oboe directory.
# TODO change this to where you downloaded oboe library
set (OBOE_DIR /Users/MyUser/Documents/repos/android_audio/oboe)
# Add the Oboe library as a subdirectory in your project.
# add_subdirectory tells CMake to look in this directory to
# compile oboe source files using oboe's CMake file.
# ./oboe specifies where the compiled binaries will be stored
add_subdirectory (${OBOE_DIR} ./oboe-bin)



target_compile_options(audio-native-lib
        PRIVATE -std=c++14 -Wall -Werror "$<$<CONFIG:RELEASE>:-Ofast>")