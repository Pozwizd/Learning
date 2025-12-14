package org.example.Java._3_Algorithms._09_Recursion;

/**
 * Основы рекурсии
 */
public class _01_RecursionBasics {
    
    // Факториал
    public static long factorial(int n) {
        if (n <= 1) return 1;  // Базовый случай
        return n * factorial(n - 1);  // Рекурсивный случай
    }
    
    // Числа Фибоначчи
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    // Сумма цифр числа
    public static int sumOfDigits(int n) {
        if (n == 0) return 0;
        return n % 10 + sumOfDigits(n / 10);
    }
    
    // Степень
    public static long power(int base, int exp) {
        if (exp == 0) return 1;
        return base * power(base, exp - 1);
    }
    
    // Оптимизированная степень (разделяй и властвуй)
    public static long powerOptimized(int base, int exp) {
        if (exp == 0) return 1;
        
        long half = powerOptimized(base, exp / 2);
        
        if (exp % 2 == 0) {
            return half * half;
        } else {
            return base * half * half;
        }
    }
    
    // Разворот строки
    public static String reverse(String s) {
        if (s.isEmpty()) return s;
        return reverse(s.substring(1)) + s.charAt(0);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Основы рекурсии ===\n");
        
        System.out.println("Факториал 5: " + factorial(5));
        System.out.println("Фибоначчи 7: " + fibonacci(7));
        System.out.println("Сумма цифр 12345: " + sumOfDigits(12345));
        System.out.println("2^10: " + power(2, 10));
        System.out.println("2^10 (оптимизир.): " + powerOptimized(2, 10));
        System.out.println("Разворот \"hello\": " + reverse("hello"));
    }
}
