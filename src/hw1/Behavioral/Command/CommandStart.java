package hw1.Behavioral.Command;


import java.util.ArrayList;
import java.util.List;

interface Command {
    void execute();

    void undo();
}

class StartCommand implements Command {
    private final Computer computer;

    public StartCommand(Computer computer) {
        this.computer = computer;
    }

    public void execute() {
        computer.start();
    }

    public void undo() {
        computer.shutdown();
    }
}

class ShutdownCommand implements Command {
    private final Computer computer;

    public ShutdownCommand(Computer computer) {
        this.computer = computer;
    }

    public void execute() {
        computer.shutdown();
    }

    public void undo() {
        computer.start();
    }
}

class RestartCommand implements Command {
    private final Computer computer;

    public RestartCommand(Computer computer) {
        this.computer = computer;
    }

    public void execute() {
        computer.restart();
    }

    public void undo() {
        computer.shutdown();
        computer.start();
    }
}

class CheckDiskCommand implements Command {
    private final Computer computer;

    public CheckDiskCommand(Computer computer) {
        this.computer = computer;
    }

    public void execute() {
        System.out.println("Проверка диска");
        computer.checkDisk();
    }

    public void undo() {
    }
}

class CheckMemoryCommand implements Command {
    private final Computer computer;

    public CheckMemoryCommand(Computer computer) {
        this.computer = computer;
    }

    public void execute() {
        System.out.println("Проверка памяти");
        computer.checkMemory();
    }

    public void undo() {
    }
}

class CheckNetworkCommand implements Command {
    private final Computer computer;

    public CheckNetworkCommand(Computer computer) {
        this.computer = computer;
    }

    public void execute() {
        System.out.println("Проверка сети");
        computer.checkNetwork();
    }

    public void undo() {
    }
}

class Invoker {
    private final List<Command> commands = new ArrayList<>();
    private int current = -1;

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommand() {
        if (current < commands.size() - 1) {
            commands.get(current + 1).execute();
            current++;
        }
    }

    public void undoCommand() {
        if (current >= 0) {
            commands.get(current).undo();
            current--;
        }
    }

}

class Computer {
    public void start() {
        System.out.println("Запуск компьютера...");
    }

    public void shutdown() {
        System.out.println("Выключение компьютера...");
    }

    public void restart() {
        System.out.println("Перезагрузка компьютера...");
    }

    public void checkDisk() {
        System.out.println("Проверка диска...");
    }

    public void checkMemory() {
        System.out.println("Проверка памяти...");
    }

    public void checkNetwork() {
        System.out.println("Проверка сети...");
    }
}

public class CommandStart {
    public static void main
            (String[] args) {
        Computer computer = new Computer();
        Invoker invoker = new Invoker();

        Command startCommand = new StartCommand(computer);
        Command shutdownCommand = new ShutdownCommand(computer);
        Command restartCommand = new RestartCommand(computer);
        Command checkDiskCommand = new CheckDiskCommand(computer);
        Command checkMemoryCommand = new CheckMemoryCommand(computer);
        Command checkNetworkCommand = new CheckNetworkCommand(computer);

        invoker.addCommand(startCommand);
        invoker.addCommand(checkDiskCommand);
        invoker.addCommand(checkMemoryCommand);
        invoker.addCommand(restartCommand);
        invoker.addCommand(checkNetworkCommand);
        invoker.addCommand(shutdownCommand);

        invoker.executeCommand();
        invoker.executeCommand();
        invoker.executeCommand();
        invoker.executeCommand();
        invoker.executeCommand();
        invoker.executeCommand();

        invoker.undoCommand();
    }
}