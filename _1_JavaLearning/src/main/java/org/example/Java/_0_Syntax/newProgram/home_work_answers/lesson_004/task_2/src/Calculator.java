package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_004.task_2.src;

/**
 * Created by Evgeniy on 09.03.2016.
 */
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Ввести знак арифметической операции: ");

        Scanner sc = new Scanner(System.in);
        String sign = sc.nextLine();

        int operand1 = 10;
        int operand2 = 5;
        int result = 0;

        switch(sign)
        {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if(operand2 == 0) // Если operand2 примет значение 0, то выполнится это условие
                {
                    System.out.println("Делить на ноль запрещено");
                }
                result = operand1 / operand2;
                break;
            default:
                System.out.println("Не известный знак");
                return; // return - прекращает выполнение метода
        }

        System.out.println(operand1 + " " + sign + " " + operand2 + " = " + result);
    }
}
