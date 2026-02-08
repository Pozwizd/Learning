package hw2.Task;

/*
Проверить скорость работы основных коллекций интерфейса List(ArrayList, LinkedList).
Создайте экземпляры этих коллекций с 1 000 000 элементов.
Заполните их случайными числами.
Проверьте скорость работы основных операций каждой коллекции:
    добавление в конец списка, добавление в первую половину списка,
    получение элемента из конца списка, получение элемента из первой половины списка,
    удаление элемента из конца списка,
    удаление элемента из первой половины списка.

 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Task5 {

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        fillList(arrayList);
        fillList(linkedList);

        long start, end;

        start = System.nanoTime();
        arrayList.add(1000000);
        end = System.nanoTime();
        System.out.println("ArrayList: добавление в конец списка - " + (end - start) + " мс");

        start = System.nanoTime();
        linkedList.add(1000000);
        end = System.nanoTime();
        System.out.println("LinkedList: добавление в конец списка - " + (end - start) + " мс");

        start = System.nanoTime();
        arrayList.add(500000, 1000000);
        end = System.nanoTime();
        System.out.println("ArrayList: добавление в первую половину списка - " + (end - start) + " мс");

        start = System.nanoTime();
        linkedList.add(500000, 1000000);
        end = System.nanoTime();
        System.out.println("LinkedList: добавление в первую половину списка - " + (end - start) + " мс");

        start = System.nanoTime();
        arrayList.get(999999);
        end = System.nanoTime();
        System.out.println("ArrayList: получение элемента из конца списка - " + (end - start) + " мс");

        start = System.nanoTime();
        linkedList.get(999999);
        end = System.nanoTime();
        System.out.println("LinkedList: получение элемента из конца списка - " + (end - start) + " мс");

        start = System.nanoTime();
        arrayList.get(499999);
        end = System.nanoTime();
        System.out.println("ArrayList: получение элемента из первой половины списка - " + (end - start) + " мс");

        start = System.nanoTime();
        linkedList.get(499999);
        end = System.nanoTime();
        System.out.println("LinkedList: получение элемента из первой половины списка - " + (end - start) + " мс");

        start = System.nanoTime();
        arrayList.remove(999999);
        end = System.nanoTime();
        System.out.println("ArrayList: удаление элемента из конца списка - " + (end - start) + " мс");

        start = System.nanoTime();
        linkedList.remove(999999);
        end = System.nanoTime();
        System.out.println("LinkedList: удаление элемента из конца списка - " + (end - start) + " мс");

        start = System.nanoTime();
        arrayList.remove(499999);
        end = System.nanoTime();
        System.out.println("ArrayList: удаление элемента из первой половины списка - " + (end - start) + " мс");

        start = System.nanoTime();
        linkedList.remove(499999);
        end = System.nanoTime();
        System.out.println("LinkedList: удаление элемента из первой половины списка - " + (end - start) + " мс");
    }

    static void fillList(List<Integer> list) {
        Random rand = new Random();
        for (int i = 0; i < 1000000; i++) {
            list.add(rand.nextInt(1000000));
        }
    }
}
