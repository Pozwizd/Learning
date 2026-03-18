package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_006.task_4.src;

import java.util.Scanner;

/**
 * Created by Evgeniy on 12.03.2016.
 */
public class Delivery {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int temp = 1;

        do {
            temp *= num--;
        } while (num > 0);

        System.out.println("Количество возможных вариантов доставки товара - " + temp);
    }
}
