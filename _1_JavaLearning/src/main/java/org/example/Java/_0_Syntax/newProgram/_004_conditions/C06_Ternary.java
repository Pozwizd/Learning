package org.example.Java._0_Syntax.newProgram._004_conditions;

public class C06_Ternary {
    // Тернарная условная операция.

    public static void main(String[] args) {
        int a = 1, b = 2, c = -5, max = 0;

        max = a > b ? (c = a) : (c = b); // сначала с = а, а только потом max = c
        System.out.println(max);
    }
}

