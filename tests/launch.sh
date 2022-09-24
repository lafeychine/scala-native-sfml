#!/usr/bin/env bash

DIR=$(dirname "$0")
SCALA_VERSION="3.2.0"

PROGRAM_SCALA=${DIR}/target/scala-${SCALA_VERSION}/tests-out
PROGRAM_CXX=${DIR}/target/cxx/tests-out

function getXFreeSlot() {
    local N=1

    while [[ -f /tmp/.X$N-lock ]]; do
        N=$((N + 1))
    done

    echo $N
}

function startXServer() {
    AUTHFILE=$(mktemp -p "${TMPDIR}" Xauthority.XXXXXX)

    XAUTHORITY=$AUTHFILE xauth source - << EOF
add :${DISPLAY} . $(mcookie)
EOF

    XAUTHORITY=$AUTHFILE Xvfb ":${DISPLAY}" -screen 0 1024x768x24 -nolisten tcp &>/dev/null &
    
    XVFB_PID=$!

    sleep 1
    #while ! DISPLAY=:${DISPLAY} xset q &>/dev/null; do
#	echo "Sleep"
 #       sleep 0.1
  #  done
}

function launchTests() {
    find "${DIR}/src/main/cxx/tests" -name "*.cpp" -print0 |
        while IFS= read -r -d '' FILE; do

            FILENAME_TEST=${FILE##*/}

            launchTest "${FILENAME_TEST%.cpp}" || return 1

        done

    FAILED_TEST=$?
}

function launchTest() {
    mkdir "${TMPDIR}/cxx" "${TMPDIR}/scala"

    echo -n " - $1: "

    executeProgram "${PROGRAM_SCALA}" "${TMPDIR}/cxx" "$1" || return 1
    executeProgram "${PROGRAM_CXX}" "${TMPDIR}/scala" "$1" || return 1

    compareOutputs || return 1

    tput setaf 2; echo "OK"; tput sgr0

    rm -rf "${TMPDIR}/cxx" "${TMPDIR}/scala"
}

function executeProgram() {
    if DISPLAY=:${DISPLAY} "$@" >"$2_stdout" 2>"${TMPDIR}/stderr"; then
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

    XAUTHORITY=$AUTHFILE xauth remove ":${DISPLAY}"
}


export LSAN_OPTIONS=suppressions=${DIR}/leak.txt

DISPLAY="$(getXFreeSlot)"
TMPDIR="$(mktemp --directory --tmpdir tests.XXXXXX)"

startXServer

launchTests

closeXServer

rm -rf "${TMPDIR}"

[[ ${FAILED_TEST} -ne 0 ]] && exit 1 || exit 0
