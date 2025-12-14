package org.example.Java._8_Concurrency._07_ConcurrentCollections;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Демонстрация ConcurrentHashMap с атомарными операциями
 */
public class _01_ConcurrentHashMap {
    
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        
        System.out.println("=== Атомарные операции ===");
        
        // putIfAbsent - добавить если отсутствует
        map.putIfAbsent("key1", 1);
        map.putIfAbsent("key1", 2); // Не заменит
        System.out.println("key1: " + map.get("key1")); // 1
        
        // computeIfAbsent - вычислить если отсутствует
        map.computeIfAbsent("key2", k -> k.length());
        System.out.println("key2: " + map.get("key2")); // 4
        
        // merge - атомарное обновление
        map.put("counter", 1);
        map.merge("counter", 1, Integer::sum); // counter = 1 + 1
        map.merge("counter", 3, Integer::sum); // counter = 2 + 3
        System.out.println("counter: " + map.get("counter")); // 5
        
        System.out.println("\n=== Многопоточный доступ ===");
        
        ConcurrentHashMap<String, Integer> sharedMap = new ConcurrentHashMap<>();
        sharedMap.put("shared", 0);
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedMap.merge("shared", 1, Integer::sum);
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                sharedMap.merge("shared", 1, Integer::sum);
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("Ожидаемое: 2000, Фактическое: " + sharedMap.get("shared"));
        
        System.out.println("\n=== Bulk операции ===");
        map.forEach((k, v) -> System.out.println(k + " = " + v));
    }
}
