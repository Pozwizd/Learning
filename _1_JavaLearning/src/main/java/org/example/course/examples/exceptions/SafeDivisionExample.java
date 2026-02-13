package org.example.course.examples.exceptions;

public class SafeDivisionExample {
    public static void main(String[] args) {
        System.out.println(divide(10, 2));
        System.out.println(divide(10, 0));
    }

    static int divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Деление на ноль запрещено");
        }
        return a / b;
    }
}
