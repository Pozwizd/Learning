package org.example.Java._2_Collections.Collections.List;

import java.util.LinkedList;

/**
 * Демонстрационный класс, показывающий базовую работу с коллекцией LinkedList в Java.[web:25]
 *
 * LinkedList — это реализация двусвязного списка, который является частью Java Collections Framework
 * и реализует интерфейсы List и Deque (двусторонняя очередь).[web:25]
 *
 * Внутри LinkedList каждый элемент хранится в отдельном узле (node), который содержит:
 * - само значение (данные);
 * - ссылку на предыдущий элемент;
 * - ссылку на следующий элемент.[web:36]
 *
 * Это отличается от ArrayList, который основан на динамическом массиве, где элементы лежат подряд в памяти.[web:30]
 *
 * Основные свойства LinkedList:[web:28]
 * - Хранит элементы в порядке добавления и поддерживает индексы (0, 1, 2, ...).[web:28]
 * - Позволяет хранить дубликаты и значение null.[web:33]
 * - Вставки и удаления в начале и в середине списка обычно дешевле, чем у ArrayList,
 *   потому что не нужно сдвигать остальные элементы — достаточно перекинуть ссылки между узлами.[web:28]
 * - Доступ по индексу медленнее, чем у ArrayList (чтобы добраться до элемента, нужно пройти по ссылкам от начала или конца).[web:28]
 * - Не является потокобезопасным, как и ArrayList.[web:28]
 *
 * У LinkedList есть дополнительные удобные методы работы с началом и концом списка:
 * addFirst, addLast, removeFirst, removeLast, getFirst, getLast и др.[web:25]
 */
public class LinkedListExample {

    /**
     * Точка входа в программу.[web:27]
     *
     * В этом методе демонстрируются основные операции:
     * - создание LinkedList;
     * - добавление элементов в конец списка (add);
     * - обход списка в цикле for-each;
     * - добавление элемента в начало (addFirst);
     * - удаление первого элемента (removeFirst).[web:27]
     */
    public static void main(String[] args) {
        // Создаём пустой LinkedList, который будет хранить строки.[web:27]
        // В угловых скобках <String> указан тип элементов (generics),
        // это значит, что список может хранить только объекты типа String.[web:33]
        LinkedList<String> myList = new LinkedList<>();

        // Добавляем элементы в конец списка с помощью метода add.[web:35]
        // LinkedList при этом создаёт новые узлы и «цепляет» их в конец двусвязного списка.[web:36]
        myList.add("apple");
        myList.add("banana");
        myList.add("cherry");

        // Цикл for-each проходит по всем элементам списка от первого до последнего.[web:27]
        // На каждой итерации переменная fruit содержит текущий элемент типа String.[web:27]
        for (String fruit : myList) {
            System.out.println(fruit);
        }

        // addFirst добавляет новый элемент в самое начало списка.[web:25]
        // Для двусвязного списка это делается быстро: создаётся новый узел и перенастраиваются ссылки.[web:36]
        myList.addFirst("orange");

        System.out.println("After adding element at the beginning:");

        // Снова печатаем все элементы списка, чтобы увидеть, что "orange" стал первым.[web:27]
        for (String fruit : myList) {
            System.out.println(fruit);
        }

        // removeFirst удаляет первый элемент списка и возвращает его значение.[web:24]
        // Если список пуст, вызов removeFirst() выбросит исключение NoSuchElementException.[web:24]
        myList.removeFirst();

        System.out.println("After removing first element:");

        // Ещё раз выводим список, чтобы показать, что первый элемент был удалён.[web:27]
        for (String fruit : myList) {
            System.out.println(fruit);
        }
    }
}
