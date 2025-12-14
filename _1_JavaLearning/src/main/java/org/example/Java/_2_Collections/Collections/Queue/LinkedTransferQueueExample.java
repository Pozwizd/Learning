package org.example.Java._2_Collections.Collections.Queue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Демонстрационный класс, показывающий работу с LinkedTransferQueue и интерфейсом TransferQueue.[web:257][web:261][web:262]
 *
 * TransferQueue — это расширение BlockingQueue, в котором продюсер может **дождаться**,
 * пока элемент будет не просто добавлен в очередь, а реально получен потребителем.[web:262][web:265][web:273]
 *
 * LinkedTransferQueue — единственная стандартная реализация TransferQueue.[web:261][web:266][web:267]
 * Основные характеристики:[web:259][web:261][web:196]
 * - неограниченная (unbounded) очередь на связанных узлах;[web:259][web:261]
 * - порядок для одного продюсера — FIFO, но при множестве продюсеров глобальный порядок может быть сложнее;[web:259][web:266]
 * - потокобезопасна и высокопроизводительна, сочетает свойства LinkedBlockingQueue и SynchronousQueue
 *   (может и накапливать элементы, и делать прямой handoff через transfer).[web:196][web:263][web:268]
 *
 * Дополнительные методы TransferQueue:[web:262][web:265][web:273]
 * - transfer(E e) — блокирует продюсера, пока какой‑то потребитель не заберёт этот элемент (через take/poll с ожиданием);[web:259][web:262][web:265]
 * - tryTransfer(E e) — пытается немедленно передать элемент ожидающему потребителю, не блокируясь; если потребителя нет — возвращает false;[web:262][web:265]
 * - tryTransfer(E e, long timeout, TimeUnit unit) — ждёт ограниченное время, пытаясь передать элемент потребителю;[web:259][web:265]
 * - hasWaitingConsumer(), getWaitingConsumerCount() — позволяют узнать, есть ли потоки, уже ожидающие элементы.[web:259][web:261]
 *
 * Типичный сценарий — очереди сообщений/задач, где иногда важен сам факт "доставки" (producer ждёт consumer),
 * а иногда — просто сложить элементы в очередь, как в обычной BlockingQueue.[web:263][web:196][web:217]
 */
public class LinkedTransferQueueExample {

    public static void main(String[] args) throws InterruptedException {
        // Работаем через интерфейс TransferQueue, используя реализацию LinkedTransferQueue.[web:261][web:262][web:275]
        TransferQueue<String> queue = new LinkedTransferQueue<>();

        // Поток‑потребитель: забирает сообщения из очереди через take().[web:261][web:262]
        Runnable consumer = () -> {
            try {
                // Небольшая задержка, чтобы producer успел дойти до transfer и там заблокироваться.[web:261]
                Thread.sleep(500);

                // take() забирает элемент, блокируясь, если очередь пуста;[web:259][web:262]
                // при этом разблокирует любой ожидающий transfer(...).[web:259][web:264][web:268]
                String msg1 = queue.take();
                System.out.println(Thread.currentThread().getName() +
                        " получил (take): " + msg1);

                Thread.sleep(500);

                // Следующие элементы могут быть уже просто в очереди (после add/put/offer).[web:257][web:261]
                String msg2 = queue.take();
                System.out.println(Thread.currentThread().getName() +
                        " получил (take): " + msg2);

                String msg3 = queue.take();
                System.out.println(Thread.currentThread().getName() +
                        " получил (take): " + msg3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Поток‑производитель: демонстрирует разницу между transfer и обычным добавлением.[web:257][web:262][web:261]
        Runnable producer = () -> {
            try {
                // 1) Прямой handoff через transfer.[web:259][web:262]
                // Если сейчас нет потребителя, ожидающего take/poll с таймаутом, поток заблокируется,
                // пока consumer не вызовет take() и не заберёт этот элемент.[web:259][web:262][web:265]
                System.out.println(Thread.currentThread().getName() +
                        " вызывает transfer(\"IMPORTANT‑MSG\") и ждёт потребителя...");
                queue.transfer("IMPORTANT‑MSG");
                System.out.println(Thread.currentThread().getName() +
                        " убедился, что \"IMPORTANT‑MSG\" получено потребителем.");

                // 2) Обычное добавление без ожидания.[web:262][web:275]
                // Эти элементы просто будут лежать в очереди, пока кто‑то их не заберёт.[web:257][web:261]
                queue.put("MSG‑2 (via put)");
                System.out.println(Thread.currentThread().getName() +
                        " добавил: MSG‑2 (via put)");

                queue.offer("MSG‑3 (via offer)");
                System.out.println(Thread.currentThread().getName() +
                        " добавил: MSG‑3 (via offer)");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread consumerThread = new Thread(consumer, "Consumer");
        Thread producerThread = new Thread(producer, "Producer");

        consumerThread.start();
        producerThread.start();

        producerThread.join();
        consumerThread.join();

        // После того как consumer забрал все 3 сообщения, очередь должна быть пуста.[web:261][web:196]
        System.out.println("Очередь после обмена: " + queue + ", size=" + queue.size());
    }
}
