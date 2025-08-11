package org.example.Java._8_Concurrency.TaskExecutorExamples;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Пример кастомного ThreadFactory и UncaughtExceptionHandler:
 * - именование потоков
 * - демонические/обычные потоки
 * - установка приоритета
 * - обработка необработанных исключений
 */
public class TaskExecutorThreadFactory {

    public static void main(String[] args) {
        ThreadFactory factory = new CustomThreadFactory(
                "app-worker-",
                false,
                Thread.NORM_PRIORITY + 1,
                (t, e) -> System.out.println("[UEH] Uncaught in " + t.getName() + ": " + e)
        );

        ExecutorService pool = new ThreadPoolExecutor(
                2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                factory
        );

        // Нормальная задача
        pool.execute(logTask("ok-1", 200));

        // Задача, кидающая RuntimeException (поймает UEH)
        pool.execute(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "] throwing exception...");
            throw new RuntimeException("boom!");
        });

        // Ещё задачки
        pool.execute(logTask("ok-2", 100));
        pool.execute(logTask("ok-3", 50));

        shutdownGracefully(pool, "customThreadFactory");
    }

    private static Runnable logTask(String name, long workMs) {
        return () -> {
            String tn = Thread.currentThread().getName();
            System.out.println("[" + tn + "] start " + name);
            try {
                TimeUnit.MILLISECONDS.sleep(workMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                System.out.println("[" + tn + "] end " + name);
            }
        };
    }

    public static void shutdownGracefully(ExecutorService service, String name) {
        service.shutdown();
        try {
            if (!service.awaitTermination(2, TimeUnit.SECONDS)) {
                System.out.println("[" + name + "] Forcing shutdownNow...");
                service.shutdownNow();
                if (!service.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.out.println("[" + name + "] Pool did not terminate");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("[" + name + "] Interrupted during shutdown, forcing now");
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Кастомная фабрика потоков с именованием, приоритетом, демоном и UEH.
     */
    public static class CustomThreadFactory implements ThreadFactory {
        private final AtomicInteger seq = new AtomicInteger();
        private final String prefix;
        private final boolean daemon;
        private final int priority;
        private final Thread.UncaughtExceptionHandler ueh;

        public CustomThreadFactory(String prefix,
                                   boolean daemon,
                                   int priority,
                                   Thread.UncaughtExceptionHandler ueh) {
            this.prefix = prefix;
            this.daemon = daemon;
            this.priority = priority;
            this.ueh = ueh;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, prefix + seq.incrementAndGet());
            t.setDaemon(daemon);
            try {
                t.setPriority(priority);
            } catch (IllegalArgumentException ignored) {
                // защита от неверного диапазона приоритета
            }
            if (ueh != null) {
                t.setUncaughtExceptionHandler(ueh);
            }
            return t;
        }
    }
}