package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_007.task_2.src;

import java.util.Scanner;

/**
 * Created by Evgeniy on 12.03.2016.
 */
public class Arithmetics {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Первый операнд - ");
        int op1 = sc.nextInt();
        System.out.print("Второй операнд - ");
        int op2 = sc.nextInt();
        System.out.print("Знак : ");
        String sym = sc.next();

        switch (sym) {
            case "+":
                add(op1, op2);
                break;
            case "-":
                sub(op1, op2);
                break;
            case "*":
                mul(op1, op2);
                break;
            case "/":
                div(op1, op2);
                break;
            default:
                System.out.println("Не известная команда!");
        }
    }

    public static void add(int op1, int op2) {
        System.out.println(op1 + " + " + op2 + " = " + (op1 + op2));
    }

    public static void sub(int op1, int op2) {
        System.out.println(op1 + " - " + op2 + " = " + (op1 - op2));
    }

    public static void mul(int op1, int op2) {
        System.out.println(op1 + " * " + op2 + " = " + (op1 * op2));
    }

    public static void div(int op1, int op2) {
        if (op2 == 0) {
            System.out.println("Делить на 0 запрещено");
            return;
        }
        System.out.println(op1 + " / " + op2 + " = " + (op1 / op2));
    }
}
