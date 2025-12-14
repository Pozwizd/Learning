package org.example.Java._8_Concurrency._11_BestPractices;

/**
 * Best Practice: Минимизация области блокировки
 */
public class _02_MinimizeLocking {
    
    // ПЛОХО: блокируем весь метод
    static class BadExample {
        private int data = 0;
        
        public synchronized void process() {
            // Долгая операция, не требующая синхронизации
            heavyComputation();
            
            // Критическая секция
            data++;
            
            // Еще одна долгая операция
            anotherHeavyComputation();
        }
        
        private void heavyComputation() {
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
        
        private void anotherHeavyComputation() {
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
    
    // ХОРОШО: блокируем только критическую секцию
    static class GoodExample {
        private int data = 0;
        private final Object lock = new Object();
        
        public void process() {
            // Долгая операция без блокировки
            heavyComputation();
            
            // Блокируем только критическую секцию
            synchronized(lock) {
                data++;
            }
            
            // Еще одна операция без блокировки
            anotherHeavyComputation();
        }
        
        private void heavyComputation() {
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
        
        private void anotherHeavyComputation() {
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Плохой пример (широкая блокировка) ===");
        BadExample bad = new BadExample();
        long start = System.currentTimeMillis();
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) bad.process();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) bad.process();
        });
        
        t1.start(); t2.start();
        t1.join(); t2.join();
        
        long badTime = System.currentTimeMillis() - start;
        System.out.println("Время: " + badTime + "ms");
        
        System.out.println("\n=== Хороший пример (узкая блокировка) ===");
        GoodExample good = new GoodExample();
        start = System.currentTimeMillis();
        
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) good.process();
        });
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 5; i++) good.process();
        });
        
        t3.start(); t4.start();
        t3.join(); t4.join();
        
        long goodTime = System.currentTimeMillis() - start;
        System.out.println("Время: " + goodTime + "ms");
        System.out.println("\nУлучшение: " + (badTime - goodTime) + "ms");
    }
}
