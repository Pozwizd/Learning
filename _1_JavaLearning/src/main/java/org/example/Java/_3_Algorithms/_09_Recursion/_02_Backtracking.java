package org.example.Java._3_Algorithms._09_Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Backtracking (возврат назад)
 */
public class _02_Backtracking {
    
    // Генерация всех подмножеств (Power Set)
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);  // Выбираем
            backtrack(nums, i + 1, current, result);  // Рекурсия
            current.remove(current.size() - 1);  // Отменяем выбор (backtrack)
        }
    }
    
    // Генерация всех перестановок
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPermute(nums, new ArrayList<>(), result, new boolean[nums.length]);
        return result;
    }
    
    private static void backtrackPermute(int[] nums, List<Integer> current, 
                                         List<List<Integer>> result, boolean[] used) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            
            current.add(nums[i]);
            used[i] = true;
            backtrackPermute(nums, current, result, used);
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }
    
    // N Queens Problem
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        backtrackQueens(board, 0, result);
        return result;
    }
    
    private static void backtrackQueens(char[][] board, int row, List<List<String>> result) {
        if (row == board.length) {
            result.add(construct(board));
            return;
        }
        
        for (int col = 0; col < board.length; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                backtrackQueens(board, row + 1, result);
                board[row][col] = '.';
            }
        }
    }
    
    private static boolean isValid(char[][] board, int row, int col) {
        // Проверка вертикали
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        
        // Проверка диагонали вверх-влево
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        
        // Проверка диагонали вверх-вправо
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        
        return true;
    }
    
    private static List<String> construct(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) {
            result.add(new String(row));
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Backtracking ===\n");
        
        int[] nums = {1, 2, 3};
        System.out.println("Подмножества [1,2,3]: " + subsets(nums));
        
        System.out.println("\nПерестановки [1,2,3]: " + permute(nums));
        
        System.out.println("\n4 Queens (количество решений): " + solveNQueens(4).size());
    }
}
