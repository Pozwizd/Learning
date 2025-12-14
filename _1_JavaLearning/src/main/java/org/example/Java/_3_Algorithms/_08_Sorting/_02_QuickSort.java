package org.example.Java._3_Algorithms._08_Sorting;

import java.util.Arrays;

/**
 * Быстрая сортировка O(n log n) в среднем
 */
public class _02_QuickSort {
    
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            
            quickSort(arr, low, pi - 1);  // До pivot
            quickSort(arr, pi + 1, high); // После pivot
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Берем последний элемент как pivot
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        // Ставим pivot на правильное место
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
    
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("До сортировки: " + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("После Quick Sort: " + Arrays.toString(arr));
        System.out.println("\nСложность: O(n log n) в среднем, O(n²) в худшем");
    }
}
