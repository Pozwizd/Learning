package org.example.Java._2_Collections.Collections.Queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Демонстрационный класс, показывающий работу с ConcurrentLinkedQueue.[web:160]
 *
 * ConcurrentLinkedQueue — это неблокирующая, потокобезопасная реализация очереди FIFO.[web:162][web:163]
 * Основные характеристики:[web:177][web:171]
 * - Находится в пакете java.util.concurrent.[web:162]
 * - Потокобезопасна: несколько потоков могут одновременно добавлять и забирать элементы без внешней синхронизации.[web:160][web:178]
 * - Использует неблокирующий алгоритм (lock-free) на основе CAS (compare-and-swap), поэтому операции не блокируют потоки.[web:170][web:173]
 * - Очередь не имеет фиксированного ограничения по размеру (по сути, неограниченная), элементы хранятся в связанном списке узлов.[web:163][web:171]
 * - Порядок — FIFO: элементы извлекаются в том же порядке, в котором были добавлены.[web:163]
 *
 * В отличие от BlockingQueue (LinkedBlockingQueue, ArrayBlockingQueue и т.п.), ConcurrentLinkedQueue:
 * - НЕ блокирует поток при пустой очереди, а просто возвращает null при poll/peek;[web:164][web:170]
 * - хорошо подходит для сценариев с высокой нагрузкой и большим количеством потоков, которым нельзя «ждать» в блокирующих операциях.[web:164][web:168]
 *
 * Этот пример показывает базовые операции (offer, poll, peek, size) без многопоточности,
 * но важно понимать, что реальная сила ConcurrentLinkedQueue раскрывается именно в многопоточной среде.[web:160][web:177]
 */
public class ConcurrentLinkedQueueExample {

    public static void main(String[] args) {
        // Работаем через интерфейс Queue, указывая реализацию ConcurrentLinkedQueue.[web:160]
        Queue<String> queue = new ConcurrentLinkedQueue<>();

        // Добавляем элементы в очередь.[web:160]
        // Методы add/offer в ConcurrentLinkedQueue оба добавляют элемент в «хвост» очереди.[web:163]
        queue.offer("Task 1");
        queue.offer("Task 2");
        queue.offer("Task 3");

        System.out.println("Очередь после добавления элементов: " + queue);

        // peek() — посмотреть элемент с головы очереди, НЕ удаляя его.[web:160][web:163]
        // Если очередь пуста, вернёт null.[web:163]
        String head = queue.peek();
        System.out.println("Голова очереди (peek): " + head);
        System.out.println("Очередь после peek (не изменилась): " + queue);

        // poll() — забрать и удалить элемент с головы очереди.[web:160]
        // Если очередь пуста, вернёт null (в отличие от remove(), который бросает исключение).[web:163]
        String first = queue.poll();
        System.out.println("Извлечён (poll) первый элемент: " + first);
        System.out.println("Очередь после первого poll: " + queue);

        String second = queue.poll();
        System.out.println("Извлечён (poll) второй элемент: " + second);
        System.out.println("Очередь после второго poll: " + queue);

        // Добавим ещё один элемент, чтобы показать, что очередь остаётся работоспособной после операций.[web:160]
        queue.offer("Task 4");
        System.out.println("Очередь после добавления Task 4: " + queue);

        // size() даёт примерное количество элементов.[web:160]
        // В многопоточной среде значение может быть не идеально точным на момент использования,
        // но в однопоточном примере это обычный корректный размер.[web:178][web:171]
        int size = queue.size();
        System.out.println("Текущий размер очереди: " + size);

        // Опустошим очередь полностью.[web:160]
        while (!queue.isEmpty()) {
            System.out.println("poll(): " + queue.poll());
        }
        System.out.println("Очередь после извлечения всех элементов: " + queue);

        // Теперь очередь пуста: peek() и poll() вернут null.[web:163]
        System.out.println("peek() на пустой очереди: " + queue.peek());
        System.out.println("poll() на пустой очереди: " + queue.poll());

        // В реальной многопоточной задаче можно представить:
        // - несколько «производителей» (producer), которые добавляют задачи в очередь с помощью offer/add;[web:159][web:164]
        // - несколько «потребителей» (consumer), которые забирают задачи через poll в цикле, пока очередь не опустеет.[web:159]
        // ConcurrentLinkedQueue внутри сама обеспечивает корректное взаимодействие между потоками без явных synchronized/lock.[web:162][web:170][web:178]8]
    }
}
