package org.example.Java._2_Collections.Collections.Queue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Демонстрационный класс, показывающий работу с LinkedBlockingDeque и интерфейсом BlockingDeque.[web:276][web:281]

 * BlockingDeque — это двусторонняя блокирующая очередь (deque = double-ended queue).[web:277][web:283][web:284]
 * Особенности BlockingDeque:[web:281][web:283][web:288]
 * - можно добавлять и забирать элементы как с головы (first), так и с хвоста (last);[web:283]
 * - поддерживает блокирующие операции: putFirst/putLast, takeFirst/takeLast (ждут при отсутствии места/элементов);[web:281][web:283]
 * - потокобезопасна: корректно работает при доступе из нескольких потоков.[web:281][web:284]

 * LinkedBlockingDeque — основная реализация BlockingDeque.[web:278][web:285][web:291]
 * Характеристики LinkedBlockingDeque:[web:278][web:279][web:201]
 * - основана на двусвязном списке узлов (linked nodes);[web:276][web:278]
 * - может быть ограниченной (bounded) при указании capacity в конструкторе или практически неограниченной по умолчанию;[web:279][web:285][web:291]
 * - вставка и удаление происходят с блокировкой при переполнении/опустошении (put/take* блокируются).[web:276][web:281][web:283]

 * Типичные сценарии:[web:281][web:283][web:201]
 * - очереди задач, где удобно работать и с головой, и с хвостом (например, реализация work‑stealing или LIFO/FIFO‑сочетаний);[web:283]
 * - producer–consumer с возможностью добавлять «срочные» задачи в начало очереди.[web:201][web:278]
 */
        public class LinkedBlockingDequeExample {

            public static void main(String[] args) throws InterruptedException {
                // Создаём BlockingDeque с реализацией LinkedBlockingDeque и ёмкостью 3.[web:276][web:278][web:279]
                BlockingDeque<String> deque = new LinkedBlockingDeque<>(3);

                // Поток‑производитель №1: добавляет элементы в хвост (обычная очередь FIFO).[web:276][web:281]
                Runnable tailProducer = () -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " пытается добавить в хвост: A");
                        deque.putLast("A"); // блокируется, если нет места.[web:281][web:283]
                        System.out.println(Thread.currentThread().getName() + " добавил в хвост: A");

                        System.out.println(Thread.currentThread().getName() + " пытается добавить в хвост: B");
                        deque.putLast("B");
                        System.out.println(Thread.currentThread().getName() + " добавил в хвост: B");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                };

                // Поток‑производитель №2: добавляет «срочные» задачи в голову.[web:281][web:283][web:201]
                Runnable headProducer = () -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " пытается добавить в голову: URGENT‑1");
                        deque.putFirst("URGENT‑1"); // добавление в голову; блокируется при переполнении.[web:281]
                        System.out.println(Thread.currentThread().getName() + " добавил в голову: URGENT‑1");

                        System.out.println(Thread.currentThread().getName() + " пытается добавить в голову: URGENT‑2");
                        // При capacity = 3 после трёх вставок deque будет полон,
                        // поэтому этот putFirst заблокируется, пока consumer не освободит место.[web:276][web:281][web:283]
                        deque.putFirst("URGENT‑2");
                        System.out.println(Thread.currentThread().getName() + " добавил в голову: URGENT‑2");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                };

                // Поток‑потребитель: забирает элементы с головы в порядке, который сформировался.[web:283][web:201]
                Runnable consumer = () -> {
                    try {
                        Thread.sleep(500); // даём продюсерам шанс «упереться» в capacity.[web:201]

                        for (int i = 0; i < 4; i++) {
                            // takeFirst забирает элемент с головы и блокируется при пустой очереди.[web:281][web:283]
                            String value = deque.takeFirst();
                            System.out.println(Thread.currentThread().getName() +
                                    " забрал с головы (takeFirst): " + value);
                            Thread.sleep(400); // имитация обработки.[web:283]
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                };

                Thread t1 = new Thread(tailProducer, "TailProducer");
                Thread t2 = new Thread(headProducer, "HeadProducer");
                Thread consumerThread = new Thread(consumer, "Consumer");

                t1.start();
                t2.start();
                consumerThread.start();

                t1.join();
                t2.join();
                consumerThread.join();

                System.out.println("Конечное состояние deque: " + deque + ", size=" + deque.size()); // ожидаем 0.[web:280][web:201]]
            }
        }
