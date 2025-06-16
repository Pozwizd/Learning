package org.example.Java._3_Algorithms;

import java.util.Random;
import java.util.Scanner;

import java.util.Arrays;

/**
 * Класс-демонстрация работы с массивами:
 * 1. Создание массива случайных целых чисел.
 * 2. Сортировка массива.
 * 3. Двоичный поиск указанного числа с подсчётом количества шагов.
 *
 * <p>Запуск программы происходит из метода {@link #main(String[])}.</p>
 *
 * @author Вы
 */
public class Task1 {

    /**
     * Генерирует массив указанной длины и заполняет его
     * случайными числами в диапазоне 0 … 99 (включительно).
     *
     * @param length требуемая длина массива
     * @return массив случайных чисел
     */
    public static int[] generateRandomArray(int length) {
        int[] array = new int[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(100);   // случайное число 0-99
        }
        return array;
    }

    /**
     * Выполняет двоичный поиск {@code target} в отсортированном массиве {@code array}.
     * <p>Метод дополнительно выводит количество сделанных шагов
     * (итераций цикла) до момента обнаружения элемента или завершения
     * поиска.</p>
     *
     * @param array  отсортированный массив целых чисел
     * @param target искомое значение
     * @return индекс найденного элемента или –1, если элемент отсутствует
     */
    public static int binarySearch(int[] array, int target) {
        int left = 0;                   // левая граница поиска
        int right = array.length - 1;   // правая граница поиска
        int steps = 0;                  // счётчик итераций

        while (left <= right) {
            steps++;
            int mid = left + (right - left) / 2;    // индекс середины

            if (array[mid] == target) {             // элемент найден
                System.out.println("Количество шагов: " + steps);
                return mid;
            } else if (array[mid] > target) {       // таргет левее
                right = mid - 1;
            } else {                                // таргет правее
                // Дополнительная быстрая проверка соседнего элемента
                if (mid + 1 <= right && array[mid + 1] == target) {
                    System.out.println("Количество шагов: " + steps);
                    return mid + 1;
                }
                left = mid + 1;
            }
        }

        // Элемент не найден
        System.out.println("Количество шагов: " + steps);
        return -1;
    }

    /**
     * Точка входа в программу.
     *
     * Алгоритм работы:
     * <ol>
     *     <li>Запрашиваем у пользователя размер массива.</li>
     *     <li>Генерируем массив случайных чисел и сортируем его.</li>
     *     <li>Выводим отсортированный массив на экран.</li>
     *     <li>Запрашиваем число для поиска.</li>
     *     <li>Ищем число методом {@link #binarySearch(int[], int)} и выводим результат.</li>
     * </ol>
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Ввод размера массива
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();

        // 2. Генерация и сортировка массива
        int[] array = generateRandomArray(size);
        Arrays.sort(array);

        // 3. Вывод отсортированного массива
        System.out.print("Отсортированный массив: ");
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();

        // 4. Ввод числа для поиска
        System.out.print("Введите число для поиска: ");
        int target = scanner.nextInt();

        // 5. Двоичный поиск
        int index = binarySearch(array, target);

        // Вывод результата
        if (index != -1) {
            System.out.println("Число найдено в массиве под индексом " + index);
        } else {
            System.out.println("Число не найдено в массиве");
        }
    }
}
