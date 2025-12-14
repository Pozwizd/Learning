package org.example.Java._3_Algorithms._02_Arrays;

/**
 * Техника Two Pointers для работы с массивами
 */
public class _03_TwoPointers {
    
    // Проверка на палиндром
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    // Найти пару чисел с заданной суммой в отсортированном массиве
    public static int[] twoSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
    
    // Удаление дубликатов из отсортированного массива
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0) return 0;
        
        int writeIndex = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[writeIndex++] = arr[i];
            }
        }
        return writeIndex;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Two Pointers Technique ===\n");
        
        // Palindrome
        System.out.println("\"racecar\" - палиндром? " + isPalindrome("racecar"));
        System.out.println("\"hello\" - палиндром? " + isPalindrome("hello"));
        
        // Two Sum
        int[] sorted = {2, 7, 11, 15, 20};
        int[] result = twoSum(sorted, 18);
        System.out.println("\nИндексы для суммы 18: [" + result[0] + ", " + result[1] + "]");
        
        // Remove duplicates
        int[] withDuplicates = {1, 1, 2, 2, 2, 3, 4, 4, 5};
        int newLength = removeDuplicates(withDuplicates);
        System.out.print("\nПосле удаления дубликатов: ");
        for (int i = 0; i < newLength; i++) {
            System.out.print(withDuplicates[i] + " ");
        }
    }
}
