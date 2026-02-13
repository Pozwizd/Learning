package org.example.course.assignments;

import java.util.List;
import java.util.Map;
import org.example.course.assignments.collections.FrequencyCounter;
import org.example.course.assignments.operators.ScoreCalculator;
import org.example.course.assignments.streams.TextNormalizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseAssignmentsTest {

    @Test
    void scoreCalculatorShouldApplyPenalty() {
        ScoreCalculator calculator = new ScoreCalculator();
        assertEquals(12, calculator.evaluateScore(10, 5, true));
        assertEquals(15, calculator.evaluateScore(10, 5, false));
        assertEquals(0, calculator.evaluateScore(1, 1, true));
    }

    @Test
    void frequencyCounterShouldCountWords() {
        FrequencyCounter counter = new FrequencyCounter();
        Map<String, Integer> actual = counter.countWords(List.of("java", "sql", "java"));
        assertEquals(2, actual.get("java"));
        assertEquals(1, actual.get("sql"));
    }

    @Test
    void frequencyCounterShouldReturnEmptyMapForEmptyInput() {
        FrequencyCounter counter = new FrequencyCounter();
        assertEquals(Map.of(), counter.countWords(List.of()));
    }

    @Test
    void frequencyCounterShouldReturnEmptyMapForNullInput() {
        FrequencyCounter counter = new FrequencyCounter();
        assertEquals(Map.of(), counter.countWords(null));
    }

    @Test
    void textNormalizerShouldTrimFilterAndUppercase() {
        TextNormalizer normalizer = new TextNormalizer();
        List<String> actual = normalizer.normalize(List.of("  java ", "go", " sql "));
        assertEquals(List.of("JAVA", "SQL"), actual);
    }

    @Test
    void textNormalizerShouldHandleEmptyInput() {
        TextNormalizer normalizer = new TextNormalizer();
        assertEquals(List.of(), normalizer.normalize(List.of()));
    }

    @Test
    void textNormalizerShouldHandleNullInput() {
        TextNormalizer normalizer = new TextNormalizer();
        assertEquals(List.of(), normalizer.normalize(null));
    }
}
