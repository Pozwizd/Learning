package org.example.Java._8_Concurrency._06_Synchronizers;

import java.util.concurrent.CountDownLatch;

/**
 * Демонстрация CountDownLatch - ожидание завершения нескольких операций
 */
public class _01_CountDownLatch {
    
    public static void main(String[] args) throws InterruptedException {
        int serviceCount = 3;
        CountDownLatch latch = new CountDownLatch(serviceCount);
        
        System.out.println("Запуск сервисов...");
        
        // Симуляция запуска 3 сервисов
        for (int i = 1; i <= serviceCount; i++) {
            final int serviceId = i;
            new Thread(() -> {
                try {
                    System.out.println("Сервис " + serviceId + " инициализируется...");
                    Thread.sleep(1000 * serviceId); // Разное время запуска
                    System.out.println("Сервис " + serviceId + " готов!");
                    latch.countDown(); // Уменьшаем счетчик
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
        
        System.out.println("Главный поток ожидает готовности всех сервисов...");
        latch.await(); // Блокируется пока счетчик не станет 0
        
        System.out.println("Все сервисы запущены! Приложение готово к работе.");
    }
}
