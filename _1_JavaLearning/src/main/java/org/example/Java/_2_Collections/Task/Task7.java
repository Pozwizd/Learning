package org.example.Java._2_Collections.Task;

/*
Создать класс сортировок, в котором создать 5 статических методов, каждый из которых должен сортировать массив своим способом.
То есть 5 методов - 5 способов сортировки.
Каждый метод принимает в качестве параметра - массив чисел и возвращает его в отсортированном, по возрастанию, виде.
В конце работы метода вывести время, за которое массив был отсортирован.
Методы сортировки не просто копировать, а понимать и уметь объяснить как каждый из них работает.
 */

import java.util.Arrays;

public class Task7 {

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 4, 7, 6, 5, 8};

        System.out.println("Исходный массив: " + Arrays.toString(arr));
        System.out.println();

        int[] sortedArr = Sorter.bubbleSort(Arrays.copyOf(arr, arr.length));
        System.out.println("Отсортированный массив (пузырьковая сортировка): " + Arrays.toString(sortedArr));
        System.out.println();

        sortedArr = Sorter.selectionSort(Arrays.copyOf(arr, arr.length));
        System.out.println("Отсортированный массив (сортировка выбором): " + Arrays.toString(sortedArr));
        System.out.println();

        sortedArr = Sorter.insertionSort(Arrays.copyOf(arr, arr.length));
        System.out.println("Отсортированный массив (сортировка вставками): " + Arrays.toString(sortedArr));
        System.out.println();

        sortedArr = Sorter.runMergeSort(Arrays.copyOf(arr, arr.length));
        System.out.println("Отсортированный массив (сортировка слиянием): " + Arrays.toString(sortedArr));

        sortedArr = Sorter.quickSort(Arrays.copyOf(arr, arr.length));
        System.out.println("Отсортированный массив (быстрая сортировка): " + Arrays.toString(sortedArr));
        System.out.println();
    }
}

class Sorter {
    /**
     * Сортировка пузырьком.
     * Алгоритмическая сложность: O(n^2).
     * Принцип заключается в двойном цикле и перестановке наибольшего элемента в конец массива
     *
     * @param arr Массив который нужно отсортировать
     */
    public static int[] bubbleSort(int[] arr) {
        long startTime = System.nanoTime();

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        long endTime = System.nanoTime();
        System.out.println("Время выполнения сортировки пузырьком: " + (endTime - startTime) + " нс");
        return arr;
    }

    /**
     * Сортировка выбором.
     * Алгоритмическая сложность: O(n^2).
     * Принцип заключается в перемещении минимального значения в начало массива
     * Суть алгоритма заключается в следующем:
     * - На каждой итерации мы ищем минимальный элемент в неотсортированной части массива.
     * - Минимальный элемент меняем местами с первым элементом в неотсортированной части массива.
     * - Повторяем процесс для оставшейся части массива, которая еще не отсортирована,
     * и продолжаем до тех пор, пока не отсортируем весь массив.
     */
    public static int[] selectionSort(int[] arr) {
        long startTime = System.nanoTime();

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            int minIdx = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }

        long endTime = System.nanoTime();
        System.out.println("Время выполнения сортировки выбором: " + (endTime - startTime) + " нс");
        return arr;
    }

    /**
     * Сортировка вставками.
     * Алгоритмическая сложность: O(n^2).
     * Принцип заключается в двойном цикле и вставкой наименьшего значение в начало
     */
    public static int[] insertionSort(int[] arr) {
        long startTime = System.nanoTime();

        int n = arr.length;

        for (int i = 1; i < n; i++) {


            int key = arr[i];


            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        long endTime = System.nanoTime();
        System.out.println("Время выполнения сортировки вставками: " + (endTime - startTime) + " нс");
        return arr;
    }

    /**
     * Сортировка слиянием.
     * Алгоритмическая сложность: O(nlogn).
     * Принцип заключается в делении массива до наименьшего значения
     * при слиянии выполняется сортировка
     * Суть алгоритма заключается в следующем:
     * Если длина массива больше 1, разделяем его на две половины и рекурсивно сортируем каждую половину.
     * Затем отсортированные половины сливаем в один отсортированный массив.
     */
    public static void mergeSort(int[] arr) {
        if (arr == null) {
            return;
        }

        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
            int[] rightArr = Arrays.copyOfRange(arr, mid, arr.length);
            mergeSort(leftArr);
            mergeSort(rightArr);

            merge(leftArr, rightArr, arr);
        }
    }

    private static void merge(int[] leftArr, int[] rightArr, int[] arr) {
        int leftIndex = 0;
        int rightIndex = 0;
        int arrIndex = 0;

        while (leftIndex < leftArr.length && rightIndex < rightArr.length) {
            if (leftArr[leftIndex] <= rightArr[rightIndex]) {
                arr[arrIndex] = leftArr[leftIndex];
                leftIndex++;
            } else {
                arr[arrIndex] = rightArr[rightIndex];
                rightIndex++;
            }
            arrIndex++;
        }

        while (leftIndex < leftArr.length) {
            arr[arrIndex] = leftArr[leftIndex];
            leftIndex++;
            arrIndex++;
        }

        while (rightIndex < rightArr.length) {
            arr[arrIndex] = rightArr[rightIndex];
            rightIndex++;
            arrIndex++;
        }
    }

    public static int[] runMergeSort(int[] arr) {
        long startTime = System.nanoTime();
        mergeSort(arr);
        long endTime = System.nanoTime();
        System.out.println("Время выполнения сортировки слиянием: " + (endTime - startTime) + " нс");
        return arr;
    }

    /**
     * Быстрая сортировка. Сортировка Хоара
     * Алгоритмическая сложность: O(nlogn).
     * Суть алгоритма заключается в следующем:
     * Выбираем опорный элемент из массива.
     * Разделяем массив на две части: элементы, меньшие опорного, и элементы, большие опорного.
     * Рекурсивно повторяем процесс для каждой части массива.
     */
    public static int[] quickSort(int[] arr) {
        long startTime = System.nanoTime();
        quickSortHelper(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Время выполнения быстрой сортировки: " + (endTime - startTime) + " нс");
        return arr;
    }

    private static void quickSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            System.out.println("Сортируем левую часть массива от " + left + " до " + (pivotIndex - 1));
            System.out.println("Текущее состояние массива: " + Arrays.toString(arr));
            quickSortHelper(arr, left, pivotIndex - 1);
            System.out.println("Сортируем правую часть массива от " + (pivotIndex + 1) + " до " + right);
            System.out.println("Текущее состояние массива: " + Arrays.toString(arr));
            quickSortHelper(arr, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        System.out.println("Выбран опорный элемент: " + pivot);
        int i = left - 1;
        for (int j = left; j <= right - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;
        System.out.println("Текущее состояние массива: " + Arrays.toString(arr));
        System.out.println("Опорный элемент перемещен в позицию " + (i + 1));
        return i + 1;
    }
}
