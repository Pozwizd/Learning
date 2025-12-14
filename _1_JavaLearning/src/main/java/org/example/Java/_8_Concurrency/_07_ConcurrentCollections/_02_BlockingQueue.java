package org.example.Java._8_Concurrency._07_ConcurrentCollections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Демонстрация BlockingQueue для Producer-Consumer паттерна
 */
public class _02_BlockingQueue {
    
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        
        // Producer
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Producing: " + i);
                    queue.put(i); // Блокируется если очередь полна
                    Thread.sleep(100);
                }
                queue.put(-1); // Сигнал завершения
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");
        
        // Consumer
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Integer item = queue.take(); // Блокируется если очередь пуста
                    if (item == -1) {
                        break;
                    }
                    System.out.println("Consuming: " + item + ", Queue size: " + queue.size());
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");
        
        producer.start();
        consumer.start();
        
        producer.join();
        consumer.join();
        
        System.out.println("Все задачи обработаны");
    }
}
