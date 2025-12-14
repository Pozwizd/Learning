package org.example.Java._3_Algorithms._02_Arrays;

/**
 * Техника Sliding Window (Скользящее окно)
 */
public class _04_SlidingWindow {
    
    // Максимальная сумма подмассива размера k
    public static int maxSumSubarray(int[] arr, int k) {
        if (arr.length < k) return -1;
        
        int maxSum = 0;
        int windowSum = 0;
        
        // Первое окно
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;
        
        // Скользим окном
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k]; // добавляем новый, убираем старый
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }
    
    // Длина наименьшего подмассива с суммой >= target
    public static int minSubArrayLen(int target, int[] arr) {
        int minLen = Integer.MAX_VALUE;
        int windowSum = 0;
        int left = 0;
        
        for (int right = 0; right < arr.length; right++) {
            windowSum += arr[right];
            
            while (windowSum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                windowSum -= arr[left];
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    // Максимальная длина подстроки с уникальными символами
    public static int longestUniqueSubstring(String s) {
        int[] charIndex = new int[128]; // ASCII
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            left = Math.max(left, charIndex[c]);
            maxLength = Math.max(maxLength, right - left + 1);
            charIndex[c] = right + 1;
        }
        
        return maxLength;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Sliding Window ===\n");
        
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println("Макс сумма подмассива размера " + k + ": " + maxSumSubarray(arr, k));
        
        int[] arr2 = {2, 3, 1, 2, 4, 3};
        System.out.println("\nМин длина подмассива с суммой >= 7: " + minSubArrayLen(7, arr2));
        
        String s = "abcabcbb";
        System.out.println("\nДлина макс подстроки без повторов в \"" + s + "\": " + longestUniqueSubstring(s));
    }
}
