package hw2.TaskBonus;

import java.util.Iterator;


public class MyQueue<T> implements Iterable<T> {

    private final MyLinkedList<T> elements;

    public MyQueue() {
        elements = new MyLinkedList<>();
    }

    public void enqueue(T item) {
        elements.add(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty queue");
        }
        T item = elements.peek();
        elements.poll();
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty queue");
        }
        return elements.peek();
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
