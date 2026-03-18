package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_008.task_2.src;

import java.util.Scanner;

/**
 * Created by Evgeniy on 13.03.2016.
 */
public class Bank {
    public static void main(String[] args) {
        // Вызываем метод с некоторыми значениями
        payment(100);
        payment(2000);
    }

    // Создаем метод payment, который в качестве аргумента принимает сумму платежа money
    public static void payment(double money) {
        double credit = 1000;     // Кредит
        double debts = 0;         // Долг
        double overPayment = 0;   // Переплата

        // Создаем условие, если ваши деньги больше кредита, то выводит сообщение:
        // Переплата составила ... грн
        if (money > credit) {
            overPayment = money - credit;
            System.out.println("Переплата составила " + overPayment + " грн");
            System.out.println("Кредит погашен");
        // Если деньги, которые вы вносите, будут равны кредиту, то выводим сообщение:
        // Кредит погашен
        } else if (money == credit) {
            System.out.println("Кредит погашен");
        } else {
            // Если сумма денег была внесена меньше, чем долг, то выводим сообщение:
            // Задолженость состявляет ... грн
            debts = credit - money;
            System.out.println("Задолженность составляет " + debts + " грн");
        }
    }

}
