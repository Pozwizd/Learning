package org.example.Java._3_Algorithms._01_Complexity;

/**
 * Демонстрация различных типов сложности алгоритмов
 */
public class _01_BigOExamples {
    
    // O(1) - Константная сложность
    public static int getFirst(int[] arr) {
        return arr[0]; // Всегда одна операция
    }
    
    // O(log n) - Логарифмическая (бинарный поиск)
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
    
    // O(n) - Линейная сложность
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }
    
    // O(n log n) - Линейно-логарифмическая (сортировка слиянием)
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }
    
    // O(n²) - Квадратичная сложность (пузырьковая сортировка)
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    // O(2^n) - Экспоненциальная (наивный Фибоначчи)
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Демонстрация сложности алгоритмов ===\n");
        
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        
        System.out.println("O(1): Первый элемент = " + getFirst(arr));
        System.out.println("O(log n): Поиск 7 = индекс " + binarySearch(arr, 7));
        System.out.println("O(n): Линейный поиск 11 = индекс " + linearSearch(arr, 11));
        
        int[] unsorted = {64, 34, 25, 12, 22, 11, 90};
        bubbleSort(unsorted);
        System.out.print("O(n²): Отсортированный массив = ");
        for (int num : unsorted) System.out.print(num + " ");
        
        System.out.println("\n\nO(2^n): Фибоначчи(10) = " + fibonacci(10));
        System.out.println("Внимание: fibonacci(40) будет работать очень долго!");
    }
}
