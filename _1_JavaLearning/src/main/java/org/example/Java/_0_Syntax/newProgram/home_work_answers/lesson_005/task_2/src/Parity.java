package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_005.task_2.src;

import java.util.Scanner;

/**
 * Created by Evgeniy on 12.03.2016.
 */
public class Parity {
    public static void main(String[] args) {

        // Вводим число 8 с клавиатуры

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        // 7 - 0111
        // &
        // 1 - 0001
        //   - 0001 == 1

        // 8 - 1000
        // &
        // 1 - 0001
        //   - 0000 == 0

        // Побитовая проверка
        // Проверка чисел на четность №1
        if ((num & 1) == 0) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

        // Деление нацело
        // Проверка чисел на четность №2
        if (num % 2 == 0) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

    }
}
