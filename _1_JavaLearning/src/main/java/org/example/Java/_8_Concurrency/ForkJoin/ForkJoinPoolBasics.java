package org.example.Java._8_Concurrency.ForkJoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Базовая демонстрация ForkJoinPool с использованием RecursiveTask для суммирования массива.
 *
 * Что показывает пример:
 * - Как разбивать задачу на подзадачи (fork) и объединять результаты (join)
 * - Как выбрать порог (THRESHOLD), при котором выгодно перестать делить и посчитать последовательно
 * - Как использовать общий пул потоков ForkJoinPool.commonPool()
 *
 * Как запустить:
 * - Запускайте метод main. Он сравнит время последовательной суммы и суммы в ForkJoin.
 */
public class ForkJoinPoolBasics {

    /**
     * Задача, которая суммирует подмассив целых чисел.
     * Наследуемся от RecursiveTask<Long>, т.к. нам нужен результат (сумма).
     */
    static class ParallelSumTask extends RecursiveTask<Long> {
        // Порог, при котором перестаём дробить и считаем напрямую
        private static final int THRESHOLD = 10_000;
        private final int[] array;
        private final int start; // включительно
        private final int end;   // исключительно

        ParallelSumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            int length = end - start;
            // База рекурсии: если кусок маленький — считаем в одном потоке
            if (length <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            }

            // Разделяем диапазон пополам и создаём 2 подзадачи
            int mid = start + length / 2;
            ParallelSumTask left = new ParallelSumTask(array, start, mid);
            ParallelSumTask right = new ParallelSumTask(array, mid, end);

            // 1) fork() — запускаем левую подзадачу асинхронно
            left.fork();

            // 2) compute() — вычисляем правую подзадачу синхронно в текущем потоке
            long rightResult = right.compute();

            // 3) join() — дожидаемся результата левой подзадачи
            long leftResult = left.join();

            // Складываем результаты
            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        int size = 5_000_000;
        // Генерируем массив псевдослучайных чисел (фиксированное зерно для воспроизводимости)
        int[] data = new Random(42).ints(size, 0, 100).toArray();

        // Последовательная сумма (через Stream для краткости)
        long t1 = System.currentTimeMillis();
        long seq = Arrays.stream(data).asLongStream().sum();
        long t2 = System.currentTimeMillis();

        // Параллельная сумма через ForkJoinPool
        ForkJoinPool pool = ForkJoinPool.commonPool(); // общий (shared) пул
        long t3 = System.currentTimeMillis();
        long par = pool.invoke(new ParallelSumTask(data, 0, data.length));
        long t4 = System.currentTimeMillis();

        System.out.println("Последовательная сумма: " + seq + " время=" + (t2 - t1) + "ms");
        System.out.println("ForkJoin сумма        : " + par + " время=" + (t4 - t3) + "ms");
        System.out.println("Параллелизм CommonPool: " + pool.getParallelism());
    }
}