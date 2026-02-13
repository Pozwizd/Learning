#!/usr/bin/env bash
set -euo pipefail

if [ "$#" -ne 1 ]; then
  echo "Usage: $0 <fully.qualified.MainClass>"
  echo "Example: $0 org.example.course.examples.operators.OperatorPrecedenceExample"
  exit 1
fi

MAIN_CLASS="$1"
SRC_ROOT="_1_JavaLearning/src/main/java"
OUT_DIR="_1_JavaLearning/.out/examples"

MAIN_FILE="$SRC_ROOT/${MAIN_CLASS//./\/}.java"
if [ ! -f "$MAIN_FILE" ]; then
  echo "Main class source file not found: $MAIN_FILE"
  exit 1
fi

mkdir -p "$OUT_DIR"

# Compile all main sources so inter-package imports work reliably.
mapfile -t SOURCES < <(rg --files "$SRC_ROOT" -g '*.java')
javac -encoding UTF-8 -d "$OUT_DIR" "${SOURCES[@]}"

java -cp "$OUT_DIR" "$MAIN_CLASS"
