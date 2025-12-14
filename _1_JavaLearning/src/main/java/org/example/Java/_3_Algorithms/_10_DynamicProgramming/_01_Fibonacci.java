package org.example.Java._3_Algorithms._10_DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Динамическое программирование: Фибоначчи
 * Сравнение наивной рекурсии, мемоизации и табуляции
 */
public class _01_Fibonacci {
    
    // Наивная рекурсия - O(2^n)
    public static long fibonacciNaive(int n) {
        if (n <= 1) return n;
        return fibonacciNaive(n - 1) + fibonacciNaive(n - 2);
    }
    
    // Мемоизация (Top-Down DP) - O(n)
    public static long fibonacciMemo(int n, Map<Integer, Long> memo) {
        if (n <= 1) return n;
        
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        long result = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }
    
    // Табуляция (Bottom-Up DP) - O(n)
    public static long fibonacciTab(int n) {
        if (n <= 1) return n;
        
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    // Оптимизированная версия - O(n) time, O(1) space
    public static long fibonacciOptimized(int n) {
        if (n <= 1) return n;
        
        long prev2 = 0;
        long prev1 = 1;
        long current = 0;
        
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return current;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Динамическое программирование: Фибоначчи ===\n");
        
        int n = 40;
        
        // Наивная рекурсия - очень медленно для больших n
        System.out.println("Вычисляем Фибоначчи(" + n + ")...\n");
        
        long start = System.currentTimeMillis();
        long result = fibonacciMemo(n, new HashMap<>());
        long time1 = System.currentTimeMillis() - start;
        System.out.println("Мемоизация: " + result + " (время: " + time1 + "ms)");
        
        start = System.currentTimeMillis();
        result = fibonacciTab(n);
        long time2 = System.currentTimeMillis() - start;
        System.out.println("Табуляция: " + result + " (время: " + time2 + "ms)");
        
        start = System.currentTimeMillis();
        result = fibonacciOptimized(n);
        long time3 = System.currentTimeMillis() - start;
        System.out.println("Оптимизированная: " + result + " (время: " + time3 + "ms)");
        
        System.out.println("\nПопробуйте вызвать fibonacciNaive(40) - будет работать очень долго!");
    }
}
