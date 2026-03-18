package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_009.task_2.src;

import java.util.Scanner;

/**
 * Created by Evgeniy on 13.03.2016.
 */
public class CustomerArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        process(num);
    }

    public static void process(int num) {

        int arr[] = new int[num];


        // Заполнение массива случайными числами
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.round((Math.random() * 30) - 5);
        }

        // Вывод массива
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int sum = 0;
        // Сумма всех элементов массива
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        System.out.println("Сумма элементов массива: " + sum);

        // Поиск наибольшего и наименьшего значения массива
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println("MAX : " + max);
        System.out.println("MIN : " + min);

        // Среднее арифметическое
        int arg = 0;
        for (int i = 0; i < arr.length; i++) {
            arg += arr[i];
        }
        arg /= arr.length;
        System.out.println("Среднее арифметическое  : " + arg);

        System.out.print("Нечетные элементы : ");
        // Нечетные элементы
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] % 2) != 0)
                System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
