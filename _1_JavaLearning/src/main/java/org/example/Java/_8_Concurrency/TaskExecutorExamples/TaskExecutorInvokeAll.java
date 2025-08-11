package org.example.Java._8_Concurrency.TaskExecutorExamples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Примеры invokeAll и invokeAny:
 * - пакетная отправка задач
 * - таймауты
 * - обработка исключений
 */
public class TaskExecutorInvokeAll {

    private static String t() { return Thread.currentThread().getName(); }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);

        List<Callable<String>> callables = Arrays.asList(
                callable("fast", 150, false),
                callable("medium", 400, false),
                callable("slow", 800, false),
                callable("boom", 300, true)
        );

        // 1) invokeAll: возвращает Future для каждой задачи и ждёт завершения всех (или до таймаута)
        try {
            System.out.println("== invokeAll (no timeout) ==");
            List<Future<String>> futures = pool.invokeAll(callables);
            printResults(futures);

            System.out.println("== invokeAll (with timeout 500ms) ==");
            List<Future<String>> futuresTimeout = pool.invokeAll(callables, 500, TimeUnit.MILLISECONDS);
            printResults(futuresTimeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("invokeAll: interrupted");
        }

        // 2) invokeAny: ждёт первый успешно завершённый результат, остальные отменяет
        try {
            System.out.println("== invokeAny (no timeout) ==");
            String first = pool.invokeAny(callables);
            System.out.println("invokeAny result: " + first);

            System.out.println("== invokeAny (with timeout 200ms) ==");
            // слишком маленький таймаут - вероятно TimeoutException
            String firstWithTimeout = pool.invokeAny(callables, 200, TimeUnit.MILLISECONDS);
            System.out.println("invokeAny result (timeout): " + firstWithTimeout);
        } catch (TimeoutException e) {
            System.out.println("invokeAny timed out: " + e);
        } catch (ExecutionException e) {
            System.out.println("invokeAny execution failed: " + e.getCause());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("invokeAny interrupted");
        } finally {
            shutdown(pool);
        }
    }

    private static Callable<String> callable(String name, long sleepMs, boolean fail) {
        return () -> {
            System.out.println("[" + t() + "] start " + name);
            try {
                TimeUnit.MILLISECONDS.sleep(sleepMs);
                if (fail) throw new IllegalStateException("boom from " + name);
                System.out.println("[" + t() + "] done " + name);
                return name + "-ok";
            } catch (InterruptedException e) {
                System.out.println("[" + t() + "] interrupted " + name);
                Thread.currentThread().interrupt();
                throw e;
            }
        };
    }

    private static void printResults(List<Future<String>> futures) {
        // Futures могут быть завершены, отменены или с исключением
        List<String> results = new ArrayList<>();
        for (Future<String> f : futures) {
            if (f.isCancelled()) {
                results.add("<cancelled>");
            } else {
                try {
                    results.add(f.get());
                } catch (ExecutionException e) {
                    results.add("error:" + e.getCause());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    results.add("error:interrupted");
                }
            }
        }
        System.out.println("invokeAll results: " + results.stream().collect(Collectors.joining(", ")));
    }

    private static void shutdown(ExecutorService pool) {
        pool.shutdownNow();
        try {
            pool.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}