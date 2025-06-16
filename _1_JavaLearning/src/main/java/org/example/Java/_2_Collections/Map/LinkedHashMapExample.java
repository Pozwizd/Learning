package org.example.Java._2_Collections.Map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {

        // Создаем LinkedHashMap с accessOrder=true
        LinkedHashMap<String, Integer> scores = new LinkedHashMap<>(16, 0.75f, true);

        // Добавляем несколько записей
        scores.put("Alice", 95);
        scores.put("Bob", 83);
        scores.put("Charlie", 77);

        // Выводим оценку Алисы
        System.out.println(scores.get("Alice"));

        // Выводим оценку Боба
        System.out.println(scores.get("Bob"));

        // Перебираем все записи в порядке доступа
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " scored " + entry.getValue());
        }
    }
}