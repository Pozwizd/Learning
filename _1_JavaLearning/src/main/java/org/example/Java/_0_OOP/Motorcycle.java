package org.example.Java._0_OOP;

/**
 * НАСЛЕДОВАНИЕ: Класс Motorcycle также расширяет Vehicle.
 */
public class Motorcycle extends Vehicle {

    private final boolean hasSidecar;

    public Motorcycle(String model, Engine engine, boolean hasSidecar) {
        super(model, engine);
        this.hasSidecar = hasSidecar;
    }

    /**
     * ПОЛИМОРФИЗМ: Это специфическая для Motorcycle реализация метода 'start'.
     * Она отличается поведением от метода 'start' в классе Car.
     */
    @Override
    public void start() {
        getEngine().start();
        System.out.println("Мотоцикл " + getModel() + " набирает обороты!");
    }

    /**
     * Этот метод также получает доступ к защищенному (protected) полю 'speed' из Vehicle.
     */
    public void wheelie() {
        if (getEngine().isRunning() && speed > 50) {
            System.out.println(getModel() + " едет на заднем колесе! Как круто!");
        } else {
            System.out.println("Нужно двигаться быстрее, чтобы поехать на заднем колесе, или двигатель выключен.");
        }
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }
}