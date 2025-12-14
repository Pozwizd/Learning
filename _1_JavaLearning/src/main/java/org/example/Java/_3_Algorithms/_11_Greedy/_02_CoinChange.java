package org.example.Java._3_Algorithms._11_Greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Жадный алгоритм: Размен монет
 * (работает только для определенных систем монет)
 */
public class _02_CoinChange {
    
    // Greedy подход для стандартных монет
    public static List<Integer> coinChangeGreedy(int[] coins, int amount) {
        List<Integer> result = new ArrayList<>();
        
        // Идем от большего к меньшему
        for (int i = coins.length - 1; i >= 0 && amount > 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                result.add(coins[i]);
            }
        }
        
        return amount == 0 ? result : new ArrayList<>();
    }
    
    // DP подход для произвольных монет (минимальное количество монет)
    public static int coinChangeDP(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        
        // Инициализация: максимальное значение
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        
        dp[0] = 0; // 0 монет для суммы 0
        
        // Для каждой суммы от 1 до amount
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
    
    public static void main(String[] args) {
        System.out.println("=== Coin Change ===\n");
        
        int[] coins = {1, 5, 10, 25}; // Стандартные монеты (центы США)
        int amount = 63;
        
        System.out.println("Монеты: [1, 5, 10, 25]");
        System.out.println("Сумма: " + amount);
        
        List<Integer> greedy = coinChangeGreedy(coins, amount);
        System.out.println("\nGreedy результат: " + greedy);
        System.out.println("Количество монет: " + greedy.size());
        
        int dpResult = coinChangeDP(coins, amount);
        System.out.println("\nDP минимальное количество: " + dpResult);
        
        // Пример, где greedy не работает
        System.out.println("\n=== Пример, где Greedy не оптимален ===");
        int[] specialCoins = {1, 3, 4};
        int specialAmount = 6;
        
        System.out.println("Монеты: [1, 3, 4]");
        System.out.println("Сумма: " + specialAmount);
        
        List<Integer> greedySpecial = coinChangeGreedy(specialCoins, specialAmount);
        System.out.println("Greedy: " + greedySpecial + " (кол-во: " + greedySpecial.size() + ")");
        
        int dpSpecial = coinChangeDP(specialCoins, specialAmount);
        System.out.println("DP оптимальное: " + dpSpecial + " монет (3 + 3)");
    }
}
