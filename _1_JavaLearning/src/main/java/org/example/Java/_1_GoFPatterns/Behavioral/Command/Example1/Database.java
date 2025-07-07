package org.example.Java._1_GoFPatterns.Behavioral.Command.Example1;

/**
 * Класс {@code Database} выступает в роли Получателя (Receiver) в паттерне "Команда".
 * Он содержит бизнес-логику и выполняет фактические операции, которые
 * инкапсулируются в объектах команд. В данном примере, {@code Database}
 * имитирует операции с базой данных, такие как вставка, обновление, выборка и удаление записей.
 */
public class Database {
    /**
     * Имитирует операцию вставки записи в базу данных.
     */
    public void insert() {
        System.out.println("Inserting record...");
    }

    /**
     * Имитирует операцию обновления записи в базе данных.
     */
    public void update() {
        System.out.println("update record...");
    }

    /**
     * Имитирует операцию выборки записи из базы данных.
     */
    public void select() {
        System.out.println("select record...");
    }

    /**
     * Имитирует операцию удаления записи из базы данных.
     */
    public void delete() {
        System.out.println("delete record...");
    }
}