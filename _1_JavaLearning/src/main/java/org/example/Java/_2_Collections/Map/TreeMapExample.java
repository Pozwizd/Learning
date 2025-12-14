package org.example.Java._2_Collections.Map;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * УЧЕБНОЕ ПОСОБИЕ: TreeMap в Java
 *
 * ЧТО ТАКОЕ TreeMap?
 * TreeMap - это реализация интерфейса Map, основанная на Красно-Черном дереве (Red-Black Tree).
 * В отличие от HashMap, она хранит ключи в ОТСОРТИРОВАННОМ порядке.
 *
 * ВНУТРЕННЕЕ УСТРОЙСТВО:
 * Это самобалансирующееся бинарное дерево поиска.
 * - Каждый узел имеет цвет (красный или черный) для поддержания баланса.
 * - Балансировка гарантирует, что высота дерева остается логарифмической O(log n).
 *
 * ПОРЯДОК СОРТИРОВКИ:
 * 1. Natural Ordering (Естественный порядок):
 *    Если ключи реализуют интерфейс Comparable (например, String, Integer),
 *    они сортируются автоматически (A->Z, 1->9).
 * 2. Custom Comparator (Кастомный компаратор):
 *    Можно передать свой Comparator в конструктор для особого порядка сортировки.
 *
 * ХАРАКТЕРИСТИКИ:
 * - Потокобезопасность: НЕТ.
 * - Null ключи: ЗАПРЕЩЕНЫ (выбросит NullPointerException).
 * - Null значения: РАЗРЕШЕНЫ.
 * - Скорость: O(log n) для основных операций (get, put, remove, containsKey).
 *   Это медленнее, чем O(1) у HashMap, но дает сортировку.
 *
 * @author Учебное пособие
 * @version 2.0
 */
public class TreeMapExample {
    public static void main(String[] args) {

        // ======================================
        // 1. СОЗДАНИЕ TreeMap (Natural Ordering)
        // ======================================

        System.out.println("=== 1. СОЗДАНИЕ И АВТОМАТИЧЕСКАЯ СОРТИРОВКА ===");
        // Ключи (String) будут отсортированы по алфавиту
        TreeMap<String, Integer> scores = new TreeMap<>();

        scores.put("John", 25);
        scores.put("Mary", 30);
        scores.put("Bob", 20);
        scores.put("Alice", 27);

        /**
         * Порядок вставки: John, Mary, Bob, Alice
         * Ожидаемый порядок (алфавитный): Alice, Bob, John, Mary
         */
        System.out.println("Содержимое карты (всегда отсортировано по ключу):");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(" - " + entry.getKey() + " : " + entry.getValue());
        }

        // ======================================
        // 2. НАВИГАЦИЯ (Navigation API)
        // ======================================
        // TreeMap реализует NavigableMap, что дает методы для поиска "ближайших" соседей.

        System.out.println("\n=== 2. МЕТОДЫ НАВИГАЦИИ ===");

        System.out.println("Первый ключ (min): " + scores.firstKey());
        System.out.println("Последний ключ (max): " + scores.lastKey());

        // Поиск ближайших ключей к "Charlie" (которого нет в карте)
        // Alice, Bob, [Charlie], John, Mary
        System.out.println("lowerKey(\"Charlie\") (строго меньше): " + scores.lowerKey("Charlie")); // Bob
        System.out.println("floorKey(\"Charlie\") (меньше или равно): " + scores.floorKey("Charlie")); // Bob
        System.out.println("ceilingKey(\"Charlie\") (больше или равно): " + scores.ceilingKey("Charlie")); // John
        System.out.println("higherKey(\"Charlie\") (строго больше): " + scores.higherKey("Charlie")); // John


        // ======================================
        // 3. ДИАПАЗОНЫ (Range Views)
        // ======================================

        System.out.println("\n=== 3. РАБОТА С ДИАПАЗОНАМИ ===");
        // subMap(fromInclusive, toExclusive) - от "Bob" (вкл) до "Mary" (искл)
        SortedMap<String, Integer> subMap = scores.subMap("Bob", "Mary");
        System.out.println("subMap [Bob, Mary): " + subMap); // Bob, John

        // headMap(toExclusive) - все, что строго меньше "John"
        System.out.println("headMap (< John): " + scores.headMap("John")); // Alice, Bob

        // tailMap(fromInclusive) - все, что больше или равно "John"
        System.out.println("tailMap (>= John): " + scores.tailMap("John")); // John, Mary


        // ======================================
        // 4. ИСПОЛЬЗОВАНИЕ COMPARATOR
        // ======================================

        System.out.println("\n=== 4. КАСТОМНАЯ СОРТИРОВКА (Reverse Order) ===");
        // Создаем карту с обратным порядком сортировки ключей
        TreeMap<Integer, String> reverseMap = new TreeMap<>(Comparator.reverseOrder());

        reverseMap.put(1, "One");
        reverseMap.put(5, "Five");
        reverseMap.put(3, "Three");

        System.out.println("Обратный порядок ключей: " + reverseMap); // {5=Five, 3=Three, 1=One}
    }
}

/**
 * =====================================
 * СРАВНЕНИЕ: HashMap vs LinkedHashMap vs TreeMap
 * =====================================
 *
 * ┌───────────────┬──────────────────┬──────────────────────┬──────────────────────┐
 * │ Характеристика│ HashMap          │ LinkedHashMap        │ TreeMap              │
 * ├───────────────┼──────────────────┼──────────────────────┼──────────────────────┤
 * │ Структура     │ Хеш-таблица      │ Хеш-таблица + Список │ Красно-Черное дерево │
 * │ Порядок       │ Хаотичный        │ Вставки / Доступа    │ Сортировка по ключу  │
 * │ Время (get/put)│ O(1)            │ O(1)                 │ O(log n)             │
 * │ Null ключи    │ 1 ключ           │ 1 ключ               │ ЗАПРЕЩЕНЫ            │
 * │ Интерфейсы    │ Map              │ Map                  │ Map, SortedMap,      │
 * │               │                  │                      │ NavigableMap         │
 * └───────────────┴──────────────────┴──────────────────────┴──────────────────────┘
 *
 * =====================================
 * КОГДА ИСПОЛЬЗОВАТЬ TreeMap?
 * =====================================
 *
 * 1. Вам нужен ГАРАНТИРОВАННЫЙ ПОРЯДОК сортировки ключей (алфавитный, по возрастанию).
 * 2. Вам нужны выборки диапазонов (все пользователи от A до K).
 * 3. Вам нужны методы навигации (найти ближайшую дату к заданной).
 *
 * Если сортировка не нужна — используйте HashMap, он быстрее.
 */