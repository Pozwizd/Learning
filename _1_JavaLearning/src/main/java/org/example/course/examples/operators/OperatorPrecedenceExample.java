package org.example.course.examples.operators;

public class OperatorPrecedenceExample {
    public static void main(String[] args) {
        int resultWithoutBrackets = 2 + 3 * 4;
        int resultWithBrackets = (2 + 3) * 4;

        System.out.println("2 + 3 * 4 = " + resultWithoutBrackets);
        System.out.println("(2 + 3) * 4 = " + resultWithBrackets);
    }
}
