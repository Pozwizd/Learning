package org.example.Java._8_Concurrency._03_LockFramework;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Демонстрация ReadWriteLock для оптимизации при множественном чтении
 */
public class _05_ReadWriteLock {
    static class Cache {
        private final Map<String, String> map = new HashMap<>();
        private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
        
        public String get(String key) {
            rwLock.readLock().lock(); // Множественные читатели
            try {
                System.out.println(Thread.currentThread().getName() + " reading");
                return map.get(key);
            } finally {
                rwLock.readLock().unlock();
            }
        }
        
        public void put(String key, String value) {
            rwLock.writeLock().lock(); // Эксклюзивная запись
            try {
                System.out.println(Thread.currentThread().getName() + " writing");
                Thread.sleep(100); // Имитация долгой операции
                map.put(key, value);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                rwLock.writeLock().unlock();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Cache cache = new Cache();
        
        // Writer
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                cache.put("key" + i, "value" + i);
            }
        }, "Writer");
        
        // Readers
        Thread reader1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                cache.get("key0");
            }
        }, "Reader-1");
        
        Thread reader2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                cache.get("key1");
            }
        }, "Reader-2");
        
        writer.start();
        Thread.sleep(50);
        reader1.start();
        reader2.start();
        
        writer.join();
        reader1.join();
        reader2.join();
    }
}
