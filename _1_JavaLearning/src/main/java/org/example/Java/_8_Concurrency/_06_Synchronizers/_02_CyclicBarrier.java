package org.example.Java._8_Concurrency._06_Synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Демонстрация CyclicBarrier - синхронизация потоков в определенной точке
 */
public class _02_CyclicBarrier {
    
    public static void main(String[] args) {
        int parties = 3;
        
        // Барьер с callback при достижении всеми потоками
        CyclicBarrier barrier = new CyclicBarrier(parties, () -> {
            System.out.println(">>> Все потоки достигли барьера! Продолжаем...\n");
        });
        
        for (int i = 1; i <= parties; i++) {
            final int workerId = i;
            new Thread(() -> {
                try {
                    System.out.println("Worker-" + workerId + " выполняет фазу 1");
                    Thread.sleep(1000 * workerId);
                    System.out.println("Worker-" + workerId + " достиг барьера");
                    
                    barrier.await(); // Ждем остальных
                    
                    System.out.println("Worker-" + workerId + " выполняет фазу 2");
                    Thread.sleep(500);
                    System.out.println("Worker-" + workerId + " завершил работу");
                    
                } catch (InterruptedException | BrokenBarrierException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}
