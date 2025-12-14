package org.example.Java._2_Collections.Collections.Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Демонстрационный класс, показывающий работу с PriorityBlockingQueue.[web:207][web:210]
 *
 * PriorityBlockingQueue — это потокобезопасная, блокирующая очередь,
 * которая упорядочивает элементы по приоритету, а не по порядку вставки.[web:209][web:210][web:196]
 *
 * Характеристики PriorityBlockingQueue:[web:207][web:210][web:196]
 * - реализует интерфейс BlockingQueue (поддерживает блокирующие операции take/poll с таймаутом);[web:197][web:210]
 * - использует те же правила упорядочивания, что и PriorityQueue: по "естественному порядку" (Comparable)
 *   или по Comparator, переданному в конструктор;[web:209][web:210][web:212]
 * - основана на куче (min‑heap) в массиве; первым извлекается элемент с наивысшим приоритетом (минимальное значение для min‑heap);[web:210][web:196]
 * - очередь **неограниченная** (unbounded): put/offer не блокируются по причине «нет места»,
 *   блокироваться будут только операции извлечения (take/poll с таймаутом), если очередь пуста;[web:210][web:196][web:218]
 * - не допускает null‑элементы (добавление null вызывает исключение).[web:209][web:196]
 *
 * Таким образом, PriorityBlockingQueue = PriorityQueue + BlockingQueue + потокобезопасность.[web:207][web:212][web:215]
 * Типичный use‑case — приоритизация задач в многопоточной системе (более важные задачи забираются раньше).[web:207][web:218]
 */
public class PriorityBlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        // Работаем через интерфейс BlockingQueue, используя реализацию PriorityBlockingQueue.[web:207][web:213]
        // В конструктор можно передать начальную ёмкость и/или Comparator, здесь берём значения по умолчанию.[web:208][web:209][web:220]
        BlockingQueue<Job> queue = new PriorityBlockingQueue<>();

        // Поток‑производитель: добавляет задачи с разными приоритетами.[web:207][web:213]
        Runnable producer = () -> {
            try {
                // Чем меньше число priority, тем выше приоритет (т.е. 1 важнее 10).[web:207][web:210]
                System.out.println(Thread.currentThread().getName() + " добавляет задачи...");
                queue.put(new Job("Low priority", 10));
                queue.put(new Job("Medium priority", 5));
                queue.put(new Job("High priority", 1));  // самая приоритетная задача.[web:210][web:213]
                queue.put(new Job("Very low priority", 20));
                System.out.println(Thread.currentThread().getName() + " завершил добавление задач.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Поток‑потребитель: забирает и обрабатывает задачи в порядке приоритета.[web:207][web:211]
        Runnable consumer = () -> {
            try {
                // take() блокируется, если очередь пуста, пока не появится элемент.[web:210][web:214]
                // Поскольку очередь приоритетная, задачи будут приходить не в порядке добавления, а по возрастанию priority.[web:207][web:212]
                for (int i = 0; i < 4; i++) {
                    Job job = queue.take();
                    System.out.println(Thread.currentThread().getName() +
                            " выполняет задачу: " + job);
                    Thread.sleep(300); // имитация обработки.[web:211]
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread producerThread = new Thread(producer, "Producer");
        Thread consumerThread = new Thread(consumer, "Consumer");

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        System.out.println("Все задачи обработаны, размер очереди: " + queue.size()); // должен быть 0.[web:210]
    }
}

/**
 * Класс Job представляет «задачу» с приоритетом для PriorityBlockingQueue.[web:207]
 *
 * Реализует Comparable<Job>, чтобы задать "естественный порядок" по полю priority.[web:207][web:210]
 */
class Job implements Comparable<Job> {
    private final String description; // Текстовое описание задачи.[web:213]
    private final int priority;       // Число приоритета: чем меньше, тем важнее.[web:207]

    public Job(String description, int priority) {
        this.description = description;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    /**
     * Определяет порядок задач по числовому приоритету.[web:207][web:210]
     * Используем сравнение по возрастанию: this.priority < other.priority => this «важнее» (идёт раньше в очереди).[web:210]
     */
    @Override
    public int compareTo(Job other) {
        return Integer.compare(this.priority, other.priority);
    }

    /**
     * Переопределяем toString для удобного вывода в консоль.[web:213]
     */
    @Override
    public String toString() {
        return "Job{'" + description + "', priority=" + priority + "}";
    }
}

