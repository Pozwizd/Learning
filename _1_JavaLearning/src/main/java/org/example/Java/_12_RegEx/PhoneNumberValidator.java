package org.example.Java._12_RegEx;

import java.util.Scanner;

public class PhoneNumberValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine();

        if (isValidPhoneNumber(phoneNumber)) {
            System.out.println("Номер телефона корректный.");
        } else {
            System.out.println("Номер телефона некорректный.");
        }
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+380(67|96|97|98)\\d{7}$";
        return phoneNumber.matches(regex);
    }
}