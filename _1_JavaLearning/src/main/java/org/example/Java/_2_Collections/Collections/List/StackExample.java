package org.example.Java._2_Collections.Collections.List;

import java.util.Stack;

/**
 * Демонстрационный класс, показывающий базовую работу с классом Stack в Java.[web:73]
 *
 * Stack — это реализация структуры данных «стек» (LIFO: Last In, First Out — «последним пришёл, первым ушёл»).[web:74]
 * Исторически класс Stack наследуется от Vector, поэтому:[web:73]
 * - все его основные методы синхронизированы (thread-safe, но медленнее в однопоточном коде);[web:81]
 * - он считается устаревшим (legacy) решением, и в новом коде обычно рекомендуют использовать Deque (например, ArrayDeque) вместо Stack.[web:77]
 *
 * Основные операции стека:[web:88]
 * - push(E item) — положить элемент на вершину стека;[web:73]
 * - pop() — снять и вернуть элемент с вершины стека;[web:73]
 * - peek() — вернуть элемент с вершины стека, не снимая его;[web:73]
 * - empty() — проверить, пуст ли стек.[web:73]
 *
 * В этом примере мы используем Stack<String>, то есть стек строк, и демонстрируем базовое LIFO-поведение.[web:76]
 */
public class StackExample {

    /**
     * Точка входа в программу.[web:76]
     *
     * Демонстрируются операции:[web:76]
     * - создание стека;[web:76]
     * - добавление элементов (push);[web:73]
     * - чтение вершины (peek);[web:88]
     * - снятие элементов с вершины (pop);[web:73]
     * - проверка пустоты стека (empty).[web:73]
     */
    public static void main(String[] args) {
        // Создаём стек строк.[web:76]
        // Тип в угловых скобках <String> означает, что стек может хранить только объекты типа String.[web:76]
        Stack<String> stack = new Stack<>();

        // Добавляем элементы на вершину стека с помощью push.[web:73]
        // Каждый новый элемент кладётся поверх предыдущего.[web:74]
        stack.push("first");
        stack.push("second");
        stack.push("third");

        System.out.println("Initial stack: " + stack); // Печать содержимого стека целиком.[web:76]

        // Метод peek возвращает элемент на вершине стека, не удаляя его.[web:73]
        // Это позволяет «подсмотреть», что лежит сверху, не изменяя стек.[web:88]
        String topElement = stack.peek();
        System.out.println("Top element (peek): " + topElement);

        // Метод pop снимает элемент с вершины стека и возвращает его.[web:73]
        // Поскольку стек работает по принципу LIFO, первым снимется "third".[web:74]
        String popped1 = stack.pop();
        System.out.println("Popped element: " + popped1);
        System.out.println("Stack after first pop: " + stack);

        // Снимем ещё один элемент.[web:73]
        String popped2 = stack.pop();
        System.out.println("Popped element: " + popped2);
        System.out.println("Stack after second pop: " + stack);

        // Проверяем, пуст ли стек с помощью empty().[web:73]
        // Вернёт true, если в стеке нет элементов.[web:73]
        boolean isEmptyBefore = stack.empty();
        System.out.println("Is stack empty (before last pop)? " + isEmptyBefore);

        // Снимаем оставшийся элемент.[web:73]
        stack.pop();
        System.out.println("Stack after popping all elements: " + stack);

        // Теперь стек пуст, empty() вернёт true.[web:73]
        boolean isEmptyAfter = stack.empty();
        System.out.println("Is stack empty (after last pop)? " + isEmptyAfter);

        // Важно: для нового кода обычно рекомендуют использовать Deque (например, ArrayDeque) вместо Stack,
        // потому что Stack медленнее из‑за лишней синхронизации и считается устаревшим дизайном.[web:77]
        // Однако для обучения базовой идее стека и работы с LIFO этот класс всё ещё удобен и нагляден.[web:78]
    }
}
