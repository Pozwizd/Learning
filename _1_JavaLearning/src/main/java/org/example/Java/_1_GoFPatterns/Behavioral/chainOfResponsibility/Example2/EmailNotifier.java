package org.example.Java._1_GoFPatterns.Behavioral.chainOfResponsibility.Example2;

/**
 * Класс {@code EmailNotifier} является конкретной реализацией
 * абстрактного уведомителя {@code Notifier}. Он отвечает за отправку
 * уведомлений по электронной почте. Этот уведомитель обрабатывает
 * сообщения, которые соответствуют его установленному приоритету.
 */
public class EmailNotifier extends Notifier {
    /**
     * Конструктор для создания нового уведомителя по электронной почте.
     *
     * @param priority Приоритет, который может обрабатывать данный уведомитель.
     */
    public EmailNotifier(int priority) {
        super(priority);
    }

    /**
     * Реализация абстрактного метода {@code write} из базового класса {@code Notifier}.
     * Этот метод выводит сообщение уведомления в консоль, имитируя отправку по электронной почте.
     *
     * @param message Сообщение, которое необходимо отправить.
     */
    @Override
    void write(String message) {
        System.out.println("Sending email: " + message);
    }
}