package org.example.Java._2_Collections.TaskBonus;

import java.util.Iterator;



public class MyArrayList<T> implements MyList<T>, Iterable<T> {
    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[10];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(T object) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = object;
    }

    @Override
    public void add(int index, T object) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (index > size) {
            index = size;
        }
        if (size == elements.length) {
            resize();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = object;
        size++;
    }

    @Override
    public boolean remove(T object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        elements = new Object[10];
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        T element = (T) elements[index];
        return element;
    }

    @Override
    public int indexOf(T object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T object) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }
    
    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = 0;
        
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more elements");
            }
            return get(currentIndex++);
        }
    }
}