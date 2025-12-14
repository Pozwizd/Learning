package org.example.Java._8_Concurrency._02_Synchronization;

/**
 * Демонстрация проблемы Race Condition
 */
public class _01_RaceCondition {
    static class Counter {
        private int count = 0;
        
        // НЕ потокобезопасная операция
        public void increment() {
            count++; // Не атомарно: read -> modify -> write
        }
        
        public int getCount() {
            return count;
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
        System.out.println("Фактическое значение: " + counter.getCount());
        System.out.println("Проблема Race Condition!");
    }
}
