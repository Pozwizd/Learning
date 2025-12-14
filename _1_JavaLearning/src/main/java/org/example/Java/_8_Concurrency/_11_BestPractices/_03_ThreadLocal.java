package org.example.Java._8_Concurrency._11_BestPractices;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Best Practice: Использование ThreadLocal для thread-specific данных
 */
public class _03_ThreadLocal {
    
    // SimpleDateFormat НЕ потокобезопасен!
    // ПЛОХО: общий для всех потоков
    // private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    // ХОРОШО: каждый поток получает свою копию
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = 
        ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    
    public static String formatDate(Date date) {
        return DATE_FORMAT.get().format(date);
    }
    
    // Пример с пользовательским контекстом
    static class UserContext {
        private static final ThreadLocal<String> currentUser = new ThreadLocal<>();
        
        public static void setUser(String user) {
            currentUser.set(user);
        }
        
        public static String getUser() {
            return currentUser.get();
        }
        
        public static void clear() {
            currentUser.remove(); // Важно очищать!
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== ThreadLocal SimpleDateFormat ===");
        
        Date now = new Date();
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + formatDate(now));
            }
        }, "Thread-1");
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + formatDate(now));
            }
        }, "Thread-2");
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("\n=== ThreadLocal User Context ===");
        
        Thread user1 = new Thread(() -> {
            UserContext.setUser("Alice");
            System.out.println(Thread.currentThread().getName() + " user: " + UserContext.getUser());
            doWork();
            UserContext.clear();
        }, "Request-1");
        
        Thread user2 = new Thread(() -> {
            UserContext.setUser("Bob");
            System.out.println(Thread.currentThread().getName() + " user: " + UserContext.getUser());
            doWork();
            UserContext.clear();
        }, "Request-2");
        
        user1.start();
        user2.start();
        user1.join();
        user2.join();
    }
    
    private static void doWork() {
        // Любой метод может получить текущего пользователя
        System.out.println("  doWork() for user: " + UserContext.getUser());
    }
}
