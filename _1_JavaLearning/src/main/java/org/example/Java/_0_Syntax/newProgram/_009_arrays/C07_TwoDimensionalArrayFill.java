package org.example.Java._0_Syntax.newProgram._009_arrays;

public class C07_TwoDimensionalArrayFill {
    // Массивы (двумерный массив).
    public static void main(String[] args) {
        // Компилятор определяет размер массива на основании выражения инициализации.
        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println(array);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + array[i][j]);
            }
            System.out.print("\n");
        }
    }
}

