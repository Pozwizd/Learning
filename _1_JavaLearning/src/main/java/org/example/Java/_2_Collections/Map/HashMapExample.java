package org.example.Java._2_Collections.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Collection;

/**
 * УЧЕБНОЕ ПОСОБИЕ: HashMap в Java
 *
 * ЧТО ТАКОЕ HashMap?
 * HashMap - это наиболее часто используемая реализация интерфейса Map (Dictionary/Ассоциативный массив).
 * Это коллекция, хранящая пары "КЛЮЧ - ЗНАЧЕНИЕ" (Key-Value).
 *
 * ВНУТРЕННЕЕ УСТРОЙСТВО:
 * - Основана на Хеш-таблице (массив "корзин" или "buckets").
 * - Использует hashCode() ключа для определения индекса в массиве.
 * - При коллизиях (совпадении индексов) использует Связный список (Linked List) или
 *   Красно-черное дерево (с Java 8, если элементов в корзине > 8).
 *
 * СТРУКТУРА ХЕШ-ТАБЛИЦЫ:
 * Массив Node<K,V>[] table (по умолчанию 16 корзин):
 *
 * Index 0:  null
 * Index 1:  [Key="A", Val=1] -> [Key="B", Val=2]  (Коллизия! Связный список)
 * Index 2:  [Key="C", Val=5]
 * ...
 * Index 15: null
 *
 * ПРИНЦИП РАБОТЫ (ОЧЕНЬ УПРОЩЕННО):
 * 1. Вычисляется hash = key.hashCode()
 * 2. Вычисляется index = hash % arrayLength
 * 3. Пара кладется в ячейку с этим индексом
 *
 * ХАРАКТЕРИСТИКИ:
 * - Порядок элементов НЕ гарантируется (может меняться при изменении размера)
 * - Ключи должны быть УНИКАЛЬНЫ (дубликаты перезаписывают значение)
 * - Разрешает 1 null ключ и множество null значений
 * - НЕ потокобезопасна (не synchronized)
 * - Очень быстрая для основных операций
 *
 * ВРЕМЕННАЯ СЛОЖНОСТЬ (Big O):
 * - put()         - O(1) в среднем, O(n) / O(log n) в худшем случае (коллизии)
 * - get()         - O(1) в среднем
 * - remove()      - O(1) в среднем
 * - containsKey() - O(1) в среднем
 * - size()        - O(1)
 *
 * ОТЛИЧИЯ ОТ TreeMap:
 * ┌──────────────┬─────────────────────┬──────────────────────┐
 * │ Критерий     │ HashMap             │ TreeMap              │
 * ├──────────────┼─────────────────────┼──────────────────────┤
 * │ Структура    │ Хеш-таблица         │ Красно-черное дерево │
 * │ Порядок      │ НЕТ (хаотичный)     │ ДА (сортировка)      │
 * │ Null ключи   │ Разрешен (1 шт)     │ ЗАПРЕЩЕНЫ            │
 * │ Скорость     │ O(1) (очень быстро) │ O(log n)             │
 * │ Интерфейс    │ Map                 │ NavigableMap, Map    │
 * └──────────────┴─────────────────────┴──────────────────────┘
 *
 * @author Учебное пособие
 * @version 2.0
 */
public class HashMapExample {

    public static void main(String[] args) {

        // ======================================
        // 1. СОЗДАНИЕ HashMap
        // ======================================

        /**
         * Конструктор по умолчанию создает карту с:
         * - initialCapacity = 16 (размер массива корзин)
         * - loadFactor = 0.75 (коэффициент загрузки)
         *
         * Когда таблица заполнится на 75% (12 элементов), размер массива
         * удвоится (станет 32), и произойдет re-hashing (перераспределение).
         */
        HashMap<String, Integer> scores = new HashMap<>();

        System.out.println("=== ДОБАВЛЕНИЕ ЭЛЕМЕНТОВ (put) ===");

        // ======================================
        // 2. ДОБАВЛЕНИЕ - метод put(K key, V value)
        // ======================================

        /**
         * АЛГОРИТМ put():
         * 1. Считаем hash = key.hashCode()
         * 2. Определяем индекс корзины (bucket index)
         * 3. Если корзина пуста -> создаем новую Node, кладем туда.
         * 4. Если корзина ЗАНЯТА (Коллизия):
         *    - Проходим по списку/дереву в этой корзине.
         *    - Сравниваем ключи через equals().
         *    - Если нашли такой же ключ -> ПЕРЕЗАПИСЫВАЕМ значение (update).
         *    - Если не нашли -> добавляем новый узел в конец.
         */

        System.out.println("Добавляем Alice...");
        scores.put("Alice", 95); // hash("Alice") -> index X
        System.out.println("Текущая карта: " + scores);

        System.out.println("\nДобавляем Bob...");
        scores.put("Bob", 83);   // hash("Bob") -> index Y
        System.out.println("Текущая карта: " + scores);

        System.out.println("\nДобавляем Charlie...");
        scores.put("Charlie", 77);
        System.out.println("Текущая карта: " + scores);

        // Обновление значения существующего ключа
        System.out.println("\n=== ОБНОВЛЕНИЕ ЗНАЧЕНИЯ ===");
        System.out.println("Попытка добавить Alice с новым баллом 100...");
        Integer oldValue = scores.put("Alice", 100); // Ключ "Alice" уже есть!

        System.out.println("Старое значение было: " + oldValue);
        System.out.println("Новое значение Alice: " + scores.get("Alice"));
        System.out.println("Карта после обновления (размер не изменился): " + scores);

        // Добавление null
        System.out.println("\n=== РАБОТА С NULL ===");
        scores.put(null, 0); // HashMap разрешает один null-ключ (обычно index 0)
        System.out.println("Добавили null-ключ: " + scores);

        // ======================================
        // 3. ПОЛУЧЕНИЕ - метод get(Object key)
        // ======================================

        System.out.println("\n=== ПОЛУЧЕНИЕ ЗНАЧЕНИЯ (get) ===");

        /**
         * АЛГОРИТМ get():
         * 1. hash = key.hashCode() -> вычисляем индекс.
         * 2. Идем в корзину по индексу.
         * 3. Проходим по цепочке элементов (если была коллизия):
         *    - if (node.hash == hash && node.key.equals(key)) -> return value
         * 4. Если не нашли -> return null.
         * Сложность: O(1), если нет коллизий.
         */

        int aliceScore = scores.get("Alice");
        System.out.println("Баллы Alice: " + aliceScore);

        Integer unknownScore = scores.get("Dave"); // Такого ключа нет
        System.out.println("Баллы Dave (нет ключа): " + unknownScore); // null

        // Безопасное получение (Java 8+)
        int daveScoreSafe = scores.getOrDefault("Dave", -1);
        System.out.println("Баллы Dave (через getOrDefault): " + daveScoreSafe);

        // ======================================
        // 4. ПРОВЕРКА НАЛИЧИЯ
        // ======================================

        System.out.println("\n=== ПРОВЕРКИ (contains) ===");

        if (scores.containsKey("Bob")) {
            System.out.println("✓ Ключ 'Bob' существует. Операция очень быстрая O(1).");
        }

        if (scores.containsValue(100)) {
            System.out.println("✓ Кто-то набрал 100 баллов. Осторожно: это медленно O(n)!");
            // containsValue вынужден перебрать ВСЕ корзины и ВСЕ элементы.
        }

        // ======================================
        // 5. УДАЛЕНИЕ - метод remove(Object key)
        // ======================================

        System.out.println("\n=== УДАЛЕНИЕ (remove) ===");

        // Удаляем null-ключ, чтобы не мешал
        scores.remove(null);

        System.out.println("Удаляем Charlie...");
        Integer removedValue = scores.remove("Charlie");
        System.out.println("Удалено значение: " + removedValue);
        System.out.println("Карта после удаления: " + scores);

        // ======================================
        // 6. СПОСОБЫ ОБХОДА (ИТЕРАЦИЯ)
        // ======================================

        System.out.println("\n=== СПОСОБЫ ОБХОДА HASHMAP ===");

        /**
         * HashMap не является Iterable напрямую.
         * Нужно получить Collection view: keySet(), values() или entrySet().
         */

        // Способ 1: entrySet() - Самый эффективный (доступ и к ключу, и к значению)
        System.out.println("1. Через entrySet() (пары ключ-значение):");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            String name = entry.getKey();
            int score = entry.getValue();
            System.out.println("   Student: " + name + ", Score: " + score);
        }

        // Способ 2: keySet() - Если нужны только ключи
        System.out.println("\n2. Через keySet() (только ключи):");
        Set<String> keys = scores.keySet();
        for (String key : keys) {
            // scores.get(key) - можно, но это лишний поиск в хеш-таблице!
            System.out.println("   Key: " + key);
        }

        // Способ 3: values() - Если нужны только значения
        System.out.println("\n3. Через values() (только значения):");
        Collection<Integer> values = scores.values();
        for (Integer val : values) {
            System.out.println("   Value: " + val);
        }

        // Способ 4: forEach (Java 8+)
        System.out.println("\n4. Через lambda forEach:");
        scores.forEach((k, v) -> System.out.println("   " + k + " = " + v));

        // ======================================
        // 7. ВАЖНОСТЬ equals() и hashCode()
        // ======================================

        System.out.println("\n=== ВАЖНОЕ ПРИМЕЧАНИЕ ===");
        System.out.println("Для корректной работы HashMap ключи ОБЯЗАНЫ переопределять");
        System.out.println("equals() и hashCode() согласованно!");

        // Пример пользовательского ключа
        class ID {
            int id;
            ID(int id) { this.id = id; }

            // Если закомментировать hashCode, HashMap сломается (не найдет ключ)!
            @Override
            public int hashCode() { return id; }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                return id == ((ID)o).id;
            }
            @Override
            public String toString() { return "ID#" + id; }
        }

        HashMap<ID, String> customMap = new HashMap<>();
        ID key1 = new ID(1);
        ID key2 = new ID(1); // Логически равен key1

        customMap.put(key1, "User Data");

        System.out.println("Ищем данные по НОВОМУ объекту с тем же ID:");
        // Если hashCode/equals реализованы верно, найдет "User Data". Иначе - null.
        System.out.println("Результат поиска: " + customMap.get(key2));
    }
}

/**
 * =====================================
 * ВИЗУАЛИЗАЦИЯ КОЛЛИЗИЙ
 * =====================================
 *
 * Представим, что массив buckets имеет размер 4.
 * Keys: "A", "B", "C", "D", "E"
 *
 * hash("A") % 4 = 1
 * hash("B") % 4 = 1  <- КОЛЛИЗИЯ с "A"
 * hash("C") % 4 = 2
 * hash("D") % 4 = 1  <- КОЛЛИЗИЯ с "A" и "B"
 * hash("E") % 4 = 0
 *
 * Структура в памяти (до Java 8 - списки):
 *
 * Bucket [0]: Node("E") -> null
 * Bucket [1]: Node("A") -> Node("B") -> Node("D") -> null (Связный список)
 * Bucket [2]: Node("C") -> null
 * Bucket [3]: null
 *
 * Структура в памяти (Java 8+ при большом кол-ве коллизий):
 * Если длина списка Bucket[1] превысит 8 элементов, он превратится в TreeNode
 * (Красно-черное дерево) для ускорения поиска с O(n) до O(log n).
 *
 * =====================================
 * ГДЕ ИСПОЛЬЗОВАТЬ HashMap?
 * =====================================
 *
 * ✓ ПОДХОДИТ ДЛЯ:
 * 1. Кэширование данных (быстрый доступ по ID).
 * 2. Подсчет частоты слов (Word Count).
 * 3. Хранение настроек (ключ-значение).
 * 4. Поиск соответствий (Mapping).
 * 5. Когда порядок элементов НЕ важен.
 *
 * ✗ НЕ ПОДХОДИТ ДЛЯ:
 * 1. Когда нужен отсортированный порядок (используйте TreeMap).
 * 2. Когда важен порядок вставки (используйте LinkedHashMap).
 * 3. Многопоточной среды без синхронизации (используйте ConcurrentHashMap).
 *
 * =====================================
 * ПЛЮСЫ (+):
 * =====================================
 * + Самая быстрая реализация Map (O(1) для get/put).
 * + Простой и понятный API.
 * + Гибкость (любые объекты в качестве ключей и значений).
 *
 * =====================================
 * МИНУСЫ (-):
 * =====================================
 * - Порядок элементов хаотичен.
 * - Производительность падает при плохом hashCode() (много коллизий).
 * - Потребляет больше памяти, чем массивы.
 *
 * =====================================
 * ВАЖНЫЕ ЗАМЕЧАНИЯ:
 * =====================================
 *
 * 1. НЕИЗМЕНЯЕМОСТЬ КЛЮЧЕЙ (IMMUTABILITY):
 *    Ключи в HashMap лучше делать immutable (как String, Integer).
 *    Если изменить поля объекта-ключа ПОСЛЕ вставки в Map, его hashCode
 *    изменится, и HashMap больше не сможет найти этот элемент!
 *
 * 2. THREAD SAFETY:
 *    HashMap НЕ потокобезопасен.
 *    Если несколько потоков меняют Map одновременно, возможны ошибки или вечный цикл.
 *    Решение:
 *    - Map<K,V> m = Collections.synchronizedMap(new HashMap<>());
 *    - ConcurrentHashMap<K,V> (предпочтительнее, быстрее).
 *
 * 3. CAPACITY и LOAD FACTOR:
 *    Если вы знаете, что будет 1000 элементов, лучше создать:
 *    new HashMap<>(1350); // (1000 / 0.75) + 1
 *    Это предотвратит лишние расширения массива (re-hashing).
 */