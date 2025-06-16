package org.example.Java._2_Collections.Methods;

import java.util.Objects;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

public class hashCodeExample {
    public static void main(String[] args) {
        Person person = new Person("Vitya", 34);
        System.out.println(person.hashCode());
    }
}
