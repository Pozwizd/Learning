package org.example.Java._8_Concurrency.TaskExecutorExamples;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Паттерны аккуратного завершения пула задач:
 * - кооперативная отмена через Future.cancel(true) и флаг interrupted
 * - shutdown() + awaitTermination() с fall-back на shutdownNow()
 * - добавление JVM shutdown hook для гарантированной очистки при завершении процесса
 * - ограничение времени ожидания и корректная обработка InterruptedException
 */
public class TaskExecutorShutdownHooks {

    public static void main(String[] args) {
        System.out.println(ts() + " start main on " + tn());

        ExecutorService pool = Executors.newFixedThreadPool(3);

        // Регистрируем shutdown hook на случай, если процесс завершится (Ctrl+C, System.exit и т.п.)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println(ts() + " [JVM Hook] received shutdown signal, stopping executors...");
            shutdownGracefully(pool, "hook-pool");
        }, "shutdown-hook"));

        // Отправим несколько долгих задач
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int id = i;
            futures.add(pool.submit(cooperativeTask("job-" + id, 1500)));
        }

        // Дадим задачам поработать
        sleep(500);

        // Демонстрация кооперативной отмены: отменим часть задач
        System.out.println(ts() + " cancel job-2 and job-3");
        futures.get(2).cancel(true);
        futures.get(3).cancel(true);

        // Подождём завершения оставшихся и соберём результаты
        for (int i = 0; i < futures.size(); i++) {
            Future<String> f = futures.get(i);
            try {
                String res = f.get(2, TimeUnit.SECONDS);
                System.out.println(ts() + " result[" + i + "]: " + res);
            } catch (CancellationException e) {
                System.out.println(ts() + " result[" + i + "]: cancelled");
            } catch (TimeoutException e) {
                System.out.println(ts() + " result[" + i + "]: timed out, will not block longer");
            } catch (ExecutionException e) {
                System.out.println(ts() + " result[" + i + "]: failed -> " + e.getCause());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(ts() + " result[" + i + "]: interrupted");
            }
        }

        // Инициируем аккуратное завершение вручную (в дополнение к hook)
        shutdownGracefully(pool, "main-pool");

        System.out.println(ts() + " end main on " + tn());
    }

    /**
     * Кооперативная задача: периодически проверяет interrupted-флаг и корректно завершается.
     */
    private static Callable<String> cooperativeTask(String name, long totalWorkMs) {
        return () -> {
            System.out.println(ts() + " [" + tn() + "] start " + name);
            long step = 100;
            long done = 0;
            try {
                while (done < totalWorkMs) {
                    // Имитируем работу
                    TimeUnit.MILLISECONDS.sleep(step);
                    done += step;

                    // Кооперативная отмена
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println(ts() + " [" + tn() + "] " + name + " interrupted, cleaning up...");
                        // Освобождение ресурсов, закрытие файлов/соединений и т.п.
                        return name + ": interrupted-cleaned";
                    }
                }
                System.out.println(ts() + " [" + tn() + "] done " + name);
                return name + ": OK";
            } catch (InterruptedException e) {
                // Если прервали во время sleep — устанавливаем флаг обратно и корректно завершаем
                Thread.currentThread().interrupt();
                System.out.println(ts() + " [" + tn() + "] " + name + " interrupted during sleep, cleaning up...");
                return name + ": interrupted-during-sleep";
            }
        };
    }

    /**
     * Универсальный метод корректного завершения.
     * 1) shutdown() — запрет новых задач, выполнение очереди
     * 2) awaitTermination(timeout) — ждём
     * 3) shutdownNow() — прерываем текущие и возвращаем невыполненные
     * 4) повторный awaitTermination для гарантии
     */
    public static void shutdownGracefully(ExecutorService service, String name) {
        service.shutdown();
        try {
            if (!service.awaitTermination(2, TimeUnit.SECONDS)) {
                System.out.println("[" + name + "] Forcing shutdownNow...");
                List<Runnable> dropped = service.shutdownNow();
                System.out.println("[" + name + "] Dropped tasks: " + dropped.size());
                if (!service.awaitTermination(2, TimeUnit.SECONDS)) {
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

    private static void sleep(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static String tn() {
        return Thread.currentThread().getName();
    }

    private static String ts() {
        return LocalTime.now().toString();
    }
}