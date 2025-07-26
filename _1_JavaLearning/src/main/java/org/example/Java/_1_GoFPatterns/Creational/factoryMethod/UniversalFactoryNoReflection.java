package org.example.Java._1_GoFPatterns.Creational.factoryMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// Универсальная фабрика без рефлексии
public class UniversalFactoryNoReflection {

    private static final Map<Class<?>, Supplier<?>> factories = new HashMap<>();

    // Регистрация фабрики для класса
    public static <T> void register(Class<T> clazz, Supplier<T> supplier) {
        factories.put(clazz, supplier);
    }



    // Создание экземпляра с @SuppressWarnings для конкретной строки
    @SuppressWarnings("unchecked") // Подавляем предупреждение только здесь
    public static <T> T createInstance(Class<T> clazz) {
        Supplier<?> supplierRaw = factories.get(clazz);
        if (supplierRaw == null) {
            throw new RuntimeException("Фабрика для " + clazz.getName() + " не зарегистрирована");
        }
        // Приведение с подавлением предупреждения, так как мы уверены в типе
        Supplier<T> supplier = (Supplier<T>) supplierRaw;
        return supplier.get();
    }

    // Пример использования
    public static void main(String[] args) {
        // Регистрируем фабрику для класса
        register(MyClass.class, MyClass::new);

        // Создаём экземпляр
        MyClass instance = createInstance(MyClass.class);
        instance.sayHello();
    }

    // Пример класса
    static class MyClass {
        public MyClass() {
            System.out.println("MyClass создан без рефлексии!");
        }

        public void sayHello() {
            System.out.println("Привет!");
        }
    }
}
