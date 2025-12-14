package org.example.Java._8_Concurrency._01_Basics;

/**
 * Демонстрация жизненного цикла потока
 * Состояния: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
 */
public class _02_ThreadLifecycle {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Поток запущен: " + Thread.currentThread().getState());
            
            try {
                Thread.sleep(2000); // TIMED_WAITING
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("Поток завершается");
        });
        
        System.out.println("Состояние перед start(): " + thread.getState()); // NEW
        
        thread.start();
        System.out.println("Состояние после start(): " + thread.getState()); // RUNNABLE
        
        Thread.sleep(500);
        System.out.println("Состояние во время sleep: " + thread.getState()); // TIMED_WAITING
        
        thread.join();
        System.out.println("Состояние после завершения: " + thread.getState()); // TERMINATED
    }
}
