package org.example.Java._2_Collections.TaskBonus;

public interface MyList<T> {
    int size();

    boolean isEmpty();

    boolean contains(T object);

    void add(T object);

    void add(int index, T object);

    boolean remove(T object);

    void clear();

    T get(int index);

    int indexOf(T object);

    int lastIndexOf(T object);
}
