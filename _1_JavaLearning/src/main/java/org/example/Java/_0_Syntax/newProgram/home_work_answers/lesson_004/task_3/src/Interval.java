package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_004.task_3.src;

/**
 * Created by Evgeniy on 10.03.2016.
 */

import java.util.Scanner;

public class Interval {
    public static void main(String[] args) {
        System.out.println("Введите число от 0 до 100:");

        // Вводим число с клавиатуры
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        // Проверям наше число в заданном диапазоне
        if (num >= 0 && num <= 14) {
            System.out.println("Вошел в промежуток [0 - 14]");
        } else if (num >= 15 && num <= 35) {
            System.out.println("Вошел в промежуток [15 - 35]");
        } else if (num >= 36 && num <= 49) {
            System.out.println("Вошел в промежуток [36 - 49]");
        } else if (num >= 50 && num <= 100) {
            System.out.println("Вошел в промежуток [50 - 100]");
        } else {
            System.out.println("Вы не вошли в промежуток, попробуйте снова");
        }
    }
}
