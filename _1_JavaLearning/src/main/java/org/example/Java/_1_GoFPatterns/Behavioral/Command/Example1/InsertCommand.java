package org.example.Java._1_GoFPatterns.Behavioral.Command.Example1;

/**
 * Класс {@code InsertCommand} является конкретной реализацией команды
 * для выполнения операции вставки в базу данных. Он инкапсулирует вызов
 * метода {@code insert()} у объекта {@code Database} (Получателя).
 * Этот класс реализует интерфейс {@code Command}, предоставляя конкретную
 * реализацию метода {@code execute()}.
 */
public class InsertCommand implements Command {
    /**
     * Ссылка на объект {@code Database}, который является получателем команды.
     * Команда будет вызывать методы этого объекта для выполнения операции.
     */
    private Database database;

    /**
     * Конструктор для создания команды вставки.
     *
     * @param database Объект {@code Database}, который будет выполнять операцию.
     */
    public InsertCommand(Database database) {
        this.database = database;
    }

    /**
     * Выполняет операцию вставки, вызывая соответствующий метод у получателя.
     */
    @Override
    public void execute() {
        database.insert();
    }
}