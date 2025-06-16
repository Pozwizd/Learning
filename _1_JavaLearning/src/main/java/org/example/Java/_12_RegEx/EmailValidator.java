package org.example.Java._12_RegEx;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String mailPattern =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+$";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.print("Введите email: ");
            String email = scanner.nextLine();
            if (email.equalsIgnoreCase("end")) {
                break;
            }
            Pattern pattern = Pattern.compile(mailPattern);
            Matcher matcher = pattern.matcher(email);
            if(matcher.matches()) {
                System.out.println("Email корректен");
            } else {
                System.out.println("Email некорректен");
            }
        }
    }

    private static boolean validate(String email) {
        Pattern pattern = Pattern.compile(mailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}