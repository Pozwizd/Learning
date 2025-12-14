package org.example.Java._8_Concurrency._04_Atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Демонстрация использования AtomicInteger для lock-free операций
 */
public class _01_AtomicInteger {
    static class Counter {
        private final AtomicInteger count = new AtomicInteger(0);
        
        public void increment() {
            count.incrementAndGet(); // Атомарно без блокировок
        }
        
        public int get() {
            return count.get();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        });
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        System.out.println("Ожидаемое значение: 20000");
        System.out.println("Фактическое значение: " + counter.get());
        System.out.println("Атомарная операция работает корректно!");
    }
}
