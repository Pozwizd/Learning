package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_009.task_3.src;

/**
 * Created by Evgeniy on 13.03.2016.
 */
public class ReversedArray {
    public static void main(String[] args) {
        int[] tmp;
        int[] tmp2;

        // Вызываем метод myReverse. Заполняем массив целочисленными значениями
        tmp = ReversedArray.myReverse(new int[]{1, 5, 3, 6, 8, 9});

        // Метод возвращает часть полученного в качестве аргумента массива
        // начиная с позиции, указанной в index, в данном случае 2
        // и размерностью аргумента count, в данном случае - 10
        tmp2 = ReversedArray.subArray(new int[]{1, 5, 9, 5, 4, 12, 45, 78}, 2, 10);

        // Выводим на экран массив tmp
        for (int i = 0; i < tmp.length; i++) {
            System.out.print(tmp[i] + " ");
        }

        // Выводим на экран массив tmp2
        System.out.println();
        for (int i = 0; i < tmp2.length; i++) {
            System.out.print(tmp2[i] + " ");
        }
    }

    // Создаем метод myReverse
    public static int[] myReverse(int[] array) {
        // Массив целочисленных элементов
        int[] tmp = new int[array.length];
        // Инвертируем массив
        for (int i = array.length - 1, j = 0; i >= 0; i--, j++) {
            tmp[j] = array[i];
        }
        // Возвращаем переменную tmp
        return tmp;
    }

    // Создаем метод, который принимает 3 значения:
    // Массив, и переменные: index, count
    public static int[] subArray(int[] array, int index, int count) {
        int[] tmp = new int[count];
        boolean full = false;
        for (int i = index, j = 0; i < count; i++, j++) {
            if (i <= ((array.length - index) + 1) && !full) {
                tmp[j] = array[i];
            } else {
                // Это условие выполняется для того, что бы оптимизировать позицию индекса в цикле
                if (!full) {
                    full = true;
                    i -= index;
                }
                tmp[j] = 1;
            }
        }
        return tmp;
    }
}
