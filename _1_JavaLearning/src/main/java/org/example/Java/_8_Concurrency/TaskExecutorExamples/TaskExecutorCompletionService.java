package org.example.Java._8_Concurrency.TaskExecutorExamples;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Пример использования ExecutorCompletionService:
 * - задачи завершаются в разном порядке
 * - результаты забираются по мере готовности через take()/poll()
 */
public class TaskExecutorCompletionService {

    private static String tn() { return Thread.currentThread().getName(); }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CompletionService<String> completion = new ExecutorCompletionService<>(pool);
        Random rnd = new Random();

        int tasks = 6;
        for (int i = 0; i < tasks; i++) {
            int id = i;
            completion.submit(() -> {
                int work = 100 + rnd.nextInt(600);
                System.out.println("[" + tn() + "] start task " + id + " (" + work + "ms)");
                try {
                    TimeUnit.MILLISECONDS.sleep(work);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return "task-" + id + ": interrupted";
                }
                System.out.println("[" + tn() + "] done task " + id);
                return "task-" + id + ": OK";
            });
        }

        // Забираем результаты по мере готовности
        for (int i = 0; i < tasks; i++) {
            try {
                Future<String> done = completion.take(); // блокируемся до готовности
                System.out.println("completed: " + done.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("take interrupted");
                break;
            } catch (ExecutionException e) {
                System.out.println("task failed: " + e.getCause());
            }
        }

        shutdown(pool);
    }

    private static void shutdown(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                pool.awaitTermination(1, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}