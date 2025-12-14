package org.example.Java._2_Collections.Collections.Queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Демонстрационный класс, показывающий базовую работу с интерфейсом Queue
 * на примере реализации LinkedList (обычная FIFO‑очередь).[web:135]
 *
 * Queue — это интерфейс для очередей: структура данных «первым пришёл — первым ушёл» (FIFO: First In, First Out).[web:132]
 * В отличие от стека (LIFO), где работаем только с вершиной, очередь работает с «хвостом» (куда добавляем)
 * и «головой» (откуда забираем).[web:138]
 *
 * Основные методы Queue:[web:132]
 * - offer(E e) — добавить элемент в очередь, вернуть true/false (не кидает исключение при отказе).[web:126]
 * - poll() — забрать и удалить элемент с головы очереди, вернуть null, если очередь пуста (без исключения).[web:136]
 * - peek() — посмотреть элемент на голове, не удаляя его, вернуть null, если очередь пуста.[web:132]
 *
 * LinkedList реализует интерфейс Queue, поэтому его можно использовать как обычную FIFO‑очередь.[web:135]
 */
public class LinkedListQueueExample {

    public static void main(String[] args) {
        // Работаем через интерфейс Queue, указывая реализацию LinkedList.[web:135]
        // Такой стиль позволяет легко заменить реализацию (на PriorityQueue, ArrayDeque и т.п.).[web:134]
        Queue<String> queue = new LinkedList<>();

        // Добавляем элементы в очередь с помощью offer.[web:135]
        // Элементы будут обрабатываться в том же порядке, в котором были добавлены.[web:138]
        queue.offer("Task 1");
        queue.offer("Task 2");
        queue.offer("Task 3");

        System.out.println("Начальное состояние очереди: " + queue);

        // peek() возвращает элемент с головы очереди, НЕ удаляя его.[web:132]
        // Если очередь пуста, вернёт null, а не бросит исключение.[web:123]
        String head = queue.peek();
        System.out.println("Элемент на голове очереди (peek): " + head);
        System.out.println("Очередь после peek (не изменилась): " + queue);

        // poll() возвращает и УДАЛЯЕТ элемент с головы очереди.[web:136]
        // Если очередь пуста, вернёт null (в отличие от remove(), который бросает исключение).[web:132]
        String first = queue.poll();
        System.out.println("Извлечён (poll) первый элемент: " + first);
        System.out.println("Очередь после первого poll: " + queue);

        // Извлекаем следующий элемент.[web:136]
        String second = queue.poll();
        System.out.println("Извлечён (poll) второй элемент: " + second);
        System.out.println("Очередь после второго poll: " + queue);

        // Можно пройти по всем оставшимся элементам очереди в цикле for-each.[web:135]
        System.out.println("Оставшиеся элементы в очереди (итерация):");
        for (String task : queue) {
            System.out.println(task);
        }

        // Опустошим очередь полностью.[web:136]
        queue.poll(); // забираем последний элемент
        System.out.println("Очередь после извлечения всех элементов: " + queue);

        // Теперь очередь пуста: peek() и poll() вернут null.[web:136]
        System.out.println("peek() на пустой очереди: " + queue.peek());
        System.out.println("poll() на пустой очереди: " + queue.poll());

        // Для сравнения: метод remove() в такой ситуации бросил бы NoSuchElementException,
        // поэтому в большинстве случаев в прикладном коде предпочитают poll()/peek() с проверкой на null.[web:123]
    }
}
