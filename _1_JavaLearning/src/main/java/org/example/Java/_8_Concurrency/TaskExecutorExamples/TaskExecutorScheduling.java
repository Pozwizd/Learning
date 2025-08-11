package org.example.Java._8_Concurrency.TaskExecutorExamples;

import java.time.LocalTime;
import java.util.concurrent.*;

/**
 * Примеры работы с ScheduledExecutorService:
 * - schedule (одноразовая задача)
 * - scheduleAtFixedRate (фиксированный период от начала к началу)
 * - scheduleWithFixedDelay (фиксированная пауза после завершения)
 * - отмена ScheduledFuture и корректное завершение пула
 */
public class TaskExecutorScheduling {

    private static String now() {
        return LocalTime.now().toString();
    }

    private static String tn() {
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // 1) Одноразовая задача
        ScheduledFuture<?> oneShot = scheduler.schedule(() ->
                System.out.println(now() + " [oneShot] run on " + tn()), 300, TimeUnit.MILLISECONDS);

        // 2) Периодическая задача по фиксированному периоду (от запуска к запуску)
        Runnable fixedRateTask = new BusyTask("fixedRate", 200); // сама задача занимает ~200мс
        ScheduledFuture<?> fixedRate = scheduler.scheduleAtFixedRate(
                fixedRateTask,
                0,            // initial delay
                300,          // период
                TimeUnit.MILLISECONDS
        );

        // 3) Периодическая задача с фиксированной задержкой (после завершения)
        Runnable fixedDelayTask = new BusyTask("fixedDelay", 250);
        ScheduledFuture<?> fixedDelay = scheduler.scheduleWithFixedDelay(
                fixedDelayTask,
                0,
                300,
                TimeUnit.MILLISECONDS
        );

        // Дадим задачам поработать немного, затем отменим
        sleep(1500);

        System.out.println(now() + " Cancel fixedRate");
        fixedRate.cancel(true); // прерываем периодическое выполнение
        System.out.println(now() + " Cancel fixedDelay");
        fixedDelay.cancel(true);

        // Подождем одноразовую, если не выполнилась
        try {
            oneShot.get(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("oneShot wait exception: " + e);
        }

        shutdownGracefully(scheduler, "scheduler");
    }

    /**
     * Задача, которая имитирует работу в течение заданного времени.
     */
    static class BusyTask implements Runnable {
        private final String name;
        private final long workMillis;

        BusyTask(String name, long workMillis) {
            this.name = name;
            this.workMillis = workMillis;
        }

        @Override
        public void run() {
            String start = now();
            String thread = tn();
            System.out.println(start + " [" + name + "] start on " + thread);
            try {
                TimeUnit.MILLISECONDS.sleep(workMillis);
            } catch (InterruptedException e) {
                System.out.println(now() + " [" + name + "] interrupted on " + thread);
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println(now() + " [" + name + "] end on " + thread + " (started at " + start + ")");
        }
    }

    private static void sleep(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void shutdownGracefully(ExecutorService service, String name) {
        service.shutdown();
        try {
            if (!service.awaitTermination(1, TimeUnit.SECONDS)) {
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
}