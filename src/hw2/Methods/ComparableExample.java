package hw2.Methods;

import java.util.*;

class Human implements Comparable<Human> {
    private String name;
    private int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int compareTo(Human other) {
        return other.name.compareTo(this.name);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class ComparableExample{
    public static void main(String[] args) {
        Set<Human> people = new TreeSet<>();

        people.add(new Human("Bob", 30));
        people.add(new Human("Charlie", 20));
        people.add(new Human("Alice", 25));
        for (Human human : people) {
            System.out.println(human.getName() + " " + human.getAge());
        }
    }
}