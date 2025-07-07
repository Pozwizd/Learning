package org.example.Java._1_GoFPatterns.Behavioral.chainOfResponsibility.Example2;

/**
 * Класс {@code SMSNotifier} является конкретной реализацией
 * абстрактного уведомителя {@code Notifier}. Он отвечает за отправку
 * уведомлений в виде SMS-сообщений менеджеру. Этот уведомитель
 * обрабатывает сообщения, которые соответствуют его установленному приоритету.
 */
public class SMSNotifier extends Notifier {
    /**
     * Конструктор для создания нового уведомителя SMS.
     *
     * @param priority Приоритет, который может обрабатывать данный уведомитель.
     */
    public SMSNotifier(int priority) {
        super(priority);
    }

    /**
     * Реализация абстрактного метода {@code write} из базового класса {@code Notifier}.
     * Этот метод выводит сообщение уведомления в консоль, имитируя отправку SMS.
     *
     * @param message Сообщение, которое необходимо отправить.
     */
    @Override
    void write(String message) {
        System.out.println("Sending SMS to manager: " + message);
    }
}