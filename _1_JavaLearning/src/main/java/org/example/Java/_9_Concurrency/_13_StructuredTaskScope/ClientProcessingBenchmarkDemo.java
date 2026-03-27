package org.example.Java._9_Concurrency._13_StructuredTaskScope;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClientProcessingBenchmarkDemo {

    private static final int CLIENT_COUNT = 1_000;
    private static final int FIXED_POOL_SIZE = 100;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        BenchmarkResult virtualThreads = runScenario(
                "Virtual Threads",
                Executors.newVirtualThreadPerTaskExecutor()
        );

        BenchmarkResult fixedPool = runScenario(
                "FixedThreadPool(" + FIXED_POOL_SIZE + ")",
                Executors.newFixedThreadPool(FIXED_POOL_SIZE)
        );

        System.out.println();
        System.out.println("Сравнение:");
        System.out.println(virtualThreads);
        System.out.println(fixedPool);
        System.out.println("Разница: " + (fixedPool.durationMs() - virtualThreads.durationMs()) + " мс");
    }

    private static BenchmarkResult runScenario(String label, ExecutorService executor)
            throws InterruptedException, ExecutionException {
        try (executor) {
            Instant startedAt = Instant.now();
            List<Future<ClientResult>> futures = new ArrayList<>(CLIENT_COUNT);

            for (int clientId = 1; clientId <= CLIENT_COUNT; clientId++) {
                final int currentClientId = clientId;
                futures.add(executor.submit(processClient(currentClientId)));
            }

            int completed = 0;
            for (Future<ClientResult> future : futures) {
                future.get();
                completed++;
            }

            long durationMs = Duration.between(startedAt, Instant.now()).toMillis();
            return new BenchmarkResult(label, completed, durationMs);
        }
    }

    private static Callable<ClientResult> processClient(int clientId) {
        return () -> {
            List<String> orders = fetchOrdersFromDb(clientId);
            String deliveryStatus = fetchDeliveryStatus(clientId);
            return new ClientResult(clientId, orders.size(), deliveryStatus);
        };
    }

    private static List<String> fetchOrdersFromDb(int clientId) throws InterruptedException {
        Thread.sleep(120);
        return List.of("order-" + clientId + "-1", "order-" + clientId + "-2");
    }

    private static String fetchDeliveryStatus(int clientId) throws InterruptedException {
        Thread.sleep(180);
        return (clientId % 2 == 0) ? "DELIVERED" : "IN_TRANSIT";
    }

    record ClientResult(int clientId, int orderCount, String deliveryStatus) {
    }

    record BenchmarkResult(String mode, int completedClients, long durationMs) {
        @Override
        public String toString() {
            return mode + ": обработано " + completedClients + " клиентов за " + durationMs + " мс";
        }
    }
}
