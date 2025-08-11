package org.example.Java._8_Concurrency.TaskExecutorExamples;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Примеры настроек ThreadPoolExecutor с ограниченной очередью и политиками отклонения:
 * - AbortPolicy (исключение)
 * - CallerRunsPolicy (выполняет вызывающий поток)
 * - DiscardPolicy / DiscardOldestPolicy
 *
 * Также показан кастомный RejectedExecutionHandler с логированием и backpressure.
 */
public class TaskExecutorRejectionPolicy {

    public static void main(String[] args) {
        System.out.println("== AbortPolicy example ==");
        demoWithPolicy(new ThreadPoolExecutor.AbortPolicy());

        System.out.println("\n== CallerRunsPolicy example ==");
        demoWithPolicy(new ThreadPoolExecutor.CallerRunsPolicy());

        System.out.println("\n== DiscardPolicy example ==");
        demoWithPolicy(new ThreadPoolExecutor.DiscardPolicy());

        System.out.println("\n== DiscardOldestPolicy example ==");
        demoWithPolicy(new ThreadPoolExecutor.DiscardOldestPolicy());

        System.out.println("\n== Custom Rejection Handler example ==");
        demoWithCustomHandler();
    }

    /**
     * Создаёт пул с маленькой очередью, посылает больше задач, чем он может вместить,
     * чтобы спровоцировать отказ в приёме задач и увидеть поведение политики.
     */
    private static void demoWithPolicy(RejectedExecutionHandler handler) {
        ThreadFactory namedFactory = new NamedThreadFactory("poolP-");
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2); // очень маленькая очередь

        ThreadPoolExecutor exec = new ThreadPoolExecutor(
                2,                  // core
                2,                  // max
                60, TimeUnit.SECONDS,
                queue,
                namedFactory,
                handler
        );

        // Отправим задач больше, чем пропускная способность пула + очередь
        int submitted = 8;
        for (int i = 0; i < submitted; i++) {
            int id = i;
            try {
                exec.execute(busyTask("T" + id, 300));
                System.out.println("submitted T" + id + " | poolSize=" + exec.getPoolSize()
                        + " queued=" + queue.size());
            } catch (RejectedExecutionException e) {
                System.out.println("Rejected T" + id + " -> " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            sleep(50);
        }

        shutdownGracefully(exec, "demoWithPolicy");
    }

    /**
     * Кастомный обработчик отклонений. Делает backpressure: ждёт освобождения места и ретраит.
     */
    private static void demoWithCustomHandler() {
        ThreadFactory namedFactory = new NamedThreadFactory("poolC-");
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);

        RejectedExecutionHandler blockingHandler = (r, executor) -> {
            // Попытка "мягкого" backpressure: блокируемся пока не освободится место,
            // затем вручную помещаем задачу в очередь, если пул ещё не завершён.
            if (executor.isShutdown()) {
                throw new RejectedExecutionException("Executor is shutdown");
            }
            try {
                System.out.println("Rejected task -> applying backpressure (offer after wait)...");
                // Ждём, пока хотя бы одна задача завершится
                while (!executor.isShutdown() && !queue.offer(r, 100, TimeUnit.MILLISECONDS)) {
                    // Можно добавить логирование метрик, таймаутов и т.д.
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RejectedExecutionException("Interrupted while backpressuring", e);
            }
        };

        ThreadPoolExecutor exec = new ThreadPoolExecutor(
                2, 2, 60, TimeUnit.SECONDS,
                queue,
                namedFactory,
                blockingHandler
        );

        int submitted = 8;
        for (int i = 0; i < submitted; i++) {
            int id = i;
            exec.execute(busyTask("C" + id, 300));
            System.out.println("submitted C" + id + " | poolSize=" + exec.getPoolSize()
                    + " queued=" + queue.size());
            sleep(50);
        }

        shutdownGracefully(exec, "customHandler");
    }

    private static Runnable busyTask(String name, long workMs) {
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
     * Кастомная фабрика потоков для удобного нейминга.
     */
    static class NamedThreadFactory implements ThreadFactory {
        private final AtomicInteger seq = new AtomicInteger();
        private final String prefix;

        NamedThreadFactory(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, prefix + seq.incrementAndGet());
            t.setDaemon(false);
            return t;
        }
    }
}