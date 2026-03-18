package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_003.task_2.src;

/**
 * Created by Evgeniy on 09.03.2016.
 */
public class ArithmeticAverage {
    public static void main(String[] args) {
        byte a = 2, b = 3, c = 5;
        // Расширение типа
        int average = (a + b + c) / 3;

        // Лучше всего использовать float/double типы переменных, в зависимости от диапазона чисел.
        System.out.println(average);
    }
}
