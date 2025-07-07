package org.example.Java._1_GoFPatterns.Behavioral.Command.Example1;

/**
 * Класс {@code CommandClient} демонстрирует использование паттерна "Команда".
 * Он создает объекты Получателя ({@code Database}), конкретные команды
 * ({@code InsertCommand}, {@code UpdateCommand}, {@code SelectCommand}, {@code DeleteCommand})
 * и Инициатора ({@code Developer}). Клиент конфигурирует Инициатора с командами
 * и затем вызывает операции через Инициатора, не взаимодействуя напрямую с Получателем.
 */
public class CommandClient {
    /**
     * Главный метод для запуска демонстрации паттерна.
     *
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        // 1. Создание объекта Получателя (Receiver).
        // Получатель - это объект, который знает, как выполнить конкретные операции.
        Database database = new Database();

        // 2. Создание объектов конкретных команд (Concrete Commands).
        // Каждая команда инкапсулирует вызов определенного метода у Получателя.
        Command insertCommand = new InsertCommand(database);
        Command updateCommand = new UpdateCommand(database);
        Command selectCommand = new SelectCommand(database);
        Command deleteCommand = new DeleteCommand(database);

        // 3. Создание объекта Инициатора (Invoker).
        // Инициатор - это объект, который хранит команды и вызывает их выполнение.
        // Он не знает деталей реализации команд или получателя.
        Developer developer = new Developer(
                insertCommand,
                updateCommand,
                selectCommand,
                deleteCommand
        );

        // 4. Выполнение операций через Инициатора.
        // Клиент вызывает методы Инициатора, который, в свою очередь,
        // делегирует выполнение соответствующим командам.
        System.out.println("--- Выполнение операций с базой данных через команды ---");
        developer.insertRecord(); // Вызывает InsertCommand, которая вызывает database.insert()
        developer.updateRecord(); // Вызывает UpdateCommand, которая вызывает database.update()
        developer.selectRecord(); // Вызывает SelectCommand, которая вызывает database.select()
        developer.deleteRecord(); // Вызывает DeleteCommand, которая вызывает database.delete()
        System.out.println("--------------------------------------------------------");
    }
}