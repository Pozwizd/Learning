package org.example.Java._12_RegEx;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    private static final String passwordPattern =
            "^(?=.*[0-9])" +
            "(?=.*[.,/=?])" +
            "(?=.*[A-Z])" +
            "(?=\\S+$).{8,100}$";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);

        if(matcher.matches()) {
            System.out.println("Пароль корректен");
        } else {
            System.out.println("Пароль некорректен");
        }
    }
}