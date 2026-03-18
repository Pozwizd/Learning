package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_003.task_4.src;

/**
 * Created by Evgeniy on 09.03.2016.
 */
public class Volume {
    public static void main(String[] args) {

        // Желательно не объявлять переменные с большой(первой) буквы

        double R = 10;
        double h = 4;

        // double S = 2 * Math.PI * Math.pow(R, 2) + 2 * Math.PI * Math.pow(R, 2);

        double V = Math.PI * Math.pow(R, 2) * h;    // Объем цилиндра
        double S = 2 * Math.PI * R * (R + h);       // Площадь

        System.out.println("Объем цилиндра V: " + V);
        System.out.println("Площадь S: " + S);
    }
}
