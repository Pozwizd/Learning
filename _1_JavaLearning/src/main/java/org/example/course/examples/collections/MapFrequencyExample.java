package org.example.course.examples.collections;

import java.util.HashMap;
import java.util.Map;

public class MapFrequencyExample {
    public static void main(String[] args) {
        String[] words = {"java", "stream", "java", "list"};
        Map<String, Integer> frequency = new HashMap<>();

        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        System.out.println(frequency);
    }
}
