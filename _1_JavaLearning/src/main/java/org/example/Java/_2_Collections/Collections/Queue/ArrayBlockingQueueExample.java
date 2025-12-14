package org.example.Java._2_Collections.Collections.Queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Демонстрационный класс, показывающий работу с ArrayBlockingQueue и интерфейсом BlockingQueue.[web:201]
 *
 * ArrayBlockingQueue — это блокирующая очередь фиксированной ёмкости, реализованная на массиве.[web:201]
 * Основные характеристики:[web:190]
 * - FIFO‑очередь (First In, First Out); элементы извлекаются в порядке добавления.[web:190]
 * - Размер задаётся в конструкторе и не меняется (очередь «ограниченная» — bounded). Если очередь полна, новые элементы временно не могут быть добавлены.[web:201]
 * - Потокобезопасна: все операции корректно работают при доступе из нескольких потоков.[web:190]
 * - Использует блокировки/условия внутри, чтобы реализовать блокирующие операции put/take.[web:190]
 *
 * Отличия от LinkedBlockingQueue:[web:191]
 * - ArrayBlockingQueue использует массив фиксированного размера, LinkedBlockingQueue — связанный список (может иметь очень большую ёмкость).[web:187]
 * - У ArrayBlockingQueue есть опциональная «справедливость» (fairness): при true потоки обслуживаются в порядке очереди ожидания, что уменьшает голодание (starvation).[web:168]
 *
 * В этом примере:[web:201]
 * - создаётся ArrayBlockingQueue ёмкостью 2;[web:201]
 * - один поток‑производитель (Producer) кладёт строки в очередь через put (может блокироваться, если очередь полна);[web:190]
 * - один поток‑потребитель (Consumer) забирает строки через take (блокируется, если очередь пуста).[web:190]
 */
public class ArrayBlockingQueueExample {

    public static void main(String[] args) throws InterruptedException { // main тоже может быть прерван при join, поэтому пробрасываем InterruptedException.[web:190]
        // Создаём BlockingQueue с реализацией ArrayBlockingQueue.[web:201]
        // Ёмкость 2 означает, что одновременно в очереди может находиться максимум 2 элемента.[web:201]
        // Второй параметр (fair = true) включает «справедливую» политику: потоки будут обслуживаться примерно в порядке ожидания.[web:201][web:168]
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(2, true);

        // Поток‑производитель: кладёт несколько сообщений в очередь.[web:179]
        Runnable producer = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " пытается добавить: MSG‑1"); // Лог печати попытки.[web:179]
                // put блокируется, если очередь полна, пока потребитель не освободит место.[web:190]
                queue.put("MSG‑1");
                System.out.println(Thread.currentThread().getName() + " добавил: MSG‑1"); // Успешная вставка.[web:179]

                System.out.println(Thread.currentThread().getName() + " пытается добавить: MSG‑2"); // Вторая попытка добавления.[web:179]
                queue.put("MSG‑2");
                System.out.println(Thread.currentThread().getName() + " добавил: MSG‑2"); // Теперь очередь заполнена (2 элемента).[web:190]

                System.out.println(Thread.currentThread().getName() + " пытается добавить: MSG‑3"); // Третья вставка при заполненной очереди.[web:201]
                // На этом вызове поток будет ждать, пока Consumer не заберёт хотя бы один элемент.[web:190]
                queue.put("MSG‑3");
                System.out.println(Thread.currentThread().getName() + " добавил: MSG‑3"); // Сообщение будет выведено только после освобождения места.[web:190]
            } catch (InterruptedException e) {
                // Если поток прервали во время put, восстановим статус прерывания.[web:190]
                Thread.currentThread().interrupt();
            }
        };

        // Поток‑потребитель: забирает элементы из очереди и «обрабатывает» их.[web:179]
        Runnable consumer = () -> {
            try {
                // Небольшая задержка, чтобы producer успел заполнить очередь и попасть на блокировку при третьем элементе.[web:179]
                Thread.sleep(500);

                // take забирает элемент из головы очереди.[web:190]
                // Если очередь пуста, take блокируется, пока не появится элемент.[web:190]
                String msg1 = queue.take();
                System.out.println(Thread.currentThread().getName() + " обработал: " + msg1); // Имитация обработки первого сообщения.[web:179]

                Thread.sleep(700); // Пауза, чтобы producer мог продолжить работу.[web:179]

                String msg2 = queue.take();
                System.out.println(Thread.currentThread().getName() + " обработал: " + msg2); // Обработка второго сообщения.[web:179]

                Thread.sleep(700); // Ещё пауза.[web:179]

                String msg3 = queue.take();
                System.out.println(Thread.currentThread().getName() + " обработал: " + msg3); // Обработка третьего сообщения.[web:179]
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Восстановление статуса прерывания.[web:190]
            }
        };

        // Создаём и запускаем потоки.[web:179]
        Thread producerThread = new Thread(producer, "Producer");
        Thread consumerThread = new Thread(consumer, "Consumer");

        producerThread.start(); // Запускаем производителя.[web:179]
        consumerThread.start(); // Запускаем потребителя.[web:179]

        // Ждём завершения обоих потоков, чтобы main не завершился раньше.[web:179]
        producerThread.join();
        consumerThread.join();

        // После обработки всех сообщений очередь должна быть пуста.[web:190]
        System.out.println("Все сообщения обработаны, очередь: " + queue); // Выводим финальное состояние очереди.[web:201]
    }
}
