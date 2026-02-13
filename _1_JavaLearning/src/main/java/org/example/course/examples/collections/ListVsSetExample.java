package org.example.course.examples.collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListVsSetExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("java", "java", "sql"));
        Set<String> set = new HashSet<>(list);

        System.out.println("List хранит дубликаты: " + list);
        System.out.println("Set хранит только уникальные: " + set);
    }
}
