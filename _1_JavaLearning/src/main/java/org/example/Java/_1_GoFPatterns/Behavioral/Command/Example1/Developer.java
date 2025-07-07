package org.example.Java._1_GoFPatterns.Behavioral.Command.Example1;

/**
 * Класс {@code Developer} выступает в роли Инициатора (Invoker) в паттерне "Команда".
 * Он содержит объекты команд и вызывает их метод {@code execute()} для выполнения
 * соответствующих операций. {@code Developer} не знает, как именно выполняются
 * операции; он лишь знает, какие команды нужно вызвать.
 */
public class Developer {
    /**
     * Команда для вставки записи.
     */
    private Command insert;
    /**
     * Команда для обновления записи.
     */
    private Command update;
    /**
     * Команда для выборки записи.
     */
    private Command select;
    /**
     * Команда для удаления записи.
     */
    private Command delete;

    /**
     * Конструктор для создания объекта {@code Developer} с заданными командами.
     *
     * @param insert Команда для вставки.
     * @param update Команда для обновления.
     * @param select Команда для выборки.
     * @param delete Команда для удаления.
     */
    public Developer(Command insert, Command update, Command select, Command delete) {
        this.insert = insert;
        this.update = update;
        this.select = select;
        this.delete = delete;
    }

    /**
     * Вызывает команду вставки записи.
     */
    public void insertRecord() {
        insert.execute();
    }

    /**
     * Вызывает команду обновления записи.
     */
    public void updateRecord() {
        update.execute();
    }

    /**
     * Вызывает команду выборки записи.
     */
    public void selectRecord() {
        select.execute();
    }

    /**
     * Вызывает команду удаления записи.
     */
    public void deleteRecord() {
        delete.execute();
    }
}