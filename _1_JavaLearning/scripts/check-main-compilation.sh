#!/usr/bin/env bash
set -euo pipefail

SRC_ROOT="_1_JavaLearning/src/main/java"
OUT_DIR="_1_JavaLearning/.out/compile-check"

mkdir -p "$OUT_DIR"
mapfile -t SOURCES < <(rg --files "$SRC_ROOT" -g '*.java')

if [ "${#SOURCES[@]}" -eq 0 ]; then
  echo "No Java sources found in $SRC_ROOT"
  exit 1
fi

javac -encoding UTF-8 -d "$OUT_DIR" "${SOURCES[@]}"
echo "Compilation OK: ${#SOURCES[@]} files"
