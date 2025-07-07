package org.example.Java._1_GoFPatterns.Behavioral.Command.Example1;

/**
 * Класс {@code SelectCommand} является конкретной реализацией команды
 * для выполнения операции выборки из базы данных. Он инкапсулирует вызов
 * метода {@code select()} у объекта {@code Database} (Получателя).
 * Этот класс реализует интерфейс {@code Command}, предоставляя конкретную
 * реализацию метода {@code execute()}.
 */
public class SelectCommand implements Command {
    /**
     * Ссылка на объект {@code Database}, который является получателем команды.
     * Команда будет вызывать методы этого объекта для выполнения операции.
     */
    private Database database;

    /**
     * Конструктор для создания команды выборки.
     *
     * @param database Объект {@code Database}, который будет выполнять операцию.
     */
    public SelectCommand(Database database) {
        this.database = database;
    }

    /**
     * Выполняет операцию выборки, вызывая соответствующий метод у получателя.
     */
    @Override
    public void execute() {
        database.select();
    }
}