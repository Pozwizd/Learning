package org.example.Java._0_OOP;

/**
 * НАСЛЕДОВАНИЕ: Класс Car расширяет класс Vehicle.
 * Он наследует публичные и защищенные (protected) члены из Vehicle.
 */
public class Car extends Vehicle {

    /**
     * ИНКАПСУЛЯЦИЯ: Это поле специфично для класса Car и является приватным.
     */
    private final int numberOfDoors;

    public Car(String model, Engine engine, int numberOfDoors) {
        // Вызываем конструктор родительского класса (Vehicle)
        super(model, engine);
        this.numberOfDoors = numberOfDoors;
    }

    /**
     * ПОЛИМОРФИЗМ: Это специфическая для Car реализация абстрактного метода 'start'.
     * Она переопределяет абстрактный метод родителя.
     */
    @Override
    public void start() {
        // Сначала запускаем двигатель, используя композитный объект Engine
        getEngine().start();
        // Затем выполняем специфичное для автомобиля действие
        System.out.println("Автомобиль " + getModel() + " готов к поездке. Системы безопасности проверены.");
    }

    /**
     * Этот метод демонстрирует доступ к 'protected' члену из родительского класса.
     */
    public void accelerate(int amount) {
        if (getEngine().isRunning()) {
            // 'speed' - это защищенное (protected) поле из класса Vehicle
            this.speed += amount;
            System.out.println(getModel() + " ускоряется до " + this.speed + " км/ч.");
        } else {
            System.out.println("Невозможно ускориться, двигатель не запущен.");
        }
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }
}