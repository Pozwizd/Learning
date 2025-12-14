package org.example.Java._3_Algorithms.test;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>(Arrays.asList(
                "name", "cat", "Room", "tool", "pool", "Pocket"
        ));

        List<String> result = input.stream()
                .filter(s -> !s.toLowerCase().startsWith("p"))  // Прибрати рядки на P/p
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))  // Капіталізація
                .collect(Collectors.toList());

        System.out.println(result);  // [Name, Cat, Room, Tool]
    }
}


