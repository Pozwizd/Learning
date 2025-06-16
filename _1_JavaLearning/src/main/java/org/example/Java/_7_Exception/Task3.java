package org.example.Java._7_Exception;

import java.util.Optional;

public class Task3 {
    public static void main(String[] args) {

    }
}

class NullPointerExceptionEx{

    public static int getLength(String str) {
        if (str == null) {
            throw new NullPointerException("str не должен быть null");
        }

        return str.length();
    }

    public static void main(String[] args) {
        // 1
        String str = null;
        try {
            int length = str.length();
        } catch (NullPointerException e) {
            System.out.println("Ошибка - объект null!");
        }

        // 2
        Optional<String> opt = Optional.ofNullable(str);
        System.out.println(opt);

        // 3
        System.out.println(getLength(str));
    }
}

class ArithmeticExceptionEx{

    public static void main(String[] args) {
        // 1
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Деление на 0!");
        }

        try {
            double z = Math.sqrt(-5);
            System.out.println(z);
        } catch (ArithmeticException e) {
            System.out.println("Извлечение корня из отрицательного числа!");
        }
    }
}

class ArrayIndexOutOfBoundsExceptionEx{
    public static void main(String[] args) {
        // 1
        int[] array = new int[5];
        for (int i = 0; i <= array.length; i++) {
            try {
                int x = array[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Индекс " + i + " вне диапазона");
            }
        }

        // 2
        int index = 5;
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int x = array[index];

        // 3
        int index3 = 5;
        int value = Optional.ofNullable(array[index3])
                .orElseThrow(() -> new ArrayIndexOutOfBoundsException(index));
    }
}

class ClassCastExceptionEx{
    public static void main(String[] args) {


        Object obj = "Example";

        try {
            Integer integer = (Integer) obj;
            System.out.println(integer);
        } catch (ClassCastException e){
            throw new ClassCastException("Несоответствие типа: " + e);
        }
    }
}

class OutOfMemoryErrorEx{
    public static void main(String[] args) {
        try {
            int[] array = new int[Integer.MAX_VALUE];
        } catch (OutOfMemoryError e) {
            System.out.println("Нехватка памяти при выделении объекта.");
        }
    }
}

class StackOverflowErrorEx {
    public static void recursion() {
        recursion();
    }

    public static void main(String[] args) {
        recursion();
    }
}

class ThreadDeathEx{
    // IllegalThreadStateException
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                }
            }

        };
        t.start();

        for (int i = 0; i < 1000; i++) {

        }
        System.out.println(t.isAlive());
        t.start();
    }
}

class NoSuchMethodErrorEx{
    public static void main(String[] args) {

    }
}

