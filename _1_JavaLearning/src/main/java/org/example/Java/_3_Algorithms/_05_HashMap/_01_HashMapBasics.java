package org.example.Java._3_Algorithms._05_HashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Основы работы с HashMap
 */
public class _01_HashMapBasics {
    
    // Подсчет частоты элементов
    public static Map<Integer, Integer> countFrequency(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        return freq;
    }
    
    // Проверка на анаграмму
    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        
        Map<Character, Integer> map = new HashMap<>();
        
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        for (char c : s2.toCharArray()) {
            if (!map.containsKey(c)) return false;
            map.put(c, map.get(c) - 1);
            if (map.get(c) == 0) map.remove(c);
        }
        
        return map.isEmpty();
    }
    
    // Two Sum с использованием HashMap
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        
        return new int[]{-1, -1};
    }
    
    // Первый неповторяющийся символ
    public static char firstUnique(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        
        for (char c : s.toCharArray()) {
            if (freq.get(c) == 1) {
                return c;
            }
        }
        
        return '_'; // не найден
    }
    
    public static void main(String[] args) {
        System.out.println("=== HashMap Examples ===\n");
        
        // Частота
        int[] arr = {1, 2, 2, 3, 3, 3, 4};
        System.out.println("Частота элементов: " + countFrequency(arr));
        
        // Анаграмма
        System.out.println("\n\"listen\" и \"silent\" - анаграммы? " + isAnagram("listen", "silent"));
        System.out.println("\"hello\" и \"world\" - анаграммы? " + isAnagram("hello", "world"));
        
        // Two Sum
        int[] nums = {2, 7, 11, 15};
        int[] result = twoSum(nums, 9);
        System.out.println("\nДва числа с суммой 9: индексы " + result[0] + " и " + result[1]);
        
        // Первый уникальный
        String s = "leetcode";
        System.out.println("\nПервый уникальный в \"" + s + "\": " + firstUnique(s));
    }
}
