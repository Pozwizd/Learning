package org.example.Java._1_GoFPatterns.Behavioral.Command.Example1;

/**
 * Класс {@code DeleteCommand} является конкретной реализацией команды
 * для выполнения операции удаления из базы данных. Он инкапсулирует вызов
 * метода {@code delete()} у объекта {@code Database} (Получателя).
 * Этот класс реализует интерфейс {@code Command}, предоставляя конкретную
 * реализацию метода {@code execute()}.
 */
public class DeleteCommand implements Command {
    /**
     * Ссылка на объект {@code Database}, который является получателем команды.
     * Команда будет вызывать методы этого объекта для выполнения операции.
     */
    private Database database;

    /**
     * Конструктор для создания команды удаления.
     *
     * @param database Объект {@code Database}, который будет выполнять операцию.
     */
    public DeleteCommand(Database database) {
        this.database = database;
    }

    /**
     * Выполняет операцию удаления, вызывая соответствующий метод у получателя.
     */
    @Override
    public void execute() {
        database.delete();
    }
}