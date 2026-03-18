package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_009.task_4.src;

/**
 * Created by Evgeniy on 13.03.2016.
 */
public class UserArray {
    public static void main(String[] args) {
        // В массив arr заносим следующие значения:
        int[] arr = insert(new int[]{7, 2, 9, 6}, 22);

        // Выводим на экран массив arr
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }

    // Создаем метод insert, в качестве аргумента принимает:
    // массив array и целочисленную переменную value
    public static int[] insert(int[] array, int value) {
        // Расшряем массив на 1 элемент
        int[] tmp = new int[array.length + 1];

        for (int i = 0; i < tmp.length; i++) {
            // Так как мы расширили на 1 элемент массив, то этот элемент принял значение 0
            // Делаем проверку, является ли это число 0
            if (i == 0) {
                // Присваиваем массиву наше добавленое число, в данном случае - 22
                // и заполняем массив tmp числами 7, 2, 9, 6.
                // На выходе массив выглядит так: 22, 7, 2, 9, 6
                tmp[i] = value;
                continue;
            }
            // i - 1 - нужно для что бы порядок индексации начинался с 0
            tmp[i] = array[i - 1];
        }
        return tmp;
    }
}
