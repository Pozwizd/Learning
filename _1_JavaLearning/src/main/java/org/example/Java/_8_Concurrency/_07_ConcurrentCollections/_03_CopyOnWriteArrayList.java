package org.example.Java._8_Concurrency._07_ConcurrentCollections;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Демонстрация CopyOnWriteArrayList - оптимизация для частого чтения
 */
public class _03_CopyOnWriteArrayList {
    
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        
        // Добавляем элементы
        list.add("A");
        list.add("B");
        list.add("C");
        
        System.out.println("=== Безопасная итерация при модификации ===");
        
        Thread modifier = new Thread(() -> {
            try {
                Thread.sleep(100);
                System.out.println("Добавляем D");
                list.add("D");
                Thread.sleep(100);
                System.out.println("Добавляем E");
                list.add("E");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        Thread reader = new Thread(() -> {
            // Итератор работает со снимком (snapshot)
            for (String item : list) {
                System.out.println("Reading: " + item);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        reader.start();
        modifier.start();
        
        reader.join();
        modifier.join();
        
        System.out.println("\nФинальный список: " + list);
        System.out.println("Никаких ConcurrentModificationException!");
    }
}
