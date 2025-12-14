package org.example.Java._8_Concurrency._05_ExecutorFramework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Базовые примеры работы с ExecutorService:
 * - создание различных пулов
 * - отправка Runnable и Callable задач
 * - получение результатов через Future
 * - корректное завершение (shutdown/awaitTermination)
 */
public class TaskExecutorBasics {

    private static String threadName() {
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        System.out.println("Start: " + threadName());

        // 1) Fixed thread pool
        ExecutorService fixed = Executors.newFixedThreadPool(3);

        // Runnable - без результата
        fixed.execute(() -> System.out.println("[fixed] Runnable executed on: " + threadName()));

        // Callable - с результатом Future
        Future<Integer> future = fixed.submit(() -> {
            System.out.println("[fixed] Callable running on: " + threadName());
            TimeUnit.MILLISECONDS.sleep(200);
            return 42;
        });

        try {
            Integer result = future.get(1, TimeUnit.SECONDS);
            System.out.println("[fixed] Callable result: " + result);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[fixed] Interrupted while waiting result");
        } catch (ExecutionException e) {
            System.out.println("[fixed] Task failed: " + e.getCause());
        } catch (TimeoutException e) {
            System.out.println("[fixed] Timed out waiting for result");
        }

        shutdownGracefully(fixed, "fixed");

        // 2) Cached thread pool - подстраивается под нагрузку (безразмерный)
        ExecutorService cached = Executors.newCachedThreadPool();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int id = i;
            futures.add(cached.submit(() -> {
                System.out.println("[cached] Task " + id + " on: " + threadName());
                TimeUnit.MILLISECONDS.sleep(100 + id * 20L);
                return "done-" + id;
            }));
        }
        for (Future<String> f : futures) {
            try {
                System.out.println("[cached] result: " + f.get());
            } catch (Exception e) {
                System.out.println("[cached] error: " + e);
            }
        }
        shutdownGracefully(cached, "cached");

        // 3) Single thread executor — последовательное выполнение задач одним потоком
        ExecutorService single = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3; i++) {
            int n = i;
            single.execute(() -> System.out.println("[single] step " + n + " on: " + threadName()));
        }
        shutdownGracefully(single, "single");

        // 4) Scheduled executor — планирование задач во времени
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
        ScheduledFuture<?> scheduledFuture = scheduled.schedule(() ->
                System.out.println("[scheduled] one-shot on: " + threadName()), 300, TimeUnit.MILLISECONDS);

        try {
            scheduledFuture.get(); // дождаться выполнения одноразовой
        } catch (Exception e) {
            System.out.println("[scheduled] error waiting one-shot: " + e);
        }
        shutdownGracefully(scheduled, "scheduled");

        System.out.println("End: " + threadName());
    }

    /**
     * Корректное завершение пула потоков.
     */
    public static void shutdownGracefully(ExecutorService service, String name) {
        service.shutdown();
        try {
            if (!service.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("[" + name + "] Forcing shutdownNow...");
                List<Runnable> dropped = service.shutdownNow();
                System.out.println("[" + name + "] Dropped tasks: " + dropped.size());
                if (!service.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.out.println("[" + name + "] Pool did not terminate");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("[" + name + "] Interrupted during shutdown, forcing now");
            List<Runnable> dropped = service.shutdownNow();
            System.out.println("[" + name + "] Dropped tasks: " + dropped.size());
            Thread.currentThread().interrupt();
        }
    }
}
