package hw2.Map;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {

        TreeMap<String, Integer> treeMap = new TreeMap<>();

        treeMap.put("John", 25);
        treeMap.put("Mary", 30);
        treeMap.put("Bob", 20);
        treeMap.put("Alice", 27);

        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        SortedMap<String, Integer> subMap = treeMap.subMap("Bob", "John");
        System.out.println("Элементы в диапазоне ключей [Bob, John]: " + subMap);

        treeMap.remove("Mary");

        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}