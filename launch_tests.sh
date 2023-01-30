#!/usr/bin/env bash

PROGRAM_NAME=scala-native-sfml-test-out
SCALA_VERSION="3.2.1"

DIR=$(dirname "$0")

PROGRAM_CXX=${DIR}/target/cxx/${PROGRAM_NAME}
PROGRAM_SCALA=${DIR}/target/scala-${SCALA_VERSION}/${PROGRAM_NAME}
PATCH=${DIR}/target/libpatch.so

while [[ $# -gt 0 ]]; do
    case $1 in
        --discard-leaks)
            DISCARD_LEAKS="exitcode=0"
            shift;;

        --only)
            REGEXP="$2"
            shift; shift;;

        -h|--help|*)
            echo "USAGE: $0 [--discard-leaks] [--only regexp]"
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

    AUTHFILE=$(mktemp -p "${TMPDIR}" Xauthority.XXXXXX)

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

function launchTests() {
    find "${DIR}/src/test/cxx/tests" -name "*.cpp" -print0 |
        while IFS= read -r -d '' FILE; do

            FILENAME_TEST=${FILE##*/}
            FILENAME_TEST=${FILENAME_TEST%.cpp}

            [[ ${REGEXP} ]] && [[ ! ${FILENAME_TEST} =~ ${REGEXP} ]] && continue

            launchTest "${FILENAME_TEST}" || return 1

        done

    FAILED_TEST=$?
}

function launchTest() {
    mkdir "${TMPDIR}/cxx" "${TMPDIR}/scala"

    echo -n " - $1: "

    executeProgram "${PROGRAM_CXX}" "${TMPDIR}/cxx" "$1" || return 1
    executeProgram "${PROGRAM_SCALA}" "${TMPDIR}/scala" "$1" || return 1

    compareOutputs || return 1

    tput setaf 2; echo "OK"; tput sgr0

    rm -rf "${TMPDIR}/cxx" "${TMPDIR}/scala"
}

function executeProgram() {
    if LD_PRELOAD="${PATCH}" "$@" >"$2_stdout" 2>"${TMPDIR}/stderr"; then
        return 0
    fi

    tput setaf 1; echo "KO"; tput sgr0
    cat "${TMPDIR}/stderr"

    return 1
}

function compareOutputs() {
    if DIFF_OUTPUT=$(diff -q "${TMPDIR}/cxx" "${TMPDIR}/scala"); then
        if DIFF_OUTPUT=$(diff -aB "${TMPDIR}/cxx_stdout" "${TMPDIR}/scala_stdout"); then
            return 0
        fi
    fi

    tput setaf 1; echo "KO"; tput sgr0
    echo "${DIFF_OUTPUT}"

    return 1
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
[[ -f ${PROGRAM_SCALA} ]] || { sbt test || exit 1; }

export DISPLAY=":$(getXFreeSlot)"
export LSAN_OPTIONS=${DISCARD_LEAKS}:suppressions=${DIR}/src/test/leak.txt

TMPDIR="$(mktemp --directory --tmpdir tests.XXXXXX)"

startXServer

launchTests

closeXServer

rm -rf "${TMPDIR}"

[[ ${FAILED_TEST} -ne 0 ]] && exit 1 || exit 0
