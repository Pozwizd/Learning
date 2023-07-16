package hw3;

import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String input = scanner.nextLine();
        System.out.print("Введите ключ шифрования: ");
        int key = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Зашифровать или расшифровать? (e/d): ");
        String option = scanner.nextLine();

        if (option.equals("e")) {
            CaesarCipher caesarCipher = new CaesarCipher(key, input);
            System.out.println("Результат шифрования: " + caesarCipher.output());
        } else if (option.equals("d")) {
            CaesarDecipher caesarDecipher = new CaesarDecipher(key, input);
            System.out.println("Результат расшифрования: " + caesarDecipher.output());
        } else {
            System.out.println("Некорректный ввод");
        }
    }
}

class CaesarCipher {
    private String input;
    private int key;

    public CaesarCipher(int key, String input) {
        this.key = key;
        this.input = input;
    }

    public String output() {
        StringBuilder outputBuilder = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) ((c - 'a' + key) % 26 + 'a');
                outputBuilder.append(shifted);
            } else {
                outputBuilder.append(c);
            }
        }
        return outputBuilder.toString();
    }
}

class CaesarDecipher {
    private String input;
    private int key;

    public CaesarDecipher(int key, String input) {
        this.key = key;
        this.input = input;
    }

    public String output() {
        StringBuilder outputBuilder = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) ((c - 'a' - key + 26) % 26 + 'a');
                outputBuilder.append(shifted);
            } else {
                outputBuilder.append(c);
            }
        }
        return outputBuilder.toString();
    }
}