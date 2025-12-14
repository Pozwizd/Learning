package org.example.Java._2_Collections.Collections.Queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * Демонстрационный класс, показывающий работу с ArrayDeque как с обычной очередью (Queue)
 * и как с двусторонней очередью/стеком (Deque).[web:145][web:149]
 *
 * ArrayDeque — это реализация интерфейса Deque (двусторонняя очередь) на основе динамического массива.[web:140][web:149]
 * Особенности ArrayDeque:[web:148][web:156]
 * - можно добавлять и удалять элементы с обеих сторон (как очередь и как стек);[web:140][web:143]
 * - нет ограничений по вместимости: при необходимости внутренний массив расширяется;[web:156]
 * - в отличие от LinkedList, не хранит лишних ссылок next/prev, поэтому обычно быстрее и более эффективен по памяти
 *   для операций очереди/стека;[web:147][web:153][web:155]
 * - не допускает null‑элементы (попытка добавить null приведёт к NullPointerException).[web:147][web:142]
 *
 * В современном коде часто рекомендуют использовать ArrayDeque:
 * - вместо Stack — для реализации стека;[web:141][web:157][web:158]
 * - вместо LinkedList — для реализации очереди.[web:147][web:153]
 */
public class ArrayDequeExample {

    public static void main(String[] args) {
        // ===== ЧАСТЬ 1. ArrayDeque как обычная очередь (FIFO) через интерфейс Queue =====[web:145][web:146]

        // Работаем через Queue<String>, но используем реализацию ArrayDeque.[web:145][web:151]
        Queue<String> queue = new ArrayDeque<>();

        // Добавляем элементы в очередь (в конец).[web:140][web:145]
        queue.offer("Task 1");
        queue.offer("Task 2");
        queue.offer("Task 3");

        System.out.println("Очередь (ArrayDeque как Queue): " + queue);

        // peek() — посмотреть элемент на голове очереди, НЕ удаляя его.[web:140][web:145]
        String head = queue.peek();
        System.out.println("Голова очереди (peek): " + head);
        System.out.println("Очередь после peek (не изменилась): " + queue);

        // poll() — забрать и удалить элемент с головы очереди.[web:140][web:145]
        String first = queue.poll();
        System.out.println("Извлечён (poll) первый элемент: " + first);
        System.out.println("Очередь после первого poll: " + queue);

        String second = queue.poll();
        System.out.println("Извлечён (poll) второй элемент: " + second);
        System.out.println("Очередь после второго poll: " + queue);

        // Забираем оставшийся элемент, очередь становится пустой.[web:140]
        queue.poll();
        System.out.println("Очередь после извлечения всех элементов: " + queue);
        System.out.println("peek() на пустой очереди: " + queue.peek()); // вернёт null[web:140][web:145]

        // ===== ЧАСТЬ 2. ArrayDeque как Deque/стек (LIFO) через интерфейс Deque =====[web:145][web:151]

        // Теперь используем ArrayDeque через интерфейс Deque.[web:146][web:154]
        Deque<String> deque = new ArrayDeque<>();

        // Как очередь: добавляем элементы в «хвост» (offerLast/addLast).[web:140][web:149]
        deque.offerLast("A");
        deque.offerLast("B");
        deque.offerLast("C");
        System.out.println("Deque после offerLast (как очередь): " + deque);

        // Как стек: push кладёт элемент «на вершину», что эквивалентно добавлению в начало.[web:140][web:145]
        deque.push("TOP");
        System.out.println("Deque после push(\"TOP\") (как стек): " + deque);

        // peekFirst/peekLast — посмотреть первый и последний элементы без удаления.[web:140][web:145]
        String firstElement = deque.peekFirst();
        String lastElement = deque.peekLast();
        System.out.println("Первый элемент (peekFirst): " + firstElement);
        System.out.println("Последний элемент (peekLast): " + lastElement);

        // pop — снять элемент с вершины стека (эквивалент removeFirst в стековом режиме).[web:140][web:145]
        String popped = deque.pop();
        System.out.println("Снято со стека (pop): " + popped);
        System.out.println("Deque после pop: " + deque);

        // Можно работать как с двусторонней очередью:
        // добавлять элементы слева и справа, а также извлекать с обеих сторон.[web:140][web:149]
        deque.offerFirst("LEFT");
        deque.offerLast("RIGHT");
        System.out.println("Deque после offerFirst/offerLast: " + deque);

        String fromLeft = deque.pollFirst();  // забрать слева[web:140]
        String fromRight = deque.pollLast();  // забрать справа[web:140]
        System.out.println("pollFirst(): " + fromLeft);
        System.out.println("pollLast(): " + fromRight);
        System.out.println("Deque в конце примера: " + deque);

        // Резюме:
        // - ArrayDeque универсален: одна структура может работать и как очередь (FIFO), и как стек (LIFO);[web:145][web:151]
        // - чаще всего именно его рекомендуют использовать для очередей/стеков вместо LinkedList и Stack.[web:147][web:153][web:157]]
    }
}
