package hw2.Methods;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImmutableExample {
    public static void main(String[] args) {
        List<String> immutableList = List.of("apple", "banana", "orange");
        System.out.println(immutableList);

        Set<Integer> immutableSet = Set.of(1, 2, 3);
        System.out.println(immutableSet);

        Map<String, Integer> immutableMap = Map.of("one", 1, "two", 2, "three", 3);
        System.out.println(immutableMap);
    }
}