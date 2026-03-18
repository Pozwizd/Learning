package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_007.task_3.src;

import java.util.Scanner;

/**
 * Created by Evgeniy on 12.03.2016.
 */

public class Conversion {

    // Создаем константы и заносим текущий курс валют на 11-16 строки

    public static final double UAH_to_USD = 0.0388;
    public static final double UAH_to_EUR = 0.0346;
    public static final double USD_to_UAH = 25.7656;
    public static final double USD_to_EUR = 0.8925;
    public static final double EUR_to_UAH = 28.8682;
    public static final double EUR_to_USD = 1.1204;

    public static void main(String[] args) {

        // Ввод с клавиатуры

        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите валюту, которую хотите поменять, гривны/ доллары/ евро" + "\n" + "h / d / e");
        String currency = sc.next();
        System.out.println("Во что вы хотите конвертировать? гривны/ доллары/ евро" + "\n" + "h / d / e");
        String convCurrency = sc.next();
        System.out.println("Введите сумму:");
        double money = sc.nextDouble();

        // Проверка на отрицательные числа

        if (money < 0) {
            System.out.println("Отрицательные числа вводить нельзя");
            return;
        }

        // Первый вариант решения. С помощью условной конструкции
        // Проверяем, что пользователь хочет обменять и в зависимости от валюты выполняем конвертацию

        if (currency.equals("h") && convCurrency.equals("d")) {
            System.out.println("Конвертация гривны в доллары : " + convUAhToUSD(money) + " $");
        } else if (currency.equals("h") && convCurrency.equals("e")) {
            System.out.println("Конвертация гривны в евро : " + convUAhToEUR(money) + " €");
        } else if (currency.equals("d") && convCurrency.equals("h")) {
            System.out.println("Конвертация доллары в гривны : " + convUSDtoUAh(money) + " UAH");
        } else if (currency.equals("d") && convCurrency.equals("e")) {
            System.out.println("Конвертация доллары в евро : " + convUSDtoEUR(money) + " €");
        } else if (currency.equals("e") && convCurrency.equals("h")) {
            System.out.println("Конвертация евро в гривны : " + convEURtoUAh(money) + " UAH");
        } else if (currency.equals("e") && convCurrency.equals("d")) {
            System.out.println("Конвертация евро в доллары : " + convEURtoUSD(money) + " €");
        } else {
            System.out.println("К сожалению, такой валюты нет.");
        }

        // Второй вариант решения. С помощью переключателя

        /*switch (currency) {
            case "h":
                if (convCurrency.equals("d")) {
                    System.out.println("Конвертация гривны в доллары: " + convUAhToUSD(money) + " $");
                } else if (convCurrency.equals("e")) {
                    System.out.println("Конвертация гривны в евро: " + convUAhToEUR(money) + " €");
                }
                break;
            case "d":
                if (convCurrency.equals("h")) {
                    System.out.println("Конвертация доллары в гривны: " + convUSDtoUAh(money) + " UAH");
                } else if (convCurrency.equals("e")) {
                    System.out.println("Конвертация доллары в евро: " + convUSDtoEUR(money) + " €");
                }
                break;
            case "e":
                if (convCurrency.equals("h")) {
                    System.out.println("Конвертация евро в гривны: " + convEURtoUAh(money) + " UAH");
                } else if (convCurrency.equals("d")) {
                    System.out.println("Конвертация евро в доллары: " + convEURtoUSD(money) + " €");
                }
                break;
            default:
                System.out.println("К сожалению, такой валюты нет.");
                return;
        }*/

    }

    // Создаем методы и в них производим конвертацию валют

    public static double convUAhToUSD(double money) {
        return money * UAH_to_USD;
    }

    public static double convUAhToEUR(double money) {
        return money * UAH_to_EUR;
    }

    public static double convUSDtoUAh(double money) {
        return money * USD_to_UAH;
    }

    public static double convUSDtoEUR(double money) {
        return money * USD_to_EUR;
    }

    public static double convEURtoUAh(double money) {
        return money * EUR_to_UAH;
    }

    public static double convEURtoUSD(double money) {
        return money * EUR_to_USD;
    }
}
