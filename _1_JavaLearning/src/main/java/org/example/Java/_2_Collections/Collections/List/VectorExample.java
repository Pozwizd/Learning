package org.example.Java._2_Collections.Collections.List;

import java.util.Vector;

/**
 * Демонстрационный класс, показывающий базовую работу с классом Vector в Java.[web:93]
 *
 * Vector — это динамический массив (growable array), очень похожий по поведению на ArrayList.[web:102]
 * Он хранит элементы вплотную в массиве и автоматически увеличивает ёмкость при переполнении.[web:99]
 *
 * Важные особенности Vector:[web:93]
 * - Поддерживает порядок вставки и индексы (0, 1, 2, ...), как и ArrayList.[web:99]
 * - Допускает дубликаты и значение null.[web:99]
 * - Является «legacy»-классом: появился ещё в ранних версиях Java, до появления современных коллекций.[web:93]
 * - Все основные методы синхронизированы, поэтому Vector потокобезопасен, но медленнее в однопоточном коде.[web:98]
 *
 * В современном коде чаще используют ArrayList, а при необходимости потокобезопасности:
 * - либо оборачивают обычный список через Collections.synchronizedList(...);[web:94]
 * - либо используют другие concurrent-коллекции.[web:92]
 *
 * Тем не менее, Vector полезно знать, чтобы понимать старый код и разницу с ArrayList.[web:96]
 */
public class VectorExample {

    /**
     * Точка входа в программу.[web:102]
     *
     * Здесь демонстрируются базовые операции:[web:93]
     * - создание Vector;[web:99]
     * - добавление элементов (add);[web:93]
     * - доступ по индексу (get);[web:93]
     * - удаление элемента (remove);[web:99]
     * - получение размера (size).[web:99]
     */
    public static void main(String[] args) {
        // Создаём Vector, который будет хранить строки.[web:102]
        // Тип <String> говорит, что в коллекции могут быть только объекты String.[web:99]
        Vector<String> vector = new Vector<>();

        // Добавляем элементы в конец вектора с помощью add.[web:93]
        // При необходимости Vector увеличит внутренний массив (по умолчанию удваивая его размер).[web:97]
        vector.add("apple");
        vector.add("banana");
        vector.add("cherry");

        System.out.println("Initial vector: " + vector); // Выведет все элементы вектора.[web:101]

        // Получаем элемент по индексу с помощью get.[web:93]
        // Индексация начинается с 0: 0 — первый элемент, 1 — второй и т.д.[web:99]
        String firstElement = vector.get(0);
        System.out.println("First element (index 0): " + firstElement);

        // Меняем значение по индексу с помощью set.[web:93]
        // Здесь заменяем "banana" (индекс 1) на "blueberry".[web:99]
        vector.set(1, "blueberry");
        System.out.println("After set(1, \"blueberry\"): " + vector);

        // Удаляем элемент по значению.[web:93]
        // remove(Object o) ищет первый такой элемент и удаляет его, если он найден.[web:99]
        vector.remove("cherry");
        System.out.println("After removing \"cherry\": " + vector);

        // Можно удалять и по индексу: remove(int index).[web:93]
        // Здесь удаляем элемент с индексом 0 (первый элемент).[web:99]
        vector.remove(0);
        System.out.println("After removing element at index 0: " + vector);

        // Метод size() возвращает количество элементов в векторе.[web:93]
        int size = vector.size();
        System.out.println("Vector size: " + size);

        // Как и ArrayList, Vector поддерживает перебор элементов в цикле for-each.[web:101]
        System.out.println("Iterating over vector elements:");
        for (String item : vector) {
            System.out.println(item);
        }

        // Важно понимать отличие от ArrayList:
        // - Vector синхронизирован (thread-safe), поэтому операции медленнее из-за блокировок.[web:103]
        // - ArrayList не синхронизирован, поэтому быстрее и рекомендован для нового кода в однопоточной среде.[web:97]
        // Vector оставили в языке главным образом для поддержки старых проектов и случаев,
        // когда нужна простая синхронизированная реализация динамического массива.[web:92]
    }
}
