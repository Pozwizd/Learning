package org.example.Java._7_Exception;

class MyException extends Exception {

    private int age;

    public MyException(String message) {
        super(message);
    }
}

public class MyExceptionExample {

    public static void check(int age) throws MyException {
        if(age < 0) {
            throw new MyException("Возраст не может быть отрицательным");
        }
    }

    public static void main(String[] args) {
        try {
            check(-5);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

}