package org.example.Java._3_Algorithms;

import java.util.Scanner;
import java.util.Stack;

public class Task4 {

    public static boolean checkBrackets(String equation) {
        Stack<Character> stack = new Stack<>();
        for (char c : equation.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите уравнение: ");
        String equation = scanner.nextLine();
        if (checkBrackets(equation)) {
            System.out.println("Скобки расставлены правильно");
        } else {
            System.out.println("Скобки расставлены неправильно");
        }
    }
}