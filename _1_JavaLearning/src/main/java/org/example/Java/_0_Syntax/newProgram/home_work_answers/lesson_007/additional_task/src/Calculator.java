package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_007.additional_task.src;

/**
 * Created by Evgeniy on 12.03.2016.
 */
public class Calculator {
    public static void main(String[] args) {
        // Выводим на экран метод, который принимает некие значения
        System.out.println(calculate(5, 2, 5));
    }

    // Создаем метод calculate
    public static int calculate(int a, int b, int c) {
        // Считаем среднее арифметическое значение аргументов
        return (a + b + c) / 3;
    }
}
