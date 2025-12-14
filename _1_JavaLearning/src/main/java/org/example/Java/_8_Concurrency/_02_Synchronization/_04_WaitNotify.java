package org.example.Java._8_Concurrency._02_Synchronization;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Демонстрация механизма wait/notify для Producer-Consumer паттерна
 */
public class _04_WaitNotify {
    static class SharedQueue {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int MAX_SIZE = 5;
        
        public synchronized void produce(int item) throws InterruptedException {
            while (queue.size() == MAX_SIZE) {
                System.out.println("Queue full, producer waiting...");
                wait(); // Освобождаем монитор и ждем
            }
            
            queue.add(item);
            System.out.println("Produced: " + item + ", Queue size: " + queue.size());
            notifyAll(); // Уведомляем потребителей
        }
        
        public synchronized int consume() throws InterruptedException {
            while (queue.isEmpty()) {
                System.out.println("Queue empty, consumer waiting...");
                wait(); // Освобождаем монитор и ждем
            }
            
            int item = queue.poll();
            System.out.println("Consumed: " + item + ", Queue size: " + queue.size());
            notifyAll(); // Уведомляем производителей
            return item;
        }
    }
    
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue();
        
        // Producer
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    sharedQueue.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Consumer
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    sharedQueue.consume();
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        producer.start();
        consumer.start();
    }
}
