package org.example.course.assignments.streams;

import java.util.Collections;
import java.util.List;

public class TextNormalizer {

    public List<String> normalize(List<String> input) {
        if (input == null || input.isEmpty()) {
            return Collections.emptyList();
        }

        return input.stream()
                .map(String::trim)
                .filter(s -> s.length() >= 3)
                .map(String::toUpperCase)
                .toList();
    }
}
