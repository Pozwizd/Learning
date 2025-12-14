package org.example.Java._8_Concurrency._02_Synchronization;

/**
 * Демонстрация использования volatile для видимости изменений между потоками
 */
public class _03_Volatile {
    // Без volatile поток может закешировать значение и не увидеть изменений
    private static volatile boolean running = true;
    
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            System.out.println("Worker started");
            int counter = 0;
            while (running) {
                counter++;
            }
            System.out.println("Worker stopped. Counter: " + counter);
        });
        
        worker.start();
        
        Thread.sleep(1000);
        System.out.println("Main: stopping worker");
        running = false; // volatile гарантирует видимость
        
        worker.join();
        System.out.println("Main: finished");
    }
}
