package org.example.course.examples.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingExample {
    public static void main(String[] args) {
        Map<Integer, List<String>> groupedByLength = List.of("cat", "tiger", "dog", "lion")
                .stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println(groupedByLength);
    }
}
