package org.example.Java._2_Collections.TaskBonus;

public class CollectionsTest {
    public static void main(String[] args) {
        testArrayList();
        testLinkedList();
        testStack();
        testQueue();
    }

    private static void testArrayList() {
        System.out.println("\n===== Тестування ArrayList =====\n");
        MyArrayList<String> list = new MyArrayList<>();
        
        // Тестування додавання елементів
        System.out.println("Додавання елементів:");
        list.add("Перший");
        list.add("Другий");
        list.add("Третій");
        list.add(1, "Вставлений");
        printCollection(list);
        
        // Тестування видалення
        System.out.println("\nВидалення елемента 'Вставлений':");
        list.remove("Вставлений");
        printCollection(list);
        
        // Тестування пошуку
        System.out.println("\nПошук елементів:");
        System.out.println("Індекс 'Перший': " + list.indexOf("Перший"));
        System.out.println("Індекс 'Третій': " + list.indexOf("Третій"));
        System.out.println("Містить 'Другий': " + list.contains("Другий"));
        System.out.println("Містить 'Відсутній': " + list.contains("Відсутній"));
        
        // Тестування очищення
        System.out.println("\nОчищення колекції:");
        list.clear();
        System.out.println("Розмір після очищення: " + list.size());
        System.out.println("Порожня: " + list.isEmpty());
    }
    
    private static void testLinkedList() {
        System.out.println("\n===== Тестування LinkedList =====\n");
        MyLinkedList<String> list = new MyLinkedList<>();
        
        // Тестування додавання елементів
        System.out.println("Додавання елементів:");
        list.add("Перший");
        list.add("Другий");
        list.add("Третій");
        list.add(1, "Вставлений");
        printCollection(list);
        
        // Тестування видалення
        System.out.println("\nВидалення елемента 'Вставлений':");
        list.remove("Вставлений");
        printCollection(list);
        
        // Тестування пошуку
        System.out.println("\nПошук елементів:");
        System.out.println("Індекс 'Перший': " + list.indexOf("Перший"));
        System.out.println("Індекс 'Третій': " + list.indexOf("Третій"));
        System.out.println("Містить 'Другий': " + list.contains("Другий"));
        System.out.println("Містить 'Відсутній': " + list.contains("Відсутній"));
        
        // Тестування Queue методів
        System.out.println("\nТестування Queue методів:");
        System.out.println("Перший елемент (peek): " + list.peek());
        System.out.println("Видалення першого елемента (poll)");
        list.poll();
        printCollection(list);
        
        // Тестування очищення
        System.out.println("\nОчищення колекції:");
        list.clear();
        System.out.println("Розмір після очищення: " + list.size());
        System.out.println("Порожня: " + list.isEmpty());
    }
    
    private static void testStack() {
        System.out.println("\n===== Тестування Stack =====\n");
        MyStack<String> stack = new MyStack<>();
        
        // Тестування додавання елементів
        System.out.println("Додавання елементів:");
        stack.push("Перший");
        stack.push("Другий");
        stack.push("Третій");
        printCollection(stack);
        
        // Тестування peek і pop
        System.out.println("\nВерхній елемент (peek): " + stack.peek());
        System.out.println("Видалення верхнього елемента (pop): " + stack.pop());
        System.out.println("Стек після pop:");
        printCollection(stack);
        
        // Тестування очищення
        System.out.println("\nОчищення стеку:");
        stack.clear();
        System.out.println("Розмір після очищення: " + stack.size());
        System.out.println("Порожній: " + stack.isEmpty());
    }
    
    private static void testQueue() {
        System.out.println("\n===== Тестування Queue =====\n");
        MyQueue<String> queue = new MyQueue<>();
        
        // Тестування додавання елементів
        System.out.println("Додавання елементів:");
        queue.enqueue("Перший");
        queue.enqueue("Другий");
        queue.enqueue("Третій");
        printCollection(queue);
        
        // Тестування peek і dequeue
        System.out.println("\nПерший елемент (peek): " + queue.peek());
        System.out.println("Видалення першого елемента (dequeue): " + queue.dequeue());
        System.out.println("Черга після dequeue:");
        printCollection(queue);
        
        // Тестування очищення
        System.out.println("\nОчищення черги:");
        queue.clear();
        System.out.println("Розмір після очищення: " + queue.size());
        System.out.println("Порожня: " + queue.isEmpty());
    }
    
    private static <T> void printCollection(Iterable<T> collection) {
        System.out.print("Елементи: ");
        for (T item : collection) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}