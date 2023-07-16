package hw2.Collections.Set;

import java.util.LinkedHashSet;

public class LinkedHashSetExample {
    public static void main(String[] args) {

        LinkedHashSet<String> mySet = new LinkedHashSet<>();

        mySet.add("apple");
        mySet.add("banana");
        mySet.add("cherry");
        mySet.add("banana");
        mySet.add("banana");

        System.out.println("LinkedHashSet содержит следующие элементы: " + mySet);

        mySet.remove("cherry");

        if (mySet.contains("banana")) {
            System.out.println("Множество содержит элемент banana");
        }

        System.out.println("LinkedHashSet содержит следующие элементы после удаления: " + mySet);
    }
}