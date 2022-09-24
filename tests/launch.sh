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

    XAUTHORITY=$AUTHFILE Xvfb ":$DISPLAY" -screen 0 1024x768x24 -nolisten tcp &>/dev/null &
    
    XVFB_PID=$!

    # TODO: Wait X server
    sleep 1
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
    mkdir "${TMPDIR}/screenshot" "${TMPDIR}/screenshot/cxx" "${TMPDIR}/screenshot/scala"

    echo -n " - $1: "

    executeProgram "${PROGRAM_SCALA}" "${TMPDIR}/screenshot" "$1" || return 1
    executeProgram "${PROGRAM_CXX}" "${TMPDIR}/screenshot" "$1" || return 1

    compareScreenshot || return 1

    tput setaf 2; echo "OK"; tput sgr0

    rm -rf "${TMPDIR}/screenshot"
}

function executeProgram() {
    DISPLAY=:${DISPLAY} "$@" >"${TMPDIR}/stdout" 2>"${TMPDIR}/stderr"

    # TODO: Check output

    return 0
}

function compareScreenshot() {
    if DIFF_OUTPUT=$(diff -q "${TMPDIR}/screenshot/cxx" "${TMPDIR}/screenshot/scala"); then
        return 0
    fi

    tput setaf 1; echo "KO"; tput sgr0
    echo "${DIFF_OUTPUT}"

    return 1
}

function closeXServer() {
    kill ${XVFB_PID}
    wait ${XVFB_PID}

    XAUTHORITY=$AUTHFILE xauth remove ":$DISPLAY"
}


DISPLAY=$(getXFreeSlot)
TMPDIR="$(mktemp --directory --tmpdir tests.XXXXXX)"

startXServer

launchTests

closeXServer

rm -rf "${TMPDIR}"

[[ ${FAILED_TEST} -ne 0 ]] && exit 1 || exit 0
