package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_005.task_3.src;

/**
 * Created by Evgeniy on 12.03.2016.
 */
public class LogicOperations {
    public static void main(String[] args) {
        int x = 5, y = 10, z = 15;

        x += y >> x++ * z;
        System.out.println("x += y >> x++ * z\t\tОтвет  x = " + x);

        z = ++x & y * 5;
        System.out.println("z = ++x & y * 5\t\t\tОтвет  z = " + z);

        y /= x + 5 | z;
        System.out.println("y /= x + 5 | z\t\t\tОтвет  y = " + y);

        z = x++ & y * 5;
        System.out.println("z = x++ & y * 5z\t\tОтвет  z = " + z);

        x = y << x++ ^ z;
        System.out.println("x = y << x++ ^ z\t\tОтвет  x = " + x);

    }
}
