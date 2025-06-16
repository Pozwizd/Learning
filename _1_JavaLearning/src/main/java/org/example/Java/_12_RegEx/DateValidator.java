package org.example.Java._12_RegEx;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (true) {
            System.out.print("Введите дату: ");
            input = scanner.nextLine();
            if (isValidDate(input)) {
                extractDateInformation(input);
            } else {
                System.out.println("Некорректная дата.");
            }
        }

    }

    // Паттерн для проверки даты в форматах "дд/мм/гггг", "дд.мм.гггг чч:мм:сс"
    private final static Pattern fullDatePattern = Pattern.compile(
            "^([0-9]{1,2})[/.]([0-9]{1,2})[/.]([0-9]{4})(\\s([0-9]{2}):([0-9]{2}):([0-9]{2}))?$");
    // Паттерн для проверки года
    private final static Pattern yearsPattern = Pattern.compile("^([0-9]{4})$");
    // Паттерн для проверки даты в формате "мм.гггг"
    private final static Pattern datePattern = Pattern.compile("(([0-9]{1,2})[/.]([0-9]{4}))$");

    private static boolean isValidDate(String input) {
        if (fullDatePattern.matcher(input).matches()) {
            return true;
        } else if (yearsPattern.matcher(input).matches()) {
            return true;
        } else return datePattern.matcher(input).matches();
    }

    private static void extractDateInformation(String input) {
        String day = "";
        String month = "";
        String year = "";
        String hours = "";
        String minutes = "";
        String seconds = "";

        Matcher matcher = fullDatePattern.matcher(input);
        if (matcher.matches()) {
            day = matcher.group(1);
            month = matcher.group(2);
            year = matcher.group(3);
            hours = matcher.group(5) != null ? matcher.group(5) : "";
            minutes = matcher.group(6) != null ? matcher.group(6) : "";
            seconds = matcher.group(7) != null ? matcher.group(7) : "";


            if (!isValidDay(day)) {
                System.out.println("Некорректный день.");
                return;
            }
            if (!isValidMonth(month)) {
                System.out.println("Некорректный месяц.");
                return;
            }
            if (!isValidHours(hours)) {
                System.out.println("Некорректные часы.");
                return;
            }
            if (!isValidMinutes(minutes)) {
                System.out.println("Некорректные минуты.");
                return;
            }
            if (!isValidSeconds(seconds)) {
                System.out.println("Некорректные секунды.");
                return;
            }
        } else {
            matcher = datePattern.matcher(input);
            if (matcher.matches()) {
                month = matcher.group(2);
                year = matcher.group(3);


                if (!isValidMonth(month)) {
                    System.out.println("Некорректный месяц.");
                    return;
                }
            } else {
                matcher = yearsPattern.matcher(input);
                if (matcher.matches()) {
                    year = matcher.group(1);
                }
            }
        }
        System.out.println("Дата корректна.");
        System.out.println("Информация о дате:");
        if (!day.isEmpty()) {
            System.out.println("День: " + day);
        }
        if (!month.isEmpty()) {
            System.out.println("Месяц: " + month);
        }
        if (!year.isEmpty()) {
            System.out.println("Год: " + year);
        }
        if (!hours.isEmpty()) {
            System.out.println("Часы: " + hours);
        }
        if (!minutes.isEmpty()) {
            System.out.println("Минуты: " + minutes);
        }
        if (!seconds.isEmpty()) {
            System.out.println("Секунды: " + seconds);
        }
    }

    private static boolean isValidDay(String day) {
        if (day.isEmpty()) {
            return true; // Допускается пустое значение
        }

        int dayValue = Integer.parseInt(day);
        return dayValue >= 1 && dayValue <= 31;
    }

    private static boolean isValidMonth(String month) {
        if (month.isEmpty()) {
            return true;
        }

        int monthValue = Integer.parseInt(month);
        return monthValue >= 1 && monthValue <= 12;
    }

    private static boolean isValidHours(String hours) {
        if (hours.isEmpty()) {
            return true;
        }

        int hoursValue = Integer.parseInt(hours);
        return hoursValue >= 0 && hoursValue <= 24;
    }

    private static boolean isValidMinutes(String minutes) {
        if (minutes.isEmpty()) {
            return true;
        }

        int minutesValue = Integer.parseInt(minutes);
        return minutesValue >= 0 && minutesValue <= 59;
    }

    private static boolean isValidSeconds(String seconds) {
        if (seconds.isEmpty()) {
            return true;
        }
        int secondsValue = Integer.parseInt(seconds);
        return secondsValue >= 0 && secondsValue <= 59;
    }
}