package org.example.Java._3_Algorithms._08_Sorting;

import java.util.Arrays;

/**
 * Пузырьковая сортировка O(n²)
 */
public class _01_BubbleSort {
    
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            // Если не было обменов, массив уже отсортирован
            if (!swapped) break;
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("До сортировки: " + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("После Bubble Sort: " + Arrays.toString(arr));
        System.out.println("\nСложность: O(n²)");
    }
}
