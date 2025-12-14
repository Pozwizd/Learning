package org.example.Java._2_Collections.Collections.Set;

import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Arrays;

/**
 * УЧЕБНОЕ ПОСОБИЕ: LinkedHashSet в Java
 *
 * ЧТО ТАКОЕ LinkedHashSet?
 * LinkedHashSet - это класс из Java Collections Framework, который расширяет HashSet.
 * Это коллекция, которая сочетает в себе:
 * - Уникальность элементов (как в HashSet)
 * - Сохранение ПОРЯДКА ДОБАВЛЕНИЯ элементов (как в LinkedList)
 *
 * ВНУТРЕННЕЕ УСТРОЙСТВО:
 * LinkedHashSet = HashSet + Двусвязный список
 *
 * ┌─────────────────────────────────────────────────────────┐
 * │           LinkedHashSet<String>                         │
 * │  (расширяет HashSet, использует LinkedHashMap)          │
 * └────────────────────┬────────────────────────────────────┘
 *                      │
 *                      ▼
 * ┌─────────────────────────────────────────────────────────┐
 * │      LinkedHashMap<E, Object>                           │
 * │  (комбинация хеш-таблицы и двусвязного списка)          │
 * └─────────────────────────────────────────────────────────┘
 *
 * СТРУКТУРА ХРАНЕНИЯ:
 *
 * 1. ХЕШ-ТАБЛИЦА (для быстрого доступа):
 *    Бакет[0] → null
 *    Бакет[1] → "banana"
 *    Бакет[2] → null
 *    Бакет[3] → "apple"
 *    Бакет[5] → "cherry"
 *
 * 2. ДВУСВЯЗНЫЙ СПИСОК (для сохранения порядка):
 *    head → "apple" ⇄ "banana" ⇄ "cherry" → tail
 *           (1-й)     (2-й)      (3-й)
 *
 * КАЖДЫЙ УЗЕЛ СОДЕРЖИТ:
 * class Entry {
 *     E element;           // сам элемент
 *     Entry<E> next;       // следующий в хеш-цепочке (для коллизий)
 *     Entry<E> before;     // предыдущий в порядке добавления
 *     Entry<E> after;      // следующий в порядке добавления
 * }
 *
 * ХАРАКТЕРИСТИКИ:
 * - Сохраняет ПОРЯДОК ДОБАВЛЕНИЯ элементов
 * - Не допускает дубликаты (уникальные элементы)
 * - Позволяет хранить один null элемент
 * - НЕ потокобезопасен (не synchronized)
 * - Немного медленнее HashSet из-за поддержки двусвязного списка
 * - Потребляет больше памяти, чем HashSet
 *
 * ВРЕМЕННАЯ СЛОЖНОСТЬ (Big O):
 * - add() - O(1) в среднем
 * - remove() - O(1) в среднем
 * - contains() - O(1) в среднем
 * - итерация - O(n), но в ПОРЯДКЕ ДОБАВЛЕНИЯ!
 *
 * ОТЛИЧИЯ ОТ ДРУГИХ SET:
 * ┌──────────────┬─────────────┬──────────────┬─────────────┐
 * │              │ HashSet     │LinkedHashSet │ TreeSet     │
 * ├──────────────┼─────────────┼──────────────┼─────────────┤
 * │ Порядок      │ НЕТ         │ Порядок      │ Сортировка  │
 * │              │ (хаотичный) │ добавления   │ (возрастание)│
 * │ Скорость     │ O(1)        │ O(1)         │ O(log n)    │
 * │ Память       │ Меньше всего│ Средне       │ Больше всего│
 * │ Null         │ Допускается │ Допускается  │ НЕТ         │
 * └──────────────┴─────────────┴──────────────┴─────────────┘
 *
 * @author Учебное пособие
 * @version 2.0
 */
public class LinkedHashSetExample {

    public static void main(String[] args) {

        System.out.println("=== СОЗДАНИЕ LinkedHashSet ===\n");

        // ======================================
        // 1. СОЗДАНИЕ LinkedHashSet
        // ======================================

        /**
         * Конструктор по умолчанию создает LinkedHashSet с:
         * - начальной емкостью = 16
         * - коэффициентом загрузки = 0.75
         *
         * ВАЖНО: Внутри создается LinkedHashMap (не HashMap!)
         *
         * Исходный код LinkedHashSet:
         * public LinkedHashSet() {
         *     super(16, .75f, true);  // true = использовать LinkedHashMap
         * }
         */
        LinkedHashSet<String> mySet = new LinkedHashSet<>();

        // Альтернативные конструкторы:
        // LinkedHashSet<String> mySet2 = new LinkedHashSet<>(32); // емкость 32
        // LinkedHashSet<String> mySet3 = new LinkedHashSet<>(32, 0.8f); // емкость 32, load factor 0.8
        // LinkedHashSet<String> mySet4 = new LinkedHashSet<>(Arrays.asList("a", "b")); // из коллекции

        System.out.println("=== ДОБАВЛЕНИЕ ЭЛЕМЕНТОВ ===\n");

        // ======================================
        // 2. ДОБАВЛЕНИЕ ЭЛЕМЕНТОВ - метод add()
        // ======================================

        /**
         * АЛГОРИТМ add() В LinkedHashSet:
         *
         * 1. ВЫЧИСЛЕНИЕ ХЕШ-КОДА
         *    hash = element.hashCode()
         *
         * 2. ОПРЕДЕЛЕНИЕ ИНДЕКСА БАКЕТА
         *    bucketIndex = hash & (capacity - 1)
         *
         * 3. ПРОВЕРКА НА ДУБЛИКАТ
         *    - Ищем в бакете элемент с таким же хешом и equals()
         *    - Если найден → НЕ добавляем, возвращаем false
         *
         * 4. ДОБАВЛЕНИЕ В ХЕШ-ТАБЛИЦУ
         *    - Создаем новый Entry
         *    - Помещаем в соответствующий бакет
         *
         * 5. ДОБАВЛЕНИЕ В ДВУСВЯЗНЫЙ СПИСОК (УНИКАЛЬНАЯ ЧАСТЬ!)
         *    - Связываем новый Entry с концом списка
         *    - tail.after = newEntry
         *    - newEntry.before = tail
         *    - tail = newEntry
         *
         * ВИЗУАЛИЗАЦИЯ ДОБАВЛЕНИЯ "apple":
         *
         * До добавления:
         * head → tail (пусто)
         *
         * После добавления "apple":
         * head → "apple" → tail
         *        └─ hashCode: 93029210
         *        └─ бакет[3]
         */

        System.out.println("Шаг 1: Добавляем 'apple'");
        boolean added1 = mySet.add("apple");
        System.out.println("  Результат: " + added1);  // true
        System.out.println("  Состояние: " + mySet);
        System.out.println("  Внутренний список: head → 'apple' → tail\n");

        /**
         * ДОБАВЛЕНИЕ "banana"
         *
         * После добавления:
         * head → "apple" ⇄ "banana" → tail
         *         (1-й)     (2-й)
         *
         * Двусвязные ссылки:
         * "apple".after = "banana"
         * "banana".before = "apple"
         */
        System.out.println("Шаг 2: Добавляем 'banana'");
        boolean added2 = mySet.add("banana");
        System.out.println("  Результат: " + added2);  // true
        System.out.println("  Состояние: " + mySet);
        System.out.println("  Внутренний список: head → 'apple' ⇄ 'banana' → tail\n");

        /**
         * ДОБАВЛЕНИЕ "cherry"
         *
         * После добавления:
         * head → "apple" ⇄ "banana" ⇄ "cherry" → tail
         *         (1-й)     (2-й)      (3-й)
         */
        System.out.println("Шаг 3: Добавляем 'cherry'");
        boolean added3 = mySet.add("cherry");
        System.out.println("  Результат: " + added3);  // true
        System.out.println("  Состояние: " + mySet);
        System.out.println("  Внутренний список: head → 'apple' ⇄ 'banana' ⇄ 'cherry' → tail\n");

        /**
         * ПОПЫТКА ДОБАВИТЬ ДУБЛИКАТ "banana"
         *
         * АЛГОРИТМ ПРОВЕРКИ НА ДУБЛИКАТ:
         * 1. Вычисляем hash("banana")
         * 2. Находим бакет
         * 3. Проверяем через equals() - "banana".equals("banana") = true
         * 4. ДУБЛИКАТ НАЙДЕН! НЕ добавляем, возвращаем false
         *
         * ВАЖНО: Порядок НЕ изменяется, список остается прежним!
         */
        System.out.println("Шаг 4: Пытаемся добавить дубликат 'banana'");
        boolean added4 = mySet.add("banana");
        System.out.println("  Результат: " + added4);  // false - дубликат!
        System.out.println("  Состояние: " + mySet);
        System.out.println("  ⚠ ДУБЛИКАТ НЕ ДОБАВЛЕН! Порядок сохранен.\n");

        /**
         * ЕЩЕ ОДНА ПОПЫТКА ДОБАВИТЬ "banana"
         */
        System.out.println("Шаг 5: Снова пытаемся добавить 'banana'");
        boolean added5 = mySet.add("banana");
        System.out.println("  Результат: " + added5);  // false
        System.out.println("  Состояние: " + mySet);
        System.out.println("  ⚠ Дубликат опять отклонен!\n");

        /**
         * КЛЮЧЕВАЯ ОСОБЕННОСТЬ LinkedHashSet:
         * ПОРЯДОК ВЫВОДА = ПОРЯДОК ДОБАВЛЕНИЯ!
         *
         * Мы добавляли: "apple" → "banana" → "cherry" → "banana" → "banana"
         * В множестве: ["apple", "banana", "cherry"]
         *
         * Порядок: ТОЧНО такой же, как при первом добавлении!
         */
        System.out.println("=== РЕЗУЛЬТАТ ===");
        System.out.println("LinkedHashSet содержит следующие элементы: " + mySet);
        System.out.println("Размер множества: " + mySet.size());
        System.out.println("\n✓ ПОРЯДОК ДОБАВЛЕНИЯ СОХРАНЕН!");
        System.out.println("  Добавили первым: 'apple' → выводится первым");
        System.out.println("  Добавили вторым: 'banana' → выводится вторым");
        System.out.println("  Добавили третьим: 'cherry' → выводится третьим\n");

        System.out.println("=== УДАЛЕНИЕ ЭЛЕМЕНТОВ ===\n");

        // ======================================
        // 3. УДАЛЕНИЕ ЭЛЕМЕНТОВ - метод remove()
        // ======================================

        /**
         * АЛГОРИТМ remove() В LinkedHashSet:
         *
         * 1. ПОИСК В ХЕШ-ТАБЛИЦЕ
         *    - Вычисляем hash(element)
         *    - Находим бакет
         *    - Ищем элемент через equals()
         *
         * 2. УДАЛЕНИЕ ИЗ ХЕШ-ТАБЛИЦЫ
         *    - Удаляем Entry из бакета
         *
         * 3. УДАЛЕНИЕ ИЗ ДВУСВЯЗНОГО СПИСКА (УНИКАЛЬНАЯ ЧАСТЬ!)
         *    - Перелинковываем ссылки:
         *      entry.before.after = entry.after
         *      entry.after.before = entry.before
         *
         * ВИЗУАЛИЗАЦИЯ УДАЛЕНИЯ "cherry":
         *
         * До удаления:
         * head → "apple" ⇄ "banana" ⇄ "cherry" → tail
         *
         * После удаления "cherry":
         * head → "apple" ⇄ "banana" → tail
         *        └────────────┘
         *     "banana".after = tail
         *     tail.before = "banana"
         *
         * Сложность: O(1) - благодаря хеш-таблице и прямым ссылкам
         */

        System.out.println("Удаляем элемент 'cherry'...");
        boolean removed = mySet.remove("cherry");
        System.out.println("  Результат удаления: " + removed);  // true
        System.out.println("  Список после удаления: head → 'apple' ⇄ 'banana' → tail\n");

        System.out.println("=== ПРОВЕРКА НАЛИЧИЯ ЭЛЕМЕНТА ===\n");

        // ======================================
        // 4. ПРОВЕРКА НАЛИЧИЯ - метод contains()
        // ======================================

        /**
         * АЛГОРИТМ contains():
         *
         * 1. Вычисляем hash(element)
         * 2. Находим бакет по индексу: hash & (capacity - 1)
         * 3. Ищем в бакете элемент через equals()
         * 4. Если найден → true, иначе → false
         *
         * ВАЖНО: Для проверки используется ТОЛЬКО хеш-таблица,
         * двусвязный список НЕ участвует в поиске!
         *
         * Поэтому сложность остается O(1), как в HashSet
         */

        if (mySet.contains("banana")) {
            System.out.println("✓ Множество содержит элемент 'banana'");
            System.out.println("  Проверка выполнена за O(1) через хеш-таблицу\n");
        }

        if (!mySet.contains("cherry")) {
            System.out.println("✗ Множество НЕ содержит элемент 'cherry' (был удален)\n");
        }

        System.out.println("=== ИТОГОВОЕ СОСТОЯНИЕ ===");
        System.out.println("LinkedHashSet содержит следующие элементы после удаления: " + mySet);
        System.out.println("Итоговый размер: " + mySet.size());
        System.out.println("Внутренний список: head → 'apple' ⇄ 'banana' → tail\n");

        // ======================================
        // 5. СРАВНЕНИЕ С HashSet
        // ======================================

        System.out.println("=== СРАВНЕНИЕ LinkedHashSet И HashSet ===\n");

        /**
         * ДЕМОНСТРАЦИЯ РАЗНИЦЫ МЕЖДУ HashSet И LinkedHashSet
         */

        // HashSet - НЕ сохраняет порядок
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("apple");
        hashSet.add("banana");
        hashSet.add("cherry");
        hashSet.add("date");
        hashSet.add("elderberry");

        System.out.println("HashSet (порядок НЕ гарантирован):");
        System.out.println("  Добавили: apple → banana → cherry → date → elderberry");
        System.out.println("  Вывод:    " + hashSet);
        System.out.println("  ⚠ Порядок может быть любым!\n");

        // LinkedHashSet - СОХРАНЯЕТ порядок
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("apple");
        linkedHashSet.add("banana");
        linkedHashSet.add("cherry");
        linkedHashSet.add("date");
        linkedHashSet.add("elderberry");

        System.out.println("LinkedHashSet (порядок ГАРАНТИРОВАН):");
        System.out.println("  Добавили: apple → banana → cherry → date → elderberry");
        System.out.println("  Вывод:    " + linkedHashSet);
        System.out.println("  ✓ Порядок ТОЧНО совпадает с порядком добавления!\n");

        // ======================================
        // 6. ИТЕРАЦИЯ ПО ЭЛЕМЕНТАМ
        // ======================================

        System.out.println("=== СПОСОБЫ ОБХОДА ЭЛЕМЕНТОВ ===\n");

        /**
         * ВАЖНАЯ ОСОБЕННОСТЬ:
         * Итерация по LinkedHashSet происходит В ПОРЯДКЕ ДОБАВЛЕНИЯ!
         *
         * Это достигается за счет обхода двусвязного списка,
         * а НЕ бакетов хеш-таблицы
         */

        // Способ 1: Enhanced for loop (рекомендуется)
        System.out.println("Способ 1 - Enhanced for (в порядке добавления):");
        for (String fruit : mySet) {
            System.out.println("  → " + fruit);
        }
        System.out.println();

        // Способ 2: Iterator
        System.out.println("Способ 2 - Iterator:");
        Iterator<String> iterator = mySet.iterator();
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println("  → " + fruit);
        }
        System.out.println();

        // Способ 3: forEach (Java 8+)
        System.out.println("Способ 3 - forEach с лямбда-выражением:");
        mySet.forEach(fruit -> System.out.println("  → " + fruit));
        System.out.println();

        // Способ 4: Stream API (Java 8+)
        System.out.println("Способ 4 - Stream API:");
        mySet.stream()
                .forEach(fruit -> System.out.println("  → " + fruit));
        System.out.println();

        // ======================================
        // 7. ДОПОЛНИТЕЛЬНЫЕ ОПЕРАЦИИ
        // ======================================

        System.out.println("=== ДОПОЛНИТЕЛЬНЫЕ ОПЕРАЦИИ ===\n");

        // Создаем новый LinkedHashSet для демонстрации
        LinkedHashSet<Integer> numbers = new LinkedHashSet<>();
        numbers.add(10);
        numbers.add(5);
        numbers.add(20);
        numbers.add(3);
        numbers.add(15);

        System.out.println("Числа в порядке добавления: " + numbers);

        // isEmpty() - проверка на пустоту
        System.out.println("\nПустое множество? " + numbers.isEmpty());  // false

        // size() - количество элементов
        System.out.println("Размер множества: " + numbers.size());  // 5

        // clear() - очистка всех элементов
        LinkedHashSet<Integer> tempSet = new LinkedHashSet<>(numbers);
        System.out.println("\nПеред очисткой: " + tempSet);
        tempSet.clear();
        System.out.println("После clear(): " + tempSet);  // []
        System.out.println("Пустое? " + tempSet.isEmpty());  // true

        // addAll() - добавление всех элементов из другой коллекции
        LinkedHashSet<Integer> set1 = new LinkedHashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        LinkedHashSet<Integer> set2 = new LinkedHashSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);

        set1.addAll(set2);
        System.out.println("\nПосле addAll: " + set1);  // [1, 2, 3, 4, 5]
        System.out.println("Порядок: сначала элементы set1, потом новые из set2");

        // removeAll() - удаление всех элементов из другой коллекции
        LinkedHashSet<Integer> set3 = new LinkedHashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        LinkedHashSet<Integer> set4 = new LinkedHashSet<>(Arrays.asList(2, 4));
        set3.removeAll(set4);
        System.out.println("\nПосле removeAll: " + set3);  // [1, 3, 5]

        // retainAll() - оставить только общие элементы
        LinkedHashSet<Integer> set5 = new LinkedHashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        LinkedHashSet<Integer> set6 = new LinkedHashSet<>(Arrays.asList(3, 4, 5, 6, 7));
        set5.retainAll(set6);
        System.out.println("После retainAll: " + set5);  // [3, 4, 5]

        // ======================================
        // 8. ПРАКТИЧЕСКИЕ ПРИМЕРЫ ИСПОЛЬЗОВАНИЯ
        // ======================================

        System.out.println("\n=== ПРАКТИЧЕСКИЕ ПРИМЕРЫ ===\n");

        /**
         * ПРИМЕР 1: Удаление дубликатов с сохранением порядка
         */
        System.out.println("Пример 1: Удаление дубликатов из списка");
        String[] fruitsArray = {"apple", "banana", "apple", "cherry", "banana", "date"};
        System.out.println("Исходный массив: " + Arrays.toString(fruitsArray));

        LinkedHashSet<String> uniqueFruits = new LinkedHashSet<>(Arrays.asList(fruitsArray));
        System.out.println("Уникальные элементы (порядок сохранен): " + uniqueFruits);
        System.out.println();

        /**
         * ПРИМЕР 2: История посещенных страниц
         */
        System.out.println("Пример 2: История посещений (без дубликатов)");
        LinkedHashSet<String> browserHistory = new LinkedHashSet<>();

        // Посещаем страницы
        browserHistory.add("google.com");
        browserHistory.add("github.com");
        browserHistory.add("stackoverflow.com");
        browserHistory.add("google.com");  // повторное посещение - не добавится
        browserHistory.add("youtube.com");

        System.out.println("История (в порядке первого посещения):");
        browserHistory.forEach(page -> System.out.println("  - " + page));
        System.out.println();

        /**
         * ПРИМЕР 3: Очередь уникальных задач
         */
        System.out.println("Пример 3: Очередь задач (без дубликатов)");
        LinkedHashSet<String> taskQueue = new LinkedHashSet<>();

        taskQueue.add("Задача 1: Проверить email");
        taskQueue.add("Задача 2: Написать отчет");
        taskQueue.add("Задача 3: Созвониться с клиентом");
        taskQueue.add("Задача 2: Написать отчет");  // дубликат - не добавится

        System.out.println("Очередь задач (в порядке добавления):");
        int taskNumber = 1;
        for (String task : taskQueue) {
            System.out.println("  " + taskNumber++ + ". " + task);
        }
    }
}

/**
 * =====================================
 * ВИЗУАЛИЗАЦИЯ ВНУТРЕННЕГО УСТРОЙСТВА
 * =====================================
 *
 * LinkedHashSet с элементами ["apple", "banana", "cherry"]:
 *
 * 1. ХЕШ-ТАБЛИЦА (для быстрого поиска):
 * ┌───────────────────────────────────┐
 * │ Бакет[0] → null                   │
 * │ Бакет[1] → Entry("banana")        │
 * │ Бакет[2] → null                   │
 * │ Бакет[3] → Entry("apple")         │
 * │ Бакет[4] → null                   │
 * │ Бакет[5] → Entry("cherry")        │
 * │ ...                               │
 * └───────────────────────────────────┘
 *
 * 2. ДВУСВЯЗНЫЙ СПИСОК (для порядка):
 * ┌──────────────────────────────────────────────────────┐
 * │                                                      │
 * │  header ⇄ Entry("apple") ⇄ Entry("banana") ⇄        │
 * │           │               │                          │
 * │           │ element: "apple"                         │
 * │           │ before: header                           │
 * │           │ after: Entry("banana")                   │
 * │           │ next: null (в бакете один)               │
 * │                                                      │
 * │  ⇄ Entry("cherry") ⇄ tail                            │
 * │    │                                                 │
 * │    │ element: "cherry"                               │
 * │    │ before: Entry("banana")                         │
 * │    │ after: tail                                     │
 * │    │ next: null                                      │
 * │                                                      │
 * └──────────────────────────────────────────────────────┘
 *
 * ОПЕРАЦИЯ add("date"):
 *
 * Шаг 1: Вычисляем hash("date") → определяем бакет[7]
 * Шаг 2: Проверяем на дубликат → не найден
 * Шаг 3: Добавляем в бакет[7]
 * Шаг 4: Добавляем в конец двусвязного списка:
 *        cherry.after = date
 *        date.before = cherry
 *        date.after = tail
 *        tail.before = date
 *
 * Результат:
 * header ⇄ "apple" ⇄ "banana" ⇄ "cherry" ⇄ "date" ⇄ tail
 *
 * =====================================
 * ГДЕ ИСПОЛЬЗОВАТЬ LinkedHashSet?
 * =====================================
 *
 * ✓ ПОДХОДИТ ДЛЯ:
 * 1. Удаление дубликатов С СОХРАНЕНИЕМ ПОРЯДКА
 * 2. История действий пользователя (без дубликатов)
 * 3. Кэш с порядком обращения (LRU cache)
 * 4. Список уникальных элементов в порядке появления
 * 5. Очередь уникальных задач
 * 6. Парсинг данных с сохранением порядка
 * 7. Отслеживание посещенных объектов в алгоритмах обхода
 * 8. Реализация Set с предсказуемым порядком итерации
 *
 * ✗ НЕ ПОДХОДИТ ДЛЯ:
 * 1. Когда порядок не важен (используйте HashSet - быстрее)
 * 2. Когда нужна сортировка (используйте TreeSet)
 * 3. Критичная производительность (HashSet чуть быстрее)
 * 4. Ограниченная память (потребляет больше, чем HashSet)
 * 5. Многопоточная среда без синхронизации
 *
 * =====================================
 * ПЛЮСЫ (+):
 * =====================================
 * + СОХРАНЯЕТ ПОРЯДОК ДОБАВЛЕНИЯ элементов (главное преимущество!)
 * + Быстрые операции O(1): add, remove, contains (как HashSet)
 * + Автоматическое удаление дубликатов
 * + Предсказуемый порядок итерации
 * + Поддержка null значения (один элемент)
 * + Простой и понятный API
 * + Идеально для удаления дубликатов с сохранением порядка
 * + Можно преобразовать обратно в List с уникальными элементами
 *
 * =====================================
 * МИНУСЫ (-):
 * =====================================
 * - Потребляет БОЛЬШЕ памяти, чем HashSet (двусвязный список)
 * - Чуть МЕДЛЕННЕЕ, чем HashSet (из-за поддержки ссылок)
 * - НЕ потокобезопасен (требует внешней синхронизации)
 * - Не поддерживает индексный доступ
 * - Требует корректной реализации hashCode() и equals()
 * - Сложнее внутренняя реализация, чем у HashSet
 *
 * =====================================
 * СРАВНЕНИЕ ПРОИЗВОДИТЕЛЬНОСТИ:
 * =====================================
 *
 * Операция         | HashSet | LinkedHashSet | TreeSet
 * -----------------|---------|---------------|----------
 * add()            | O(1)    | O(1)*         | O(log n)
 * remove()         | O(1)    | O(1)*         | O(log n)
 * contains()       | O(1)    | O(1)          | O(log n)
 * Итерация         | O(n)    | O(n)          | O(n)
 * Порядок итерации | Нет     | Да (добавл.)  | Да (сорт.)
 * Память           | Низкая  | Средняя       | Высокая
 *
 * * O(1) в среднем, O(n) в худшем случае (плохая хеш-функция)
 *
 * =====================================
 * ВАЖНЫЕ ЗАМЕЧАНИЯ:
 * =====================================
 *
 * 1. ПОРЯДОК ДОБАВЛЕНИЯ:
 *    LinkedHashSet запоминает ПЕРВОЕ добавление элемента.
 *    Повторное добавление того же элемента НЕ изменяет его позицию!
 *
 *    Пример:
 *    set.add("A");  // позиция 1
 *    set.add("B");  // позиция 2
 *    set.add("A");  // дубликат - НЕ добавится, "A" остается на позиции 1
 *
 * 2. СТОИМОСТЬ ПАМЯТИ:
 *    Каждый элемент хранит 2 дополнительные ссылки (before, after)
 *    Для N элементов: дополнительно ~16N байт (64-bit JVM)
 *
 * 3. ПОТОКОБЕЗОПАСНОСТЬ:
 *    Для многопоточности используйте:
 *    Set<String> syncSet = Collections.synchronizedSet(new LinkedHashSet<>());
 *
 * 4. ПРЕОБРАЗОВАНИЕ:
 *    // Список → LinkedHashSet (удаление дубликатов с сохранением порядка)
 *    List<String> list = Arrays.asList("a", "b", "a", "c");
 *    LinkedHashSet<String> set = new LinkedHashSet<>(list);
 *    // Результат: ["a", "b", "c"] - порядок первого появления
 *
 *    // LinkedHashSet → List (сохраняем порядок)
 *    List<String> newList = new ArrayList<>(set);
 *
 * 5. КОГДА ВЫБИРАТЬ:
 *    HashSet         → нужна максимальная скорость, порядок не важен
 *    LinkedHashSet   → нужен порядок добавления, уникальность
 *    TreeSet         → нужна автоматическая сортировка
 *
 * 6. СРАВНЕНИЕ EQUALS:
 *    При добавлении пользовательских объектов ОБЯЗАТЕЛЬНО
 *    переопределяйте hashCode() и equals():
 *
 *    class Person {
 *        String name;
 *
 *        @Override
 *        public boolean equals(Object o) {
 *            if (this == o) return true;
 *            if (o == null || getClass() != o.getClass()) return false;
 *            Person person = (Person) o;
 *            return Objects.equals(name, person.name);
 *        }
 *
 *        @Override
 *        public int hashCode() {
 *            return Objects.hash(name);
 *        }
 *    }
 */
