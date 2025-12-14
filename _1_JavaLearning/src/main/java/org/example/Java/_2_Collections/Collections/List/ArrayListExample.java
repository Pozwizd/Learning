package org.example.Java._2_Collections.Collections.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Демонстрационный класс, показывающий базовую работу с коллекцией ArrayList в Java.

 * ArrayList — это реализация динамического массива, который является частью Java Collections Framework и реализует интерфейс List.
 * В отличие от обычного массива (String[]), размер ArrayList не фиксирован и может автоматически увеличиваться по мере добавления элементов.

 * НАЧАЛЬНАЯ ЁМКОСТЬ: в Java 8+ при создании ArrayList через конструктор по умолчанию внутренний массив имеет ёмкость 0.
 * При добавлении первого элемента ёмкость автоматически увеличивается до 10. Это ленивая инициализация для экономии памяти.
 * Можно задать свою начальную ёмкость: new ArrayList<>(20) — создаст массив сразу на 20 элементов.

 * Когда вы добавите 11-й элемент (при заполнении всех 10 ячеек), ArrayList снова увеличит ёмкость по формуле:
 * newCapacity = oldCapacity + (oldCapacity >> 1)
 * Это побитовый сдвиг вправо, что эквивалентно делению на 2. Итого:

 * newCapacity = 10 + (10 / 2) = 15


 * Основные свойства ArrayList:
 * - Хранит элементы в порядке добавления (доступ по индексу: 0, 1, 2 и т.д.).
 * - Позволяет хранить дубликаты и значение null.
 * - Обеспечивает быстрый доступ по индексу (метод get), но операции вставки/удаления из середины дороже, чем у связанных списков.
 * - Не является потокобезопасным, поэтому при работе из нескольких потоков нужна дополнительная синхронизация.

 * В этом примере используется ArrayList<String>, то есть список строк.
 */
class ArrayListExample {

    /**
     * Точка входа в программу.
     *
     * Аргументы командной строки (String[] args) в данном примере не используются, но обязательны по сигнатуре метода main.
     */
    public static void main(String[] args) {
        // Создаём пустой список строк.
        // Тип в угловых скобках <String> — это обобщение (generics), которое говорит компилятору, что в этом списке можно хранить только объекты типа String.
        // ВНУТРЕННЕЕ УСТРОЙСТВО: создается пустой массив Object[] elementData с ёмкостью 0. При первом add() вырастет до 10.
        ArrayList<String> myArrayList = new ArrayList<String>();

        // Добавляем элементы в конец списка с помощью метода add.
        // Каждый вызов add увеличивает логический размер списка (size) на 1.
        // ПРОИЗВОДИТЕЛЬНОСТЬ: O(1) амортизированно, но O(n) при перераспределении массива.
        // Когда size достигает capacity (например, 10), создается новый массив 15 и копируются ссылки.
        myArrayList.add("Apple");
        myArrayList.add("Banana");
        myArrayList.add("Cherry");
        myArrayList.add("Date");

        // Выводим заголовок, чтобы отделить вывод списка от остального текста.
        System.out.println("My ArrayList:");

        // Цикл for-each (улучшенный for) проходится по всем элементам коллекции myArrayList.
        // На каждой итерации переменная fruit содержит очередной элемент списка типа String.
        // ВНУТРЕННЕЕ УСТРОЙСТВО: компилятор преобразует в итератор. Каждый next() возвращает elementData[index++] — O(1).
        // Iterator проверяет modCount и выбросит ConcurrentModificationException при изменении списка во время итерации.
        for (String fruit : myArrayList) {
            // Печатаем текущий элемент списка.
            System.out.println(fruit);
        }

        // Удаляем элемент "Cherry" из списка с помощью метода remove по значению.
        // Если такой элемент найден, удаляется его первое вхождение, а размер списка уменьшается на 1.
        // ПРОИЗВОДИТЕЛЬНОСТЬ: O(n) — сначала поиск через indexOf() (перебор и equals()),
        // затем System.arraycopy() сдвигает элементы влево, затем обнуляется последняя ячейка для GC.
        myArrayList.remove("Cherry");

        // Метод contains проверяет, есть ли в списке указанный элемент (по equals).
        // Возвращает true, если элемент найден, и false, если его нет.
        // КРИТИЧНО: для объектов собственных классов переопределяйте equals() и hashCode() корректно, иначе будет сравнение по ссылкам!
        if (myArrayList.contains("Banana")) {
            System.out.println("My ArrayList contains Banana");
        }

        // Метод size возвращает текущее количество элементов в списке.
        // ВНУТРЕННЕЕ УСТРОЙСТВО: просто возвращает поле size, не пересчитывает — O(1).
        int size = myArrayList.size();
        System.out.println("My ArrayList size is: " + size);

        // Метод clear удаляет все элементы из списка, но сам объект ArrayList остаётся существовать.
        // ВАЖНО: обнуляет elementData[0..size-1] = null (помогает сборщику мусора), но ЁМКОСТЬ ОСТАЁТСЯ ПРЕЖНЕЙ!
        // Если нужно освободить память, вызовите trimToSize() после clear.
        myArrayList.clear();

        // Метод isEmpty возвращает true, если список пуст (size == 0), и false в противном случае.
        // ВНУТРЕННЕЕ УСТРОЙСТВО: реализация — return size == 0; — O(1).
        if (myArrayList.isEmpty()) {
            System.out.println("My ArrayList is empty");
        }

        // ===== ДОПОЛНИТЕЛЬНЫЕ ПРИМЕРЫ ПРОДВИНУТОГО ИСПОЛЬЗОВАНИЯ =====

        // Пример 1: Предустановка ёмкости для оптимизации
        // Если заранее известен размер, избегаем перераспределений
        // Начальная ёмкость 1000 вместо стандартных 0→10→15→22→... (при росте)
        ArrayList<String> optimizedList = new ArrayList<>(1000);

        // Пример 2: Пакетное добавление (гораздо эффективнее N отдельных add())
        ArrayList<String> batchList = new ArrayList<>();
        batchList.add("X");
        batchList.add("Y");
        myArrayList.addAll(batchList); // Добавляет коллекцию за один вызов — O(n)

        // Пример 3: Потокобезопасная обёртка (критично для микросервисов!)
        // Никогда не передавайте ArrayList в несколько потоков без синхронизации
        List<String> threadSafeList = Collections.synchronizedList(new ArrayList<>());

        // Пример 4: Уменьшение ёмкости после массового удаления
        // Если вы создали ArrayList на 10000 элементов, удалили 99000 — память не освободится!
        ArrayList<String> largeList = new ArrayList<>(10000);
        // ... добавляем много элементов ...
        largeList.clear(); // size=0, но capacity=10000 (массив все ещё в памяти!)
        largeList.trimToSize(); // Теперь capacity=0, массив уменьшен — память освобождена

        // Пример 5: SubList (возвращает представление, а не копию!)
        // Все изменения в subList отражаются в оригинале и наоборот
        ArrayList<String> original = new ArrayList<>();
        original.add("A"); original.add("B"); original.add("C"); original.add("D");
        List<String> subList = original.subList(1, 3); // ["B", "C"]
        subList.set(0, "Z"); // original теперь ["A", "Z", "C", "D"] — ВНИМАНИЕ!

        // Пример 6: Правильное удаление во время итерации (не через for-each!)
        ArrayList<String> modifiableList = new ArrayList<>();
        modifiableList.add("1"); modifiableList.add("2"); modifiableList.add("3");

        // НЕПРАВИЛЬНО: for-each + remove() вызовет ConcurrentModificationException
        // ПРАВИЛЬНО: используем итератор
        Iterator<String> it = modifiableList.iterator();
        while (it.hasNext()) {
            String item = it.next();
            if (item.equals("2")) {
                it.remove(); // Безопасное удаление через итератор
            }
        }

        // Пример 7: Преобразование в массив
        String[] array = myArrayList.toArray(new String[0]); // Указать тип массива

        // Пример 8: Batch-удаление через removeAll (O(n*m) — использует contains())
        ArrayList<String> toRemove = new ArrayList<>();
        toRemove.add("Apple");
        myArrayList.removeAll(toRemove); // Удалит все вхождения — внимание на производительность!
    }
}
