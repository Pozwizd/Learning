package org.example.Java._0_OOP;

public class Main {
    public static void main(String[] args) {
        // --- Создание объектов ---
        // Создаем экземпляры двигателей (для композиции)
        Engine carEngine = new Engine("V8");
        Engine motorcycleEngine = new Engine("V-Twin");

        // Создаем конкретные экземпляры транспортных средств
        Car myCar = new Car("Ford Mustang", carEngine, 2);
        Motorcycle myMotorcycle = new Motorcycle("Harley-Davidson", motorcycleEngine, false);

        System.out.println("==================================================");
        System.out.println("ДЕМОНСТРАЦИЯ ПОЛИМОРФИЗМА");
        System.out.println("==================================================");

        // ПОЛИМОРФИЗМ: Создаем массив ссылок типа Vehicle.
        // Мы можем хранить в этом массиве любой подкласс Vehicle (Car, Motorcycle).
        Vehicle[] myVehicles = {myCar, myMotorcycle};

        // Проходим в цикле по транспортным средствам и вызываем один и тот же метод 'start()'.
        // Фактический метод, который будет выполнен, зависит от истинного типа объекта.
        for (Vehicle v : myVehicles) {
            System.out.println("\n--- Запускаем " + v.getClass().getSimpleName() + " ---");
            v.start(); // для myCar вызывается Car.start(), для myMotorcycle - Motorcycle.start()
            v.stop();
        }

        System.out.println("\n==================================================");
        System.out.println("ДЕМОНСТРАЦИЯ НАСЛЕДОВАНИЯ И ИНКАПСУЛЯЦИИ");
        System.out.println("==================================================");

        // Запускаем машину, чтобы использовать ее специфические методы
        myCar.start();
        // Доступ к методу, специфичному для Car
        myCar.accelerate(80);
        // Доступ к методу, унаследованному от Vehicle
        System.out.println("Текущая скорость автомобиля: " + myCar.getSpeed()); // Доступ к protected 'speed' через публичный геттер
        // Доступ к инкапсулированному полю через геттер
        System.out.println("В моей машине " + myCar.getNumberOfDoors() + " двери.");
        myCar.stop();

        System.out.println("---");

        myMotorcycle.start();
        // Доступ к методу, специфичному для Motorcycle
        myMotorcycle.wheelie(); // Не сработает, потому что скорость равна 0
        // Доступ к защищенному (protected) полю 'speed' возможен внутри метода подкласса,
        // но здесь мы демонстрируем изменение его состояния через публичный метод.
        System.out.println("Текущая скорость мотоцикла: " + myMotorcycle.getSpeed());
        System.out.println("Есть ли у него коляска? " + myMotorcycle.hasSidecar());


        System.out.println("\n==================================================");
        System.out.println("ДЕМОНСТРАЦИЯ ДОСТУПА PACKAGE-PRIVATE");
        System.out.println("==================================================");

        // Создаем экземпляр package-private класса
        ServiceStation station = new ServiceStation();

        // Метод serviceVehicle может вызывать package-private метод 'performService' у автомобиля
        station.serviceVehicle(myCar);
    }
}