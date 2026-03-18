package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_005.task_4.src;

import java.util.Scanner;

/**
 * Created by Evgeniy on 12.03.2016.
 */
public class Premium {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        double money = sc.nextDouble();

        if (year >= 0 && year <= 4) {
            money = money * 1.10 - money;
            System.out.println("Премия сотрудника с выслугой " + year + " года составит 10% от зп - " + money + " грн");
        } else if (year >= 5 && year <= 9) {
            money = money * 1.15 - money;
            System.out.println("Премия сотрудника с выслугой " + year + " года составит 15% от зп - " + money + " грн");
        } else if (year >= 10 && year <= 14) {
            money = money * 1.25 - money;
            System.out.println("Премия сотрудника с выслугой " + year + " года составит 25% от зп - " + money + " грн");
        } else if (year >= 15 && year <= 19) {
            money = money * 1.35 - money;
            System.out.println("Премия сотрудника с выслугой " + year + " года составит 35% от зп - " + money + " грн");
        } else if (year >= 20 && year <= 24) {
            money = money * 1.45 - money;
            System.out.println("Премия сотрудника с выслугой " + year + " года составит 45% от зп - " + money + " грн");
        } else if (year >= 25) {
            money = money * 1.50 - money;
            System.out.println("Премия сотрудника с выслугой " + year + " года составит 50% от зп - " + money + " грн");
        }
    }
}
