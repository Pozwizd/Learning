#!/usr/bin/env bash
set -euo pipefail

SRC_ROOT="_1_JavaLearning/src/main/java"
OUT_DIR="_1_JavaLearning/.out/offline-assignments"
TMP_DIR="_1_JavaLearning/.out/tmp"

mkdir -p "$OUT_DIR" "$TMP_DIR"
mapfile -t SOURCES < <(rg --files "$SRC_ROOT" -g '*.java')

javac -encoding UTF-8 -d "$OUT_DIR" "${SOURCES[@]}"

cat > "$TMP_DIR/OfflineAssignmentsCheck.java" <<'JAVA'
import java.util.List;
import java.util.Map;
import org.example.course.assignments.collections.FrequencyCounter;
import org.example.course.assignments.operators.ScoreCalculator;
import org.example.course.assignments.streams.TextNormalizer;

public class OfflineAssignmentsCheck {
    public static void main(String[] args) {
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        assertEquals(12, scoreCalculator.evaluateScore(10, 5, true), "score with penalty");
        assertEquals(0, scoreCalculator.evaluateScore(1, 1, true), "score cannot be negative");

        FrequencyCounter frequencyCounter = new FrequencyCounter();
        assertEquals(2, frequencyCounter.countWords(List.of("java", "sql", "java")).get("java"), "frequency java");
        assertEquals(Map.of(), frequencyCounter.countWords(List.of()), "frequency empty");
        assertEquals(Map.of(), frequencyCounter.countWords(null), "frequency null");

        TextNormalizer textNormalizer = new TextNormalizer();
        assertEquals(List.of("JAVA", "SQL"), textNormalizer.normalize(List.of("  java ", "go", " sql ")), "normalize regular");
        assertEquals(List.of(), textNormalizer.normalize(List.of()), "normalize empty");
        assertEquals(List.of(), textNormalizer.normalize(null), "normalize null");

        System.out.println("Offline assignments check: OK");
    }

    private static void assertEquals(Object expected, Object actual, String scenario) {
        if ((expected == null && actual != null) || (expected != null && !expected.equals(actual))) {
            throw new AssertionError("Assertion failed for " + scenario + ". Expected=" + expected + ", actual=" + actual);
        }
    }
}
JAVA

javac -encoding UTF-8 -cp "$OUT_DIR" -d "$OUT_DIR" "$TMP_DIR/OfflineAssignmentsCheck.java"
java -cp "$OUT_DIR" OfflineAssignmentsCheck
