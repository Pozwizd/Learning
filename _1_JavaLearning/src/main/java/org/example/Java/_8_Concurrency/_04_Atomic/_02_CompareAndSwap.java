package org.example.Java._8_Concurrency._04_Atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Демонстрация Compare-And-Swap (CAS) операций
 */
public class _02_CompareAndSwap {
    
    public static void main(String[] args) {
        System.out.println("=== AtomicInteger CAS ===");
        AtomicInteger counter = new AtomicInteger(5);
        
        // Если значение == 5, установить 10
        boolean success = counter.compareAndSet(5, 10);
        System.out.println("CAS (5 -> 10): " + success); // true
        System.out.println("Текущее значение: " + counter.get()); // 10
        
        // Попытка изменить с неверным ожидаемым значением
        success = counter.compareAndSet(5, 20);
        System.out.println("CAS (5 -> 20): " + success); // false
        System.out.println("Текущее значение: " + counter.get()); // 10
        
        System.out.println("\n=== AtomicReference CAS ===");
        AtomicReference<String> ref = new AtomicReference<>("initial");
        
        success = ref.compareAndSet("initial", "updated");
        System.out.println("CAS (initial -> updated): " + success); // true
        System.out.println("Текущее значение: " + ref.get()); // updated
        
        // Lock-free stack пример
        System.out.println("\n=== Lock-Free Stack ===");
        LockFreeStack<Integer> stack = new LockFreeStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        
        System.out.println("Pop: " + stack.pop()); // 3
        System.out.println("Pop: " + stack.pop()); // 2
        System.out.println("Pop: " + stack.pop()); // 1
    }
    
    static class LockFreeStack<T> {
        private final AtomicReference<Node<T>> head = new AtomicReference<>();
        
        static class Node<T> {
            final T value;
            Node<T> next;
            
            Node(T value) {
                this.value = value;
            }
        }
        
        public void push(T value) {
            Node<T> newHead = new Node<>(value);
            Node<T> oldHead;
            do {
                oldHead = head.get();
                newHead.next = oldHead;
            } while (!head.compareAndSet(oldHead, newHead));
        }
        
        public T pop() {
            Node<T> oldHead;
            Node<T> newHead;
            do {
                oldHead = head.get();
                if (oldHead == null) {
                    return null;
                }
                newHead = oldHead.next;
            } while (!head.compareAndSet(oldHead, newHead));
            return oldHead.value;
        }
    }
}
