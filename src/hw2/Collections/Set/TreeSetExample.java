package hw2.Collections.Set;

import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {
        TreeSet<Integer> mySet = new TreeSet<>();

        mySet.add(10);
        mySet.add(2);
        mySet.add(8);
        mySet.add(5);

        System.out.println("TreeSet содержит следующие элементы: " + mySet);


        mySet.remove(8);

        if (mySet.contains(2)) {
            System.out.println("Множество содержит элемент 2");
        }

        System.out.println("TreeSet содержит следующие элементы после удаления: " + mySet);
    }
}