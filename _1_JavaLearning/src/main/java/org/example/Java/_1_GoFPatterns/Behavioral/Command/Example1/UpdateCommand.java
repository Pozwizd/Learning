package org.example.Java._1_GoFPatterns.Behavioral.Command.Example1;

/**
 * Класс {@code UpdateCommand} является конкретной реализацией команды
 * для выполнения операции обновления в базе данных. Он инкапсулирует вызов
 * метода {@code update()} у объекта {@code Database} (Получателя).
 * Этот класс реализует интерфейс {@code Command}, предоставляя конкретную
 * реализацию метода {@code execute()}.
 */
public class UpdateCommand implements Command {
    /**
     * Ссылка на объект {@code Database}, который является получателем команды.
     * Команда будет вызывать методы этого объекта для выполнения операции.
     */
    private Database database;

    /**
     * Конструктор для создания команды обновления.
     *
     * @param database Объект {@code Database}, который будет выполнять операцию.
     */
    public UpdateCommand(Database database) {
        this.database = database;
    }

    /**
     * Выполняет операцию обновления, вызывая соответствующий метод у получателя.
     */
    @Override
    public void execute() {
        database.update();
    }
}