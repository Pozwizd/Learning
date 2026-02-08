package hw2.Methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Individual {
    private final String name;
    private final int age;

    public Individual(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        List<Individual> people = new ArrayList<>();
        people.add(new Individual("Alice", 25));
        people.add(new Individual("Bob", 30));
        people.add(new Individual("Charlie", 20));

        // Сортировка по возрасту с помощью Comparator
        Collections.sort(people, new Comparator<Individual>() {
            public int compare(Individual p1, Individual p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });

        // Вывод отсортированных объектов
        for (Individual person : people) {
            System.out.println(person.getName() + " " + person.getAge());
        }
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
