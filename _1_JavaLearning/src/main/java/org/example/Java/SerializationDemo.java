package org.example.Java;

import java.io.*;

public class SerializationDemo {

    // Класс без имплементации Serializable
    static class PersonWithoutSerializable {
        private String name;
        private int age;

        public PersonWithoutSerializable(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    // Класс с имплементацией Serializable
    static class PersonWithSerializable implements Serializable {
        private static final long serialVersionUID = 1L; // Рекомендуется для версионного контроля
        private String name;
        private int age;

        public PersonWithSerializable(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    public static void main(String[] args) {
        // Демонстрация БЕЗ Serializable
        System.out.println("Попытка сериализации БЕЗ имплементации Serializable:");
        PersonWithoutSerializable personWithout = new PersonWithoutSerializable("Alice", 30);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("personWithout.ser"))) {
            oos.writeObject(personWithout);
            System.out.println("Сериализация удалась (но это не сработает)");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Демонстрация С Serializable
        System.out.println("\nПопытка сериализации С имплементацией Serializable:");
        PersonWithSerializable personWith = new PersonWithSerializable("Bob", 25);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("personWith.ser"))) {
            oos.writeObject(personWith);
            System.out.println("Сериализация удалась!");
        } catch (IOException e) {
            System.out.println("Ошибка сериализации: " + e.getMessage());
            return; // Выходим, если сериализация сломалась
        }

        // Десериализация и вывод
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("personWith.ser"))) {
            PersonWithSerializable deserializedPerson = (PersonWithSerializable) ois.readObject();
            System.out.println("Десериализованный объект: " + deserializedPerson);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка десериализации: " + e.getMessage());
        }
    }
}