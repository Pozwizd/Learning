package org.example.Java._1_GoFPatterns.Behavioral.chainOfResponsibility.Example2;

/**
 * Класс {@code ChainOfResponsibilityClient2} демонстрирует использование
 * паттерна "Цепочка обязанностей" для системы уведомлений. Он создает
 * различные типы уведомителей (простой отчет, email, SMS), настраивает
 * их в цепочку и отправляет уведомления с разными уровнями приоритета.
 * Клиент взаимодействует только с первым элементом цепочки, не зная,
 * какой именно уведомитель будет обрабатывать сообщение.
 */
public class ChainOfResponsibilityClient2 {
    /**
     * Главный метод для запуска демонстрации паттерна.
     *
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        // 1. Создание экземпляров конкретных уведомителей.
        // Каждый уведомитель имеет свой приоритет, определяющий,
        // какие сообщения он будет обрабатывать.
        Notifier reportNotifier = new SimpleReportNotifier(Priority.ROUTINE);
        Notifier emailNotifier = new EmailNotifier(Priority.IMPORTANT);
        Notifier smsNotifier = new SMSNotifier(Priority.ASAP);

        // 2. Настройка цепочки обязанностей.
        // Уведомления будут передаваться от уведомителя с низким приоритетом
        // к уведомителю с более высоким приоритетом.
        // reportNotifier -> emailNotifier -> smsNotifier
        reportNotifier.setNextNotifier(emailNotifier);
        emailNotifier.setNextNotifier(smsNotifier);

        // 3. Отправка уведомлений с различными уровнями приоритета.

        // Сценарий 1: Рутинное уведомление.
        // Будет обработано только SimpleReportNotifier, так как его приоритет (ROUTINE)
        // соответствует уровню уведомления. Затем запрос передастся дальше,
        // но другие уведомители не будут его обрабатывать, так как их приоритеты выше.
        System.out.println("--- Уведомление с приоритетом ROUTINE ---");
        reportNotifier.notifyManager("Everything is OK", Priority.ROUTINE);
        System.out.println("-----------------------------------------\n");

        // Сценарий 2: Важное уведомление.
        // Будет обработано SimpleReportNotifier (поскольку IMPORTANT >= ROUTINE)
        // и EmailNotifier (поскольку IMPORTANT >= IMPORTANT).
        // Затем запрос передастся SMSNotifier, но он не будет его обрабатывать,
        // так как ASAP > IMPORTANT.
        System.out.println("--- Уведомление с приоритетом IMPORTANT ---");
        reportNotifier.notifyManager("Houston, we've had a problem here", Priority.IMPORTANT);
        System.out.println("-------------------------------------------\n");

        // Сценарий 3: Срочное уведомление.
        // Будет обработано SimpleReportNotifier (ASAP >= ROUTINE),
        // EmailNotifier (ASAP >= IMPORTANT) и SMSNotifier (ASAP >= ASAP).
        System.out.println("--- Уведомление с приоритетом ASAP ---");
        reportNotifier.notifyManager("System critical error! Immediate action required!", Priority.ASAP);
        System.out.println("--------------------------------------\n");
    }
}