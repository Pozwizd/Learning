package hw2.TaskBonus;

import java.util.Iterator;


public class MyStack<T> implements Iterable<T> {
    private final MyArrayList<T> elements;

    public MyStack() {
        elements = new MyArrayList<>();
    }

    public void push(T item) {
        elements.add(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек порожній");
        }
        T item = elements.get(elements.size() - 1);
        elements.remove(item);
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек порожній");
        }
        return elements.get(elements.size() - 1);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public int size() {
        return elements.size();
    }

    public void clear() {
        elements.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }
}
