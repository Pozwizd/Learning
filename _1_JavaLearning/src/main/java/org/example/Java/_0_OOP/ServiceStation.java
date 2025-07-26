package org.example.Java._0_OOP;

/**
 * ДОСТУП PACKAGE-PRIVATE (ПО УМОЛЧАНИЮ): У этого класса нет модификатора public,
 * что означает, что к нему могут обращаться только другие классы в том же пакете.
 * Это полезно для вспомогательных или утилитных классов, которые не должны быть
 * доступны остальной части приложения.
 */
class ServiceStation {

    /**
     * Этот метод вызывает package-private метод 'performService' у объекта Vehicle.
     * Это возможно только потому, что ServiceStation находится в том же пакете, что и Vehicle.
     */
    public void serviceVehicle(Vehicle vehicle) {
        System.out.println("\n--- Добро пожаловать на Станцию Техобслуживания ---");
        System.out.println("Обслуживается транспортное средство: " + vehicle.getModel());
        // Вызов package-private метода
        vehicle.performService();
        System.out.println("Обслуживание завершено для: " + vehicle.getModel());
        System.out.println("------------------------------------");
    }
}