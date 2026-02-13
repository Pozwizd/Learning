package org.example.course.examples.streams;

import java.util.List;

public class StreamPipelineExample {
    public static void main(String[] args) {
        List<String> result = List.of(" java ", "sql", "stream", "api")
                .stream()
                .map(String::trim)
                .filter(s -> s.length() >= 4)
                .map(String::toUpperCase)
                .toList();

        System.out.println(result);
    }
}
