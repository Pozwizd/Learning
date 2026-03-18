package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_006.additional_task.src;

import java.util.Scanner;

/**
 * Created by Evgeniy on 12.03.2016.
 */
public class Rectangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();

        for(int i = 0; i < num1; i ++)
        {
            for(int x = 0; x < num2; x++)
            {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
