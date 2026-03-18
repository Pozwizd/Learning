package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_006.task_3.src;

import java.util.Scanner;

/**
 * Created by Evgeniy on 12.03.2016.
 */
public class Task_3 {
    public static void main(String[] args) {

        System.out.println("Введите размер");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        System.out.println();
        System.out.println("Ромб");
        int z = 1;
        for (int i = 0; i < num / 2 + 1; i++) {
            int prob = (num - z) / 2;
            for (int j = 0; j < prob; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < z; k++) {
                System.out.print("*");
            }
            System.out.println();

            z = z + 2;
        }
        z = num - 2;
        //===
        for (int i = num / 2; i > 0; i--) {
            int prob = (num - z) / 2;
            for (int j = 0; j < prob; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < z; k++) {
                System.out.print("*");
            }
            System.out.println();
            z = z - 2;
        }

        System.out.println("Прямоугольник");
        int w = 20;
        int h = 5;

        for (int i = 0; i < h; i++) {
            for (int x = 0; x < w; x++) {
                System.out.print("*");
            }
            System.out.println("");
        }

        System.out.println("Прямоугольный треугольник");
        for (int r = 0; r < 10; r++) {
            for (int e = 0; e < r + 1; e++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("Равносторонний треугольник");
        for (
                int i = 1;
                i <= 5; ++i)

        {
            for (int j = 5; j > i; --j)
                System.out.print(" ");
            for (int j = 1; j < 2 * i; ++j)
                System.out.print("*");
            System.out.println();
        }
    }
}

