package org.example.Java._2_Collections.TaskBonus;

import java.util.Iterator;



public class MyLinkedList<T> implements MyList<T>, Queue<T>, Iterable<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T object) {
        return indexOf(object) != -1;
    }

    @Override
    public void add(T object) {
        addLast(object);
    }

    @Override
    public void add(int index, T object) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            addLast(object);
        } else if (index == 0) {
            addFirst(object);
        } else {
            Node<T> node = getNode(index - 1);
            Node<T> newNode = new Node<>(object, node, node.next);
            node.next = newNode;
            newNode.next.prev = newNode;
            size++;
        }
    }

    @Override
    public boolean remove(T object) {
        Node<T> node = first;
        while (node != null) {
            if (node.data.equals(object)) {
                removeNode(node);
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getNode(index).data;
    }

    @Override
    public int indexOf(T object) {
        Node<T> node = first;
        int index = 0;
        while (node != null) {
            if (node.data.equals(object)) {
                return index;
            }
            node = node.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T object) {
        Node<T> node = last;
        int index = size - 1;
        while (node != null) {
            if (node.data.equals(object)) {
                return index;
            }
            node = node.prev;
            index--;
        }
        return -1;
    }

    @Override
    public T offer() {
        if (isEmpty()) {
            return null;
        }
        return first.data;
    }

    @Override
    public void poll() {
        if (isEmpty()) {
            return;
        }
        removeFirst();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return first.data;
    }

    private void addFirst(T object) {
        Node<T> newNode = new Node<>(object, null, first);
        if (first == null) {
            last = newNode;
        } else {
            first.prev = newNode;
        }
        first = newNode;
        size++;
    }

    private void addLast(T object) {
        Node<T> newNode = new Node<>(object, last, null);
        if (last == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    private void removeFirst() {
        if (isEmpty()) {
            return;
        }
        if (first == last) {
            first = last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
    }

    private void removeLast() {
        if (isEmpty()) {
            return;
        }
        if (first == last) {
            first = last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
    }

    private void removeNode(Node<T> node) {
        if (node == first) {
            removeFirst();
        } else if (node == last) {
            removeLast();
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node;
        if (index < size / 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException("No more elements");
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}