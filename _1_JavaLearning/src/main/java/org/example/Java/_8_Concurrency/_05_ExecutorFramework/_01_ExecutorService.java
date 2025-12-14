package org.example.Java._8_Concurrency._05_ExecutorFramework;

import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class _01_ExecutorService {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("=== Примеры использования Executor Framework ===");

        // 1. newSingleThreadExecutor - один поток
        System.out.println("\n1. SingleThreadExecutor:");
        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 3; i++) {
            final int taskId = i;
            singleExecutor.submit(() -> {
                System.out.println("SingleThread: Задача " + taskId + " выполняется в " + Thread.currentThread().getName());
                sleep(1000);
                return "Результат " + taskId;
            });
        }
        shutdownAndWait(singleExecutor, "SingleThreadExecutor");

        // 2. newFixedThreadPool - фиксированный размер пула
        System.out.println("\n2. FixedThreadPool:");
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);

        List<Future<String>> futures = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            Future<String> future = fixedPool.submit(() -> {
                System.out.println("FixedPool: Задача " + taskId + " в " + Thread.currentThread().getName());
                sleep(1500);
                return "FixedPool результат " + taskId;
            });
            futures.add(future);
        }

        // Получаем результаты
        for (Future<String> future : futures) {
            System.out.println("Получен: " + future.get());
        }
        shutdownAndWait(fixedPool, "FixedThreadPool");

        // 3. newCachedThreadPool - динамический размер
        System.out.println("\n3. CachedThreadPool:");
        ExecutorService cachedPool = Executors.newCachedThreadPool();

        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            cachedPool.execute(() -> {
                System.out.println("CachedPool: Задача " + taskId + " в " + Thread.currentThread().getName() + " в " + LocalDateTime.now());
                sleep(800);
            });
        }
        shutdownAndWait(cachedPool, "CachedThreadPool");

        // 4. ScheduledExecutorService - планирование задач
        System.out.println("\n4. ScheduledExecutorService:");
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Разовая задача с задержкой
        ScheduledFuture<String> delayed = scheduler.schedule(() -> {
            System.out.println("Scheduled: Отложенная задача выполнена");
            return "Delayed result";
        }, 2, TimeUnit.SECONDS);

        // Периодическая задача
        ScheduledFuture<?> periodic = scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Scheduled: Периодическая задача в " + LocalDateTime.now());
        }, 1, 1, TimeUnit.SECONDS);

        System.out.println("Ждем результат отложенной задачи: " + delayed.get());

        // Останавливаем периодическую задачу через 4 секунды
        Thread.sleep(4000);
        periodic.cancel(false);
        shutdownAndWait(scheduler, "ScheduledExecutorService");

        // 5. ThreadPoolExecutor с детальной настройкой
        System.out.println("\n5. ThreadPoolExecutor с настройками:");

        // Создаем собственный обработчик отклоненных задач
        RejectedExecutionHandler rejectedHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("ThreadPoolExecutor: Задача отклонена - " + r.toString());
            }
        };

        // Создаем пул с детальными настройками
        ThreadPoolExecutor customPool = new ThreadPoolExecutor(
                2,                          // corePoolSize - минимальное количество потоков
                4,                          // maximumPoolSize - максимальное количество потоков
                10,                         // keepAliveTime - время жизни свободных потоков
                TimeUnit.SECONDS,           // единица времени
                new ArrayBlockingQueue<>(2), // очередь задач (размер 2)
                Executors.defaultThreadFactory(), // фабрика потоков
                rejectedHandler             // обработчик отклоненных задач
        );

        // Запускаем монитор пула
        Thread monitorThread = new Thread(new PoolMonitor(customPool));
        monitorThread.start();

        // Отправляем задачи (больше чем может обработать пул)
        for (int i = 1; i <= 8; i++) {
            final int taskId = i;
            try {
                customPool.execute(new WorkerTask("CustomPool-Task-" + taskId));
            } catch (RejectedExecutionException e) {
                System.out.println("Задача " + taskId + " отклонена");
            }
        }

        Thread.sleep(15000); // Ждем выполнения

        customPool.shutdown();
        monitorThread.interrupt();

        // 6. Использование Callable с результатами
        System.out.println("\n6. Callable с обработкой исключений:");
        ExecutorService callablePool = Executors.newFixedThreadPool(2);

        List<Future<Integer>> callableFutures = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            final int taskId = i;
            Future<Integer> future = callablePool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    System.out.println("Callable: Задача " + taskId + " начата");

                    if (taskId == 3) {
                        throw new RuntimeException("Ошибка в задаче " + taskId);
                    }

                    sleep(1000);
                    int result = taskId * 10;
                    System.out.println("Callable: Задача " + taskId + " завершена с результатом " + result);
                    return result;
                }
            });
            callableFutures.add(future);
        }

        // Обрабатываем результаты с обработкой исключений
        for (int i = 0; i < callableFutures.size(); i++) {
            try {
                Integer result = callableFutures.get(i).get();
                System.out.println("Получен результат задачи " + (i + 1) + ": " + result);
            } catch (ExecutionException e) {
                System.out.println("Ошибка в задаче " + (i + 1) + ": " + e.getCause().getMessage());
            }
        }

        shutdownAndWait(callablePool, "CallablePool");

        // 7. invokeAll и invokeAny
        System.out.println("\n7. invokeAll и invokeAny:");
        ExecutorService invokePool = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            final int taskId = i;
            tasks.add(() -> {
                sleep(taskId * 500);
                return "InvokeTask-" + taskId + " результат";
            });
        }

        // invokeAll - ждет завершения всех задач
        System.out.println("invokeAll - ждем все задачи:");
        List<Future<String>> allResults = invokePool.invokeAll(tasks);
        for (Future<String> future : allResults) {
            System.out.println("invokeAll получен: " + future.get());
        }

        // invokeAny - возвращает результат первой завершившейся задачи
        System.out.println("invokeAny - первый результат:");
        String firstResult = invokePool.invokeAny(tasks);
        System.out.println("invokeAny получен: " + firstResult);

        shutdownAndWait(invokePool, "InvokePool");

        System.out.println("\n=== Все примеры завершены ===");
    }

    // Класс для мониторинга пула потоков
    static class PoolMonitor implements Runnable {
        private final ThreadPoolExecutor executor;

        public PoolMonitor(ThreadPoolExecutor executor) {
            this.executor = executor;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted() && !executor.isTerminated()) {
                System.out.printf("Monitor: [%d/%d] Активных: %d, Завершено: %d, Всего задач: %d%n",
                        executor.getPoolSize(),
                        executor.getCorePoolSize(),
                        executor.getActiveCount(),
                        executor.getCompletedTaskCount(),
                        executor.getTaskCount()
                );

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    // Рабочая задача
    static class WorkerTask implements Runnable {
        private final String taskName;

        public WorkerTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println(taskName + " начата в " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000); // Имитация работы
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(taskName + " завершена");
        }

        @Override
        public String toString() {
            return taskName;
        }
    }

    // Вспомогательные методы
    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void shutdownAndWait(ExecutorService executor, String poolName) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println(poolName + ": Принудительное завершение");
                executor.shutdownNow();
            } else {
                System.out.println(poolName + ": Корректно завершен");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
