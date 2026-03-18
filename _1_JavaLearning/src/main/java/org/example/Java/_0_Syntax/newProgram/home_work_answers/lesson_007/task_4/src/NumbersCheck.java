package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_007.task_4.src;

import java.util.Scanner;

/**
 * Created by Evgeniy on 13.03.2016.
 */
public class NumbersCheck {

    public static void main(String[] args) {
        System.out.println("Введите число с клавиатуры");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // Вызов методов

        NumbersCheck.CheckAM(n);
        NumbersCheck.CheckEasy(n);
        NumbersCheck.CheckDiv(n);

    }


    // Метод проверят на положительные/отрицательные числа

    public static void CheckAM(int n) {
        if (n >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    // Метод поиска простых чисел

    public static void CheckEasy(int n) {

        // Перебираем возможные делители от 2 до sqrt(n)
        for (int i = 2; i < Math.sqrt(n); i++) {
            // Если разделилось нацело, то это число не простое
            if (n % i == 0) {
                System.out.println("Число не простое");
                return;
            }
        }
        System.out.println("Число простое");
    }

    // Метод проверяющий делится ли число на  2, 5, 3, 6, 9 без остатка

    public static void CheckDiv(int n) {
        if ((n % 2 == 0) && (n % 5 == 0) && (n % 3 == 0) && (n % 6 == 0) && (n % 9 == 0)) {
            System.out.println("Число делится нацело");
        } else {
            System.out.println("Число не делится нацело");
        }
    }

}
