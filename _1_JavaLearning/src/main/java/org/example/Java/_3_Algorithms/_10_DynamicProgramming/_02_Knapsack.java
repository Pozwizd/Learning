package org.example.Java._3_Algorithms._10_DynamicProgramming;

/**
 * Задача о рюкзаке (0/1 Knapsack Problem)
 */
public class _02_Knapsack {
    
    // Рекурсивное решение
    public static int knapsackRecursive(int[] weights, int[] values, int capacity, int n) {
        // Базовый случай
        if (n == 0 || capacity == 0) {
            return 0;
        }
        
        // Если вес текущего элемента больше capacity, не можем его взять
        if (weights[n - 1] > capacity) {
            return knapsackRecursive(weights, values, capacity, n - 1);
        }
        
        // Максимум из: взять элемент или не брать
        int include = values[n - 1] + knapsackRecursive(weights, values, capacity - weights[n - 1], n - 1);
        int exclude = knapsackRecursive(weights, values, capacity, n - 1);
        
        return Math.max(include, exclude);
    }
    
    // DP решение (табуляция)
    public static int knapsackDP(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        // Заполняем таблицу
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    // Можем взять элемент
                    int include = values[i - 1] + dp[i - 1][w - weights[i - 1]];
                    int exclude = dp[i - 1][w];
                    dp[i][w] = Math.max(include, exclude);
                } else {
                    // Не можем взять
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    // Оптимизированная версия с одномерным массивом
    public static int knapsackOptimized(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[] dp = new int[capacity + 1];
        
        for (int i = 0; i < n; i++) {
            // Идем справа налево, чтобы не перезаписать значения
            for (int w = capacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }
        
        return dp[capacity];
    }
    
    public static void main(String[] args) {
        System.out.println("=== 0/1 Knapsack Problem ===\n");
        
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        
        System.out.println("Веса: [10, 20, 30]");
        System.out.println("Ценности: [60, 100, 120]");
        System.out.println("Вместимость рюкзака: " + capacity);
        
        int result = knapsackDP(values, weights, capacity);
        System.out.println("\nМаксимальная ценность: " + result);
        
        result = knapsackOptimized(values, weights, capacity);
        System.out.println("Оптимизированное решение: " + result);
        
        System.out.println("\nСложность: O(n * capacity)");
    }
}
