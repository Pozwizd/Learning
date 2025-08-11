package org.example.Java._8_Concurrency.ForkJoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Демонстрация:
 * - Создание кастомного ForkJoinPool с заданным уровнем параллелизма
 * - Отправка RecursiveTask в кастомный пул
 * - Использование ManagedBlocker для корректной работы пула при блокирующих операциях
 *
 * Зачем нужен ManagedBlocker:
 * - Потоки в ForkJoinPool заточены под кратковременные задачи без блокировки.
 * - При блокировке (например, I/O) пул может "голодать" — не хватать потоков для других задач.
 * - ManagedBlocker позволяет пулу временно компенсировать блокировку (создать/разморозить поток) и избежать деградации.
 *
 * Как запустить:
 * - Запустите метод main. Он создаст массив, выполнит параллельную сумму с редкими блокировками и выведет время.
 */
public class CustomPoolAndManagedBlocker {

    /**
     * Имитация блокирующей операции, совместимая с ForkJoinPool через ManagedBlocker.
     * В реальном коде это может быть I/O, ожидание сокета и т.д.
     */
    static class BlockingIO implements ManagedBlocker {
        private volatile boolean done = false;
        private final long millis;

        BlockingIO(long millis) {
            this.millis = millis;
        }

        @Override
        public boolean block() throws InterruptedException {
            // Метод будет вызван пулом, когда поток собирается блокироваться.
            if (!done) {
                TimeUnit.MILLISECONDS.sleep(millis); // имитация задержки/блокировки
                done = true;
            }
            return true; // true = блокировка завершена
        }

        @Override
        public boolean isReleasable() {
            // Если уже не нужно блокироваться — вернуть true
            return done;
        }
    }

    /**
     * Задача суммирования элементов массива, иногда выполняющая блокирующую операцию.
     * Наследуемся от RecursiveTask<Long>, т.к. возвращаем сумму.
     */
    static class SumWithOccasionalBlock extends RecursiveTask<Long> {
        private static final int THRESHOLD = 100_000; // мелкие куски считаем последовательно
        private final int[] data;
        private final int start; // включительно
        private final int end;   // исключительно
        private final Random rnd = new Random(7);

        SumWithOccasionalBlock(int[] data, int start, int end) {
            this.data = data;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int len = end - start;
            if (len <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += data[i];
                    // Периодически выполняем блокировку (имитация I/O) через ManagedBlocker
                    if ((i & 0x7FFF) == 0) {
                        try {
                            ForkJoinPool.managedBlock(new BlockingIO(rnd.nextInt(3))); // 0–2 мс
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                return sum;
            }
            // Делим задачу на две подзадачи
            int mid = start + len / 2;
            SumWithOccasionalBlock left = new SumWithOccasionalBlock(data, start, mid);
            SumWithOccasionalBlock right = new SumWithOccasionalBlock(data, mid, end);
            left.fork();             // левую — асинхронно
            long r = right.compute(); // правую — синхронно
            return left.join() + r;  // объединяем результаты
        }
    }

    public static void main(String[] args) {
        int size = 3_000_000;
        int[] arr = new Random(99).ints(size, 0, 100).toArray();

        // Кастомный пул с явным уровнем параллелизма
        int parallelism = Math.max(2, Runtime.getRuntime().availableProcessors() - 1);
        ForkJoinPool pool = new ForkJoinPool(parallelism);

        System.out.println("Параллелизм кастомного пула: " + pool.getParallelism());
        long t1 = System.currentTimeMillis();
        long sum = pool.invoke(new SumWithOccasionalBlock(arr, 0, arr.length));
        long t2 = System.currentTimeMillis();

        System.out.println("Сумма=" + sum + " время=" + (t2 - t1) + "ms");
        pool.shutdown();
    }
}