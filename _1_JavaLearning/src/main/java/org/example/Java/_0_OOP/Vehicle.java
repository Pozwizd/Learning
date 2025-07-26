package org.example.Java._0_OOP;

/**
 * АБСТРАКЦИЯ: Это абстрактный класс, который нельзя инстанцировать напрямую.
 * Он определяет шаблон того, чем является Транспортное средство и что оно может делать.
 */
public abstract class Vehicle {

    /**
     * ИНКАПСУЛЯЦИЯ: Это поле является приватным и доступ к нему можно получить
     * только через его публичный геттер, скрывая внутреннее состояние.
     */
    private final String model;

    /**
     * ИНКАПСУЛЯЦИЯ И КОМПОЗИЦИЯ: Транспортное средство "имеет" Двигатель (отношение "has-a").
     * Объект Двигателя инкапсулирован внутри Транспортного средства.
     */
    private final Engine engine;

    /**
     * МОДИФИКАТОР PROTECTED: Это поле доступно только внутри этого пакета
     * и подклассам (таким как Car и Motorcycle).
     */
    protected int speed;

    public Vehicle(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
        this.speed = 0;
    }

    // --- Абстрактный метод ---

    /**
     * АБСТРАКЦИЯ И ПОЛИМОРФИЗМ: Это абстрактный метод.
     * Подклассы ДОЛЖНЫ предоставить свою собственную реализацию, что позволяет
     * использовать разное поведение для одного и того же действия (полиморфизм).
     */
    public abstract void start();


    // --- Конкретные методы ---

    public void stop() {
        if (engine.isRunning()) {
            engine.stop();
            this.speed = 0;
            System.out.println(model + " остановился.");
        }
    }

    // --- Демонстрация модификаторов доступа ---

    /**
     * МОДИФИКАТОР PACKAGE-PRIVATE (ПО УМОЛЧАНИЮ): Этот метод может быть вызван
     * только классами из того же пакета (org.example.Java._0_OOP).
     * К нему нельзя получить доступ из подклассов в других пакетах или откуда-либо еще.
     */
    void performService() {
        System.out.println("Выполняется обслуживание " + getModel() + ". Проверка типа двигателя: " + engine.getType());
    }


    // --- Публичные геттеры (Инкапсуляция) ---

    public String getModel() {
        return model;
    }

    public int getSpeed() {
        return speed;
    }
    
    public Engine getEngine() {
        return engine;
    }
}