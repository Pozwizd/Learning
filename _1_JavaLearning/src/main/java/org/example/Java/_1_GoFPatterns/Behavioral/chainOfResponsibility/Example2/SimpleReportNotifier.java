package org.example.Java._1_GoFPatterns.Behavioral.chainOfResponsibility.Example2;

/**
 * Класс {@code SimpleReportNotifier} является конкретной реализацией
 * абстрактного уведомителя {@code Notifier}. Он отвечает за отправку
 * уведомлений в виде простого отчета, обычно выводя их в консоль.
 * Этот уведомитель обрабатывает сообщения, которые соответствуют его
 * установленному приоритету.
 */
public class SimpleReportNotifier extends Notifier {
    /**
     * Конструктор для создания нового уведомителя простого отчета.
     *
     * @param priority Приоритет, который может обрабатывать данный уведомитель.
     */
    public SimpleReportNotifier(int priority) {
        super(priority);
    }

    /**
     * Реализация абстрактного метода {@code write} из базового класса {@code Notifier}.
     * Этот метод выводит сообщение уведомления в консоль, имитируя создание простого отчета.
     *
     * @param message Сообщение, которое необходимо отправить.
     */
    @Override
    void write(String message) {
        System.out.println("Notifying using simple report: " + message);
    }
}