package org.example.Java._2_Collections.Collections.Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Демонстрационный класс, показывающий работу с LinkedBlockingQueue и интерфейсом BlockingQueue.[web:187][web:193]
 *
 * BlockingQueue — это специализированная очередь, которая поддерживает блокирующие операции.[web:188][web:195]
 * Основные особенности BlockingQueue:[web:188][web:197]
 * - методы вставки (put/offer) могут ждать, пока освободится место, если очередь заполнена;[web:188][web:190]
 * - методы извлечения (take/poll с таймаутом) могут ждать появления элемента, если очередь пуста;[web:188][web:193]
 * - интерфейс используется для реализации потокобезопасного обмена данными между потоками по паттерну producer–consumer.[web:179][web:186]
 *
 * LinkedBlockingQueue — это потокобезопасная реализация BlockingQueue на базе связанного списка узлов.[web:187][web:194][web:196]
 * Характеристики LinkedBlockingQueue:[web:187][web:191]
 * - порядок элементов FIFO (First In, First Out);[web:187][web:196]
 * - может быть ограниченной (bounded), если задать capacity в конструкторе, или почти неограниченной (по умолчанию до Integer.MAX_VALUE);[web:187][web:194]
 * - все операции потокобезопасны, внутри используются блокировки и условия (locks/conditions);[web:191][web:196]
 * - отлично подходит для реализации очереди задач между производителями (producers) и потребителями (consumers).[web:179][web:186][web:197]
 *
 * В этом учебном примере:[web:179][web:186]
 * - создаётся LinkedBlockingQueue с маленькой ёмкостью (2), чтобы было видно эффект блокировок;[web:187][web:196]
 * - один поток-производитель добавляет задачи с помощью put (может блокироваться, если очередь полна);[web:188][web:190]
 * - один поток-потребитель забирает задачи с помощью take (блокируется, если очередь пуста).[web:188][web:193]
 */
public class LinkedBlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        // Создаём BlockingQueue с реализацией LinkedBlockingQueue.[web:187][web:191]
        // Ёмкость 2 делает очередь ограниченной: одновременно внутри может быть не больше двух элементов.[web:187][web:194][web:196]
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(2);

        // Поток-производитель (producer): кладёт в очередь несколько задач.[web:179][web:186]
        Runnable producer = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " пытается добавить Task 1");
                // put добавляет элемент, блокируясь, если очередь заполнена, пока не освободится место.[web:188][web:190]
                queue.put("Task 1");
                System.out.println(Thread.currentThread().getName() + " добавил Task 1");

                System.out.println(Thread.currentThread().getName() + " пытается добавить Task 2");
                queue.put("Task 2");
                System.out.println(Thread.currentThread().getName() + " добавил Task 2");

                System.out.println(Thread.currentThread().getName() + " пытается добавить Task 3");
                // На этом шаге, если потребитель ещё ничего не забрал, очередь уже полна (2 элемента),
                // поэтому put("Task 3") заблокирует поток до тех пор, пока потребитель не освободит место.[web:188][web:190][web:193]
                queue.put("Task 3");
                System.out.println(Thread.currentThread().getName() + " добавил Task 3");
            } catch (InterruptedException e) {
                // Если поток прервали во время блокирующей операции put, ловим InterruptedException.[web:188][web:193]
                Thread.currentThread().interrupt();
            }
        };

        // Поток-потребитель (consumer): забирает задачи из очереди.[web:179][web:186]
        Runnable consumer = () -> {
            try {
                // Небольшая пауза, чтобы дать производителю шанс «забиться» в заполненную очередь.[web:179]
                Thread.sleep(500);

                // take забирает и удаляет элемент с головы очереди, блокируясь, если очередь пуста,
                // пока не появится новый элемент.[web:188][web:190][web:193]
                String task1 = queue.take();
                System.out.println(Thread.currentThread().getName() + " обработал: " + task1);

                Thread.sleep(500); // имитация обработки задачи.[web:179]

                String task2 = queue.take();
                System.out.println(Thread.currentThread().getName() + " обработал: " + task2);

                Thread.sleep(500); // имитация обработки.[web:179]

                String task3 = queue.take();
                System.out.println(Thread.currentThread().getName() + " обработал: " + task3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Создаём и запускаем потоки.[web:179][web:182]
        Thread producerThread = new Thread(producer, "Producer");
        Thread consumerThread = new Thread(consumer, "Consumer");

        producerThread.start();
        consumerThread.start();

        // Ждём завершения обоих потоков, чтобы main не завершился раньше.[web:179][web:186]
        producerThread.join();
        consumerThread.join();

        System.out.println("Все задачи обработаны, очередь пуста: " + queue);
    }
}
