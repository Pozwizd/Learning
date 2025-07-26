package org.example.Java._0_OOP;

/**
 * Простой класс, представляющий Двигатель.
 * Демонстрирует отношение "has-a" (Композиция) при использовании в классе Vehicle.
 */
public class Engine {
    private final String type;
    private boolean isRunning;

    public Engine(String type) {
        this.type = type;
        this.isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            this.isRunning = true;
            System.out.println("Двигатель (" + type + ") запущен.");
        }
    }

    public void stop() {
        if (isRunning) {
            this.isRunning = false;
            System.out.println("Двигатель (" + type + ") остановлен.");
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getType() {
        return type;
    }
}