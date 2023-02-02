#!/usr/bin/env bash

DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &>/dev/null && pwd )

PATCH=${DIR}/target/libpatch.so
PROGRAM_CXX=${DIR}/target/cxx/scala-native-sfml-test-out

while [[ $# -gt 0 ]]; do
    case $1 in
        --fast)
            SBT_FLAGS='set nativeLTO := "none"; set nativeMode := "debug"'
            shift;;

        -h|--help|*)
            echo "USAGE: $0 [--fast]"
            exit 0;;
    esac
done

function checkCommand() {
    command -v "$1" >/dev/null 2>&1 || {
        echo >&2 "$1 isn't installed. Aborting."
        exit 1
    }
}

function getXFreeSlot() {
    local N=1

    while [[ -f /tmp/.X$N-lock ]]; do
        N=$((N + 1))
    done

    echo $N
}

function startXServer() {
    local ATTEMPT=0

    AUTHFILE=$(mktemp -p "${SNSFML_SCREENSHOT_FOLDER_PATH}" Xauthority.XXXXXX)

    XAUTHORITY=$AUTHFILE xauth source - << EOF
add ${DISPLAY} . 00000000000000000000000000000000
EOF

    XAUTHORITY=$AUTHFILE Xvfb "${DISPLAY}" -screen 0 1024x768x24 -nolisten tcp &>/dev/null &
    
    XVFB_PID=$!

    while ! xset q &>/dev/null; do
        [[ ${ATTEMPT} -eq 30 ]] && {
            echo >&2 "Cannot open X server. Aborting."
            exit 1
        }

        sleep 0.1

        ATTEMPT=$((ATTEMPT + 1))
    done
}

function closeXServer() {
    kill ${XVFB_PID}
    wait ${XVFB_PID}

    XAUTHORITY=$AUTHFILE xauth remove "${DISPLAY}"
}

for cmd in cat clang++ diff dirname find make mkdir mktemp rm sbt sleep tput xauth xset Xvfb; do
    checkCommand ${cmd}
done

([[ -f ${PROGRAM_CXX} ]] && [[ -f ${PATCH} ]]) || { make -C src/test/cxx || exit 1; }

export DISPLAY=":$(getXFreeSlot)"
export LD_PRELOAD="${PATCH}"
export SNSFML_SCREENSHOT_FOLDER_PATH="$(mktemp --directory --tmpdir tests.XXXXXX)"

startXServer

sbt "${SBT_FLAGS}" test || exit 1

closeXServer

rm -rf "${SNSFML_SCREENSHOT_FOLDER_PATH}"
