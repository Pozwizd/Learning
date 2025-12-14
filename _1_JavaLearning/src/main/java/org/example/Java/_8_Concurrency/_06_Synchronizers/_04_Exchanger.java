package org.example.Java._8_Concurrency._06_Synchronizers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * Примеры использования java.util.concurrent.Exchanger:
 * - парный обмен данными между двумя потоками (producer/consumer)
 * - "двунаправленная" ручная передача буферов (double buffering)
 * - демонстрация блокировки до появления пары и тайминга обмена
 *
 * Запускайте и смотрите порядок вывода.
 */
public class _04_Exchanger {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Example 1: простой обмен строками ===");
        simpleStringExchange();

        System.out.println("\n=== Example 2: обмен изменяемыми буферами (double buffering) ===");
        doubleBufferingExchange();
    }

    /**
     * Два потока обмениваются строками. Каждый вызов exchange блокируется, пока пара не вызовет exchange.
     */
    private static void simpleStringExchange() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread t1 = new Thread(() -> {
            try {
                String myData = "Hello from T1";
                System.out.println(ts() + " [T1] prepared: " + myData);
                // Пауза, чтобы показать, что второй поток блокируется до прихода пары
                sleep(300);
                String received = exchanger.exchange(myData);
                System.out.println(ts() + " [T1] received: " + received);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "exch-t1");

        Thread t2 = new Thread(() -> {
            try {
                String myData = "Hello from T2";
                System.out.println(ts() + " [T2] prepared: " + myData);
                String received = exchanger.exchange(myData); // будет ждать, пока T1 вызовет exchange
                System.out.println(ts() + " [T2] received: " + received);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "exch-t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    /**
     * Producer и Consumer обмениваются буферами. Producer заполняет, затем отдает bufferA и забирает пустой,
     * Consumer забирает заполненный, "обрабатывает" и возвращает пустой — типичный паттерн double buffering.
     */
    private static void doubleBufferingExchange() throws InterruptedException {
        Exchanger<List<Integer>> exchanger = new Exchanger<>();

        // Начальные буферы
        List<Integer> emptyA = new ArrayList<>();
        List<Integer> emptyB = new ArrayList<>();

        Thread producer = new Thread(() -> runProducer(exchanger, emptyA), "producer");
        Thread consumer = new Thread(() -> runConsumer(exchanger, emptyB), "consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

    private static void runProducer(Exchanger<List<Integer>> exchanger, List<Integer> buffer) {
        try {
            for (int round = 1; round <= 3; round++) {
                buffer.clear();
                // Заполняем буфер "порцией данных"
                for (int i = 0; i < 5; i++) {
                    int val = round * 10 + i;
                    buffer.add(val);
                    System.out.println(ts() + " [Producer] add " + val);
                    sleep(50);
                }
                System.out.println(ts() + " [Producer] buffer ready, exchanging...");
                // Передаем заполненный буфер и получаем пустой для следующего цикла
                buffer = exchanger.exchange(buffer);
                System.out.println(ts() + " [Producer] got buffer size " + buffer.size());
            }
            System.out.println(ts() + " [Producer] done");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void runConsumer(Exchanger<List<Integer>> exchanger, List<Integer> buffer) {
        try {
            for (int round = 1; round <= 3; round++) {
                System.out.println(ts() + " [Consumer] waiting for data...");
                // Получаем заполненный буфер и отдаём свой текущий (пустой)
                buffer = exchanger.exchange(buffer);
                System.out.println(ts() + " [Consumer] received size " + buffer.size());
                // Обрабатываем элементы
                for (Integer x : buffer) {
                    System.out.println(ts() + " [Consumer] process " + x);
                    sleep(80);
                }
                buffer.clear(); // очищаем перед возвратом producer-у
                System.out.println(ts() + " [Consumer] buffer processed, will return empty");
            }
            System.out.println(ts() + " [Consumer] done");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void sleep(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static String ts() {
        return java.time.LocalTime.now().toString();
    }
}