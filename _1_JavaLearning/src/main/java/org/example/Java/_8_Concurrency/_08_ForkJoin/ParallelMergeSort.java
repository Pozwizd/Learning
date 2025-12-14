package org.example.Java._8_Concurrency._08_ForkJoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Параллельная сортировка слиянием на основе ForkJoin (RecursiveAction — без возвращаемого значения).
 *
 * Что показывает пример:
 * - Как реализовать разделяй-и-властвуй сортировку (merge sort) с использованием invokeAll(...)
 * - Как выбирать порог (THRESHOLD) и сортировать маленькие куски последовательно (Arrays.sort)
 * - Как выполнять слияние результатов в общий массив (merge)
 *
 * Как запустить:
 * - Запускайте main. Он сравнит время обычной сортировки Arrays.sort (последовательно) и параллельного варианта.
 */
public class ParallelMergeSort {

    /**
     * Задача сортировки участка массива [left..right].
     * Используем общий буфер temp для копирования при слиянии.
     */
    static class MergeSortAction extends RecursiveAction {
        private static final int THRESHOLD = 10_000; // Порог — ниже него сортируем последовательно
        private final int[] arr;
        private final int left;
        private final int right;
        private final int[] temp;

        MergeSortAction(int[] arr, int left, int right, int[] temp) {
            this.arr = arr;
            this.left = left;
            this.right = right;
            this.temp = temp;
        }

        @Override
        protected void compute() {
            int len = right - left + 1;
            // База рекурсии: маленький участок — просто сортируем напрямую
            if (len <= THRESHOLD) {
                Arrays.sort(arr, left, right + 1);
                return;
            }
            // Делим диапазон на две части
            int mid = left + (right - left) / 2;
            MergeSortAction leftTask = new MergeSortAction(arr, left, mid, temp);
            MergeSortAction rightTask = new MergeSortAction(arr, mid + 1, right, temp);

            // Параллельно выполнить обе подзадачи
            invokeAll(leftTask, rightTask);

            // После завершения обеих — слить отсортированные половины
            merge(left, mid, right);
        }

        /**
         * Классическое слияние двух отсортированных половин [l..m] и [m+1..r]
         * Результат записываем в исходный массив arr.
         */
        private void merge(int l, int m, int r) {
            int i = l, j = m + 1, k = l;
            // Пока есть элементы в обеих половинах — выбираем меньший
            while (i <= m && j <= r) {
                if (arr[i] <= arr[j]) temp[k++] = arr[i++];
                else temp[k++] = arr[j++];
            }
            // Докопировать хвост оставшейся половины (если есть)
            while (i <= m) temp[k++] = arr[i++];
            while (j <= r) temp[k++] = arr[j++];
            // Перенести из temp обратно в arr
            for (int x = l; x <= r; x++) arr[x] = temp[x];
        }
    }

    public static void main(String[] args) {
        int size = 2_000_000;
        // Одинаковые исходные данные для честного сравнения
        int[] data1 = new Random(123).ints(size, -1_000_000, 1_000_000).toArray();
        int[] data2 = Arrays.copyOf(data1, data1.length);

        // Последовательная сортировка
        long t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        long t2 = System.currentTimeMillis();

        // Параллельная сортировка через ForkJoin
        ForkJoinPool pool = ForkJoinPool.commonPool();
        long t3 = System.currentTimeMillis();
        pool.invoke(new MergeSortAction(data2, 0, data2.length - 1, new int[data2.length]));
        long t4 = System.currentTimeMillis();

        System.out.println("Sequential sort time: " + (t2 - t1) + "ms");
        System.out.println("Parallel   sort time: " + (t4 - t3) + "ms");
        System.out.println("Arrays equal: " + Arrays.equals(data1, data2));
    }
}
