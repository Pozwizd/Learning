package org.example.Java._2_Collections.Collections.Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Демонстрационный класс, показывающий работу с SynchronousQueue.[web:221][web:223]
 *
 * SynchronousQueue — это особый вид BlockingQueue, у которого НЕТ внутренней ёмкости (zero capacity).[web:222][web:224][web:233]
 * Внутри очередь никогда не хранит элементы: каждый put/offer должен сразу «встретиться» с take/poll в другом потоке.[web:222][web:226][web:230]
 *
 * Ключевые свойства SynchronousQueue:[web:224][web:229][web:233][web:196]
 * - Каждая операция вставки (put/offer) блокируется, пока другой поток не выполнит соответствующую операцию извлечения (take/poll) и наоборот.[web:221][web:226][web:230][web:196]
 * - Нет внутреннего буфера: элемент существует «в очереди» только в момент передачи между потоками.[web:222][web:228][web:233]
 * - Нельзя сделать peek: метод peek всегда возвращает null, так как элемент не хранится.[web:229][web:233][web:235]
 * - Методы size(), isEmpty(), contains() ведут себя так, как будто коллекция почти всегда пуста (size обычно 0).[web:233][web:235][web:238]
 * - Не допускает null‑элементы.[web:235][web:236]
 *
 * Типичный сценарий использования — прямой «handoff» задач или событий от producer к consumer,
 * когда производитель не должен «складировать» задачи, а должен синхронизироваться с потребителем.[web:177][web:226][web:228][web:230]
 */
public class SynchronousQueueExample {

    public static void main(String[] args) throws InterruptedException {
        // Работаем через интерфейс BlockingQueue, указывая реализацию SynchronousQueue.[web:221][web:224]
        // По умолчанию используется несправедливая политика доступа (non‑fair): порядок пробуждения ожидающих потоков не гарантирован.[web:224][web:228][web:233]
        BlockingQueue<String> syncQueue = new SynchronousQueue<>();

        // Поток‑производитель: пытается отправить несколько сообщений в очередь.[web:221][web:225][web:228]
        Runnable producer = () -> {
            try {
                for (int i = 1; i <= 3; i++) {
                    String msg = "MSG-" + i; // Формируем текст сообщения.[web:224]
                    System.out.println(Thread.currentThread().getName() +
                            " пытается отправить: " + msg); // Логируем попытку.[web:225]

                    // put блокируется, пока другой поток не выполнит take и не заберёт это сообщение.[web:221][web:224][web:229][web:233]
                    syncQueue.put(msg);

                    System.out.println(Thread.currentThread().getName() +
                            " успешно отправил: " + msg); // Сообщение передано потребителю.[web:225]
                    Thread.sleep(300); // Небольшая пауза между отправками.[web:228]
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Восстанавливаем флаг прерывания.[web:224][web:229]
            }
        };

        // Поток‑потребитель: принимает сообщения из очереди.[web:221][web:225][web:228]
        Runnable consumer = () -> {
            try {
                // Небольшая задержка, чтобы producer успел дойти до блокировки на первом put.[web:225][web:230]
                Thread.sleep(500);

                for (int i = 1; i <= 3; i++) {
                    // take блокируется, пока другой поток не выполнит put и не предложит элемент.[web:224][web:229][web:233]
                    String msg = syncQueue.take();

                    System.out.println(Thread.currentThread().getName() +
                            " получил и обработал: " + msg); // Лог обработки сообщения.[web:228]
                    Thread.sleep(700); // Имитация времени обработки.[web:228]
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Корректное завершение при прерывании.[web:224]
            }
        };

        Thread producerThread = new Thread(producer, "Producer");
        Thread consumerThread = new Thread(consumer, "Consumer");

        producerThread.start(); // Запускаем producer.[web:225]
        consumerThread.start(); // Запускаем consumer.[web:225]

        producerThread.join();  // Ждём завершения producer.[web:228]
        consumerThread.join();  // Ждём завершения consumer.[web:228]

        // Важно: SynchronousQueue почти всегда логически пуста — элементы не накапливаются внутри.[web:222][web:233][web:238]
        System.out.println("size() после обмена: " + syncQueue.size()); // Ожидаемо 0.[web:233][web:235]

        // peek всегда возвращает null, потому что нельзя «подсмотреть» элемент — он не хранится.[web:229][web:233][web:235]
        System.out.println("peek() на SynchronousQueue: " + syncQueue.peek());

        // poll без ожидающего put, как правило, тоже вернёт null (если прямо сейчас нет пары put/take).[web:229][web:196]
        System.out.println("poll() на SynchronousQueue: " + syncQueue.poll());

        // Резюме по сравнению с другими BlockingQueue:
        // - LinkedBlockingQueue/ArrayBlockingQueue могут накапливать элементы во внутреннем буфере.[web:187][web:194][web:190][web:201]
        // - SynchronousQueue вообще не хранит элементы и подходит для сценариев «жёсткого» обмена задачами между потоками без буфера.[web:226][web:228][web:196][web:234]
    }
}
