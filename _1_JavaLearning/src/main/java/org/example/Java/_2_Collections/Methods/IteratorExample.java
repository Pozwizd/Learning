package org.example.Java._2_Collections.Methods;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

class Cheloveki implements Iterable<Cheloveki> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Iterator<Cheloveki> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Cheloveki> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Cheloveki> spliterator() {
        return Iterable.super.spliterator();
    }
}

public class IteratorExample {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            System.out.println(element);
        }
    }
}