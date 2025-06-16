package org.example.Java._2_Collections.Collections.List;

import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> myList = new LinkedList<>();

        myList.add("apple");
        myList.add("banana");
        myList.add("cherry");

        for (String fruit : myList) {
            System.out.println(fruit);
        }

        myList.addFirst("orange");

        System.out.println("After adding element at the beginning:");
        for (String fruit : myList) {
            System.out.println(fruit);
        }

        myList.removeFirst();

        System.out.println("After removing first element:");
        for (String fruit : myList) {
            System.out.println(fruit);
        }
    }
}
