package org.example.Java._1_GoFPatterns.Creational.factoryMethod;

// Универсальная фабрика
public class UniversalFactory {

    // Метод для создания экземпляра по классу
    public static <T> T createInstance(Class<T> clazz) {
        try {
            // Используем рефлексию для создания экземпляра
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // Обработка ошибок: если нет конструктора или другие проблемы
            throw new RuntimeException("Не удалось создать экземпляр класса: " + clazz.getName(), e);
        }
    }

    // Пример использования
    public static void main(String[] args) {
        // Пример класса для теста
        class MyClass {
            public MyClass() {
                System.out.println("MyClass создан!");
            }

            public void doSomething() {
                System.out.println("Делаем что-то...");
            }
        }

        // Создаем экземпляр через фабрику
        MyClass instance = UniversalFactory.createInstance(MyClass.class);
        instance.doSomething();
    }
}
