package hw2.Map;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> scores = new HashMap<>();

        scores.put("Alice", 95);
        scores.put("Bob", 83);
        scores.put("Charlie", 77);

        int aliceScore = scores.get("Alice");

        scores.remove("Charlie");

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            String name = entry.getKey();
            int score = entry.getValue();
            System.out.println(name + " scored " + score);
        }
    }
}
