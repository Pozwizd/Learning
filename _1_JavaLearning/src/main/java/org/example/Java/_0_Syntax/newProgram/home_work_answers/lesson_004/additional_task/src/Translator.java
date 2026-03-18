package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_004.additional_task.src;

/**
 * Created by Evgeniy on 09.03.2016.
 */
import java.util.Scanner;

public class Translator {
    public static void main(String[] args) {
        System.out.println("Введите слово на русском языке:");

        // Ввод с клавиатуры
        Scanner in = new Scanner(System.in);
        String str = in.next();

        // В переключателе заносим наше значение и проверяем его
        switch (str) {
            case "солнечный":
                System.out.println("sunny");
                break;
            case "дождь":
                System.out.println("rain");
                break;
            case "холод":
                System.out.println("cold");
                break;
            case "ветер":
                System.out.println("wind");
                break;
            case "жара":
                System.out.println("heat");
                break;
            case "туман":
                System.out.println("fog");
                break;
            case "снег":
                System.out.println("снег");
                break;
            case "прохладный":
                System.out.println("cool");
                break;
            case "ливень":
                System.out.println("shower");
                break;
            default:
                System.out.println("Такого слова нет");
        }
    }
}