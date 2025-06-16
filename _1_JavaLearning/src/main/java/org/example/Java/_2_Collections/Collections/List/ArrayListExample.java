package org.example.Java._2_Collections.Collections.List;

import java.util.ArrayList;

class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> myArrayList = new ArrayList<String>();

        myArrayList.add("Apple");
        myArrayList.add("Banana");
        myArrayList.add("Cherry");
        myArrayList.add("Date");

        System.out.println("My ArrayList:");
        for (String fruit : myArrayList) {
            System.out.println(fruit);
        }

        myArrayList.remove("Cherry");

        if (myArrayList.contains("Banana")) {
            System.out.println("My ArrayList contains Banana");
        }

        int size = myArrayList.size();
        System.out.println("My ArrayList size is: " + size);

        myArrayList.clear();

        if (myArrayList.isEmpty()) {
            System.out.println("My ArrayList is empty");
        }
    }
}
