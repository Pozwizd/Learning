package org.example.Java._3_Algorithms._08_Sorting;

import java.util.Arrays;

/**
 * Сортировка слиянием O(n log n)
 */
public class _03_MergeSort {
    
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSort(arr, left, mid);        // Сортируем левую половину
            mergeSort(arr, mid + 1, right);   // Сортируем правую половину
            merge(arr, left, mid, right);     // Сливаем
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // Временные массивы
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);
        
        // Слияние
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        
        // Копируем оставшиеся элементы
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
    
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        
        System.out.println("До сортировки: " + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("После Merge Sort: " + Arrays.toString(arr));
        System.out.println("\nСложность: O(n log n) всегда");
        System.out.println("Пространство: O(n)");
    }
}
