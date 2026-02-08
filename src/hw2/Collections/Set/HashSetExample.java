package hw2.Collections.Set;

import java.util.HashSet;

public class HashSetExample {
    public static void main(String[] args) {
        HashSet<String> mySet = new HashSet<>();

        mySet.add("apple");
        mySet.add("banana");
        mySet.add("cherry");
        mySet.add("banana");

        System.out.println("HashSet содержит следующие элементы: " + mySet);


        mySet.remove("cherry");


        if (mySet.contains("banana")) {
            System.out.println("Множество содержит элемент banana");
        }

        System.out.println("HashSet содержит следующие элементы после удаления: " + mySet);
    }
}