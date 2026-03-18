package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_003.task_1.src;

/**
 * Created by Evgeniy on 09.03.2016.
 */
public class Task_1 {
    public static void main(String[] args) {
        int x = 10, y = 12, z = 3;

        x += y - x++ * z;		// -8
        System.out.println(x);
        z = --x - y * 5;		// -69
        System.out.println(z);
        y /= x + 5 % z;			// -3
        System.out.println(y);
        z = x++ + y * 5;		// -24
        System.out.println(z);
        x = y - x++ * z;		// -195
        System.out.println(x);
    }
}
