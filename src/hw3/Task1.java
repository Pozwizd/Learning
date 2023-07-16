package hw3;

import java.util.Random;
import java.util.Scanner;
import java.util.*;


public class Task1 {


    public static int[] generateRandomArray(int length) {
        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }


    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int steps = 0;

        while (left <= right) {
            steps++;
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                System.out.println("Количество шагов: " + steps);
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else if (array[mid] < target) {
                if (mid + 1 <= right && array[mid + 1] == target) {
                    System.out.println("Количество шагов: " + steps);
                    return mid + 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        System.out.println("Количество шагов: " + steps);
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();


        int[] array = generateRandomArray(size);
        Arrays.sort(array);


        System.out.print("Отсортированный массив: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();


        System.out.print("Введите число для поиска: ");
        int target = scanner.nextInt();


        int index = binarySearch(array, target);


        if (index != -1) {
            System.out.println("Число найдено в массиве под индексом " + index);
        } else {
            System.out.println("Число не найдено в массиве");
        }
    }
}