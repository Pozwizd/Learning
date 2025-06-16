package org.example.Java._2_Collections.Methods;

import java.util.ArrayList;
import java.util.List;

public class WildCardExample<T> {
    private final List<? super T> contents = new ArrayList<>();

    public static void main(String[] args) {
        WildCardExample<Integer> intBox = new WildCardExample<>();
        intBox.add(123);

        WildCardExample<Number> numberBox = new WildCardExample<>();
        numberBox.add(3.14);

        WildCardExample<Object> objectBox = new WildCardExample<>();
        objectBox.add("Hello");
    }

    public void add(T element) {
        contents.add(element);
    }
}