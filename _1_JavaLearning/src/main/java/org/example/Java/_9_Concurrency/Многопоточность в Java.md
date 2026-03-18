## Введение в многопоточность

**Многопоточность** в Java — это способность выполнять несколько задач одновременно в рамках одного процесса. Это фундаментальная концепция, которая позволяет максимально использовать ресурсы процессора и создавать отзывчивые, высокопроизводительные приложения[^1][^2][^3][^4].

### Зачем нужна многопоточность?

- **Максимальное использование CPU**: современные процессоры многоядерные, многопоточность позволяет задействовать все ядра
- **Отзывчивость UI**: длительные операции не блокируют пользовательский интерфейс
- **Асинхронная обработка**: возможность выполнять I/O операции без блокировки основного потока
- **Масштабируемость**: обработка множества одновременных запросов в серверных приложениях[^2][^3][^4].


## 1. Основы многопоточности

### 1.1 Создание потоков: Thread и Runnable

В Java существует два основных способа создания потоков[^3][^5][^6][^4].

**Способ 1: Наследование от класса Thread**

```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Поток работает: " + Thread.currentThread().getName());
    }
}

// Использование
MyThread thread = new MyThread();
thread.start(); // НЕ run()!
```

**Способ 2: Реализация интерфейса Runnable (предпочтительный)**

```java
class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Задача выполняется: " + Thread.currentThread().getName());
    }
}

// Использование
Thread thread = new Thread(new MyTask());
thread.start();

// С lambda (Java 8+)
Thread thread = new Thread(() -> {
    System.out.println("Lambda task");
});
thread.start();
```

**Почему Runnable предпочтительнее?**

- Разделяет логику задачи и механизм выполнения
- Позволяет наследоваться от других классов (Java не поддерживает множественное наследование)
- Более гибкий — можно использовать с Executor Framework[^3][^5][^6][^4].


### 1.2 Жизненный цикл потока

Каждый поток в Java проходит через несколько состояний[^3][^6][^7]:

1. **NEW** — поток создан, но не запущен (`new Thread()`)
2. **RUNNABLE** — поток запущен и готов к выполнению или выполняется (`start()`)
3. **BLOCKED** — поток ожидает монитор для входа в synchronized блок
4. **WAITING** — поток ожидает бесконечно (`wait()`, `join()`)
5. **TIMED_WAITING** — поток ожидает с таймаутом (`sleep()`, `wait(timeout)`)
6. **TERMINATED** — поток завершил выполнение

### 1.3 Методы управления потоками

**Основные методы класса Thread:**

- `start()` — запускает поток (вызывает run() в новом потоке)
- `run()` — содержит код, который будет выполнен в потоке
- `sleep(long millis)` — приостанавливает текущий поток на указанное время
- `join()` — ожидает завершения потока
- `interrupt()` — прерывает поток
- `isAlive()` — проверяет, выполняется ли поток[^3][^5][^6].

**Пример использования join():**

```java
Thread thread1 = new Thread(() -> {
    System.out.println("Thread 1 started");
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("Thread 1 finished");
});

thread1.start();
thread1.join(); // Ждем завершения thread1
System.out.println("Main thread continues");
```


## 2. Синхронизация и потокобезопасность

### 2.1 Проблема Race Condition

**Race Condition** (состояние гонки) возникает, когда несколько потоков одновременно обращаются к общим данным, и результат зависит от порядка выполнения[^2][^8][^9].

**Пример проблемы:**

```java
class Counter {
    private int count = 0;
    
    public void increment() {
        count++; // НЕ атомарная операция!
        // На самом деле это:
        // 1. Читаем count
        // 2. Увеличиваем на 1
        // 3. Записываем обратно
    }
    
    public int getCount() {
        return count;
    }
}
```

При одновременном вызове `increment()` из разных потоков значение может потеряться[^2][^8][^9].

### 2.2 Synchronized — базовая синхронизация

**Synchronized методы:**

```java
class Counter {
    private int count = 0;
    
    public synchronized void increment() {
        count++; // Теперь атомарно для всех потоков
    }
    
    public synchronized int getCount() {
        return count;
    }
}
```

**Synchronized блоки:**

```java
class Counter {
    private int count = 0;
    private final Object lock = new Object();
    
    public void increment() {
        synchronized(lock) {
            count++;
        }
    }
}
```

**Как работает synchronized:**

- Каждый объект в Java имеет монитор (monitor)
- При входе в synchronized блок/метод поток захватывает монитор
- Другие потоки ждут освобождения монитора
- После выхода из блока монитор освобождается[^1][^8][^9].


### 2.3 Volatile — обеспечение видимости

Ключевое слово `volatile` гарантирует **видимость изменений** переменной между потоками без использования блокировок[^10][^11][^12][^13].

**Проблема без volatile:**

```java
class Task implements Runnable {
    private boolean running = true; // Может быть закеширована в потоке
    
    public void run() {
        while (running) {
            // Выполнение задачи
        }
    }
    
    public void stop() {
        running = false; // Изменение может быть не видно другому потоку
    }
}
```

**Решение с volatile:**

```java
private volatile boolean running = true; // Всегда читается из памяти
```

**Когда использовать volatile:**

- Простые флаги (boolean)
- Переменные, которые записываются одним потоком и читаются другими
- Не защищает составные операции (`count++`)[^10][^11][^12][^13].


### 2.4 Wait/Notify механизм

Методы `wait()`, `notify()`, `notifyAll()` позволяют потокам координировать свою работу[^8][^9].

**Producer-Consumer пример:**

```java
class SharedQueue {
    private Queue<Integer> queue = new LinkedList<>();
    private final int MAX_SIZE = 10;
    
    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == MAX_SIZE) {
            wait(); // Ждем, пока очередь освободится
        }
        queue.add(item);
        notifyAll(); // Уведомляем потребителей
    }
    
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // Ждем, пока появятся элементы
        }
        int item = queue.poll();
        notifyAll(); // Уведомляем производителей
        return item;
    }
}
```


## 3. Lock Framework — продвинутая синхронизация

### 3.1 ReentrantLock

`ReentrantLock` предоставляет более гибкие возможности блокировки по сравнению с `synchronized`[^9][^14][^15].

**Базовое использование:**

```java
class Counter {
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();
    
    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock(); // ОБЯЗАТЕЛЬНО в finally!
        }
    }
}
```

**Преимущества ReentrantLock:**

1. **tryLock()** — попытка захвата без ожидания
2. **tryLock(timeout)** — попытка с таймаутом
3. **lockInterruptibly()** — прерываемая блокировка
4. **fairness** — честная очередь потоков[^9][^14][^15]

**Пример с tryLock:**

```java
if (lock.tryLock()) {
    try {
        // Критическая секция
    } finally {
        lock.unlock();
    }
} else {
    // Блокировка занята, делаем что-то другое
}

// С таймаутом
if (lock.tryLock(1, TimeUnit.SECONDS)) {
    try {
        // Критическая секция
    } finally {
        lock.unlock();
    }
}
```


### 3.2 Condition — продвинутая координация

`Condition` — это более мощная альтернатива `wait()/notify()`, позволяющая создавать множественные условия для одного Lock[^14][^16][^15][^17].

```java
class BoundedBuffer {
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private final Object[] items = new Object[^100];
    private int putIndex, takeIndex, count;
    
    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await(); // Ждем освобождения места
            }
            items[putIndex] = x;
            if (++putIndex == items.length) putIndex = 0;
            ++count;
            notEmpty.signal(); // Уведомляем, что есть элементы
        } finally {
            lock.unlock();
        }
    }
    
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await(); // Ждем появления элементов
            }
            Object x = items[takeIndex];
            if (++takeIndex == items.length) takeIndex = 0;
            --count;
            notFull.signal(); // Уведомляем, что есть место
            return x;
        } finally {
            lock.unlock();
        }
    }
}
```


### 3.3 ReadWriteLock — оптимизация для чтения

`ReadWriteLock` позволяет множественное чтение и эксклюзивную запись[^15].

```java
class Cache {
    private final Map<String, Data> map = new HashMap<>();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();
    
    public Data get(String key) {
        readLock.lock(); // Множественные читатели могут входить
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }
    
    public void put(String key, Data value) {
        writeLock.lock(); // Только один писатель
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
}
```


## 4. Атомарные операции

### 4.1 Atomic классы

Пакет `java.util.concurrent.atomic` предоставляет lock-free атомарные операции[^12].

**Основные классы:**

- `AtomicInteger`, `AtomicLong` — атомарные числа
- `AtomicBoolean` — атомарный boolean
- `AtomicReference<V>` — атомарная ссылка

**Пример использования:**

```java
class Counter {
    private final AtomicInteger count = new AtomicInteger(0);
    
    public void increment() {
        count.incrementAndGet(); // Атомарно без блокировок
    }
    
    public int get() {
        return count.get();
    }
}
```

**Основные методы:**

- `get()`, `set(value)`
- `incrementAndGet()`, `getAndIncrement()`
- `addAndGet(delta)`, `getAndAdd(delta)`
- `compareAndSet(expect, update)` — CAS операция[^12].


### 4.2 Compare-And-Swap (CAS)

CAS — это lock-free алгоритм, лежащий в основе атомарных операций.

```java
AtomicInteger counter = new AtomicInteger(5);

// Атомарно: если значение == 5, установить 10
boolean success = counter.compareAndSet(5, 10);
```


## 5. Executor Framework — управление потоками

### 5.1 ExecutorService

`ExecutorService` предоставляет высокоуровневый API для управления пулом потоков[^18][^19][^20][^21].

**Создание различных типов пулов:**

```java
// Фиксированный пул из 5 потоков
ExecutorService executor = Executors.newFixedThreadPool(5);

// Кэшируемый пул (создает потоки по требованию)
ExecutorService executor = Executors.newCachedThreadPool();

// Один поток
ExecutorService executor = Executors.newSingleThreadExecutor();

// Для планирования задач
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
```

**Отправка задач:**

```java
ExecutorService executor = Executors.newFixedThreadPool(3);

// Runnable (без результата)
executor.execute(() -> {
    System.out.println("Task executed");
});

// Callable (с результатом)
Future<String> future = executor.submit(() -> {
    Thread.sleep(1000);
    return "Result";
});

String result = future.get(); // Блокируется до завершения

// Правильное завершение
executor.shutdown();
executor.awaitTermination(1, TimeUnit.MINUTES);
```


### 5.2 ThreadPoolExecutor — тонкая настройка

`ThreadPoolExecutor` позволяет полностью контролировать поведение пула потоков[^18][^19][^20].

```java
ThreadPoolExecutor executor = new ThreadPoolExecutor(
    2,                                    // corePoolSize
    4,                                    // maximumPoolSize
    60L,                                  // keepAliveTime
    TimeUnit.SECONDS,                     // unit
    new LinkedBlockingQueue<>(100),       // workQueue
    new ThreadPoolExecutor.CallerRunsPolicy() // rejectionHandler
);
```

**Параметры:**

- **corePoolSize** — минимальное количество потоков
- **maximumPoolSize** — максимальное количество потоков
- **keepAliveTime** — время жизни "лишних" потоков
- **workQueue** — очередь задач
- **RejectionHandler** — политика при переполнении[^18][^19][^20].


### 5.3 Callable и Future

`Callable` — это улучшенная версия `Runnable`, которая возвращает результат и может бросать исключения.

```java
Callable<Integer> task = () -> {
    Thread.sleep(1000);
    return 42;
};

ExecutorService executor = Executors.newFixedThreadPool(2);
Future<Integer> future = executor.submit(task);

// Проверка статуса
if (!future.isDone()) {
    System.out.println("Task is still running");
}

// Получение результата (блокирует)
Integer result = future.get();

// С таймаутом
try {
    Integer result = future.get(5, TimeUnit.SECONDS);
} catch (TimeoutException e) {
    future.cancel(true); // Отмена задачи
}
```


## 6. Синхронизаторы

### 6.1 CountDownLatch

`CountDownLatch` позволяет одному или нескольким потокам ждать завершения операций в других потоках[^22][^23][^24][^25][^26].

```java
CountDownLatch latch = new CountDownLatch(3); // Ждем 3 события

// Рабочие потоки
for (int i = 0; i < 3; i++) {
    new Thread(() -> {
        System.out.println("Service " + Thread.currentThread().getName() + " started");
        try {
            Thread.sleep(2000); // Имитация работы
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown(); // Уменьшаем счетчик
    }).start();
}

// Главный поток ждет
latch.await(); // Блокируется пока счетчик не станет 0
System.out.println("All services started!");
```

**Применение:**

- Ожидание инициализации сервисов
- Параллельное выполнение задач с ожиданием всех результатов
- Стартовый сигнал для одновременного запуска потоков[^22][^23][^24][^25].


### 6.2 CyclicBarrier

`CyclicBarrier` заставляет потоки ждать друг друга в определенной точке[^22][^23][^24][^25].

```java
CyclicBarrier barrier = new CyclicBarrier(3, () -> {
    System.out.println("All threads reached the barrier!");
});

for (int i = 0; i < 3; i++) {
    new Thread(() -> {
        System.out.println(Thread.currentThread().getName() + " doing work");
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " reached barrier");
            barrier.await(); // Ждем остальных
            System.out.println(Thread.currentThread().getName() + " passed barrier");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }).start();
}
```

**Отличия от CountDownLatch:**

- `CyclicBarrier` можно переиспользовать (reset())
- Все потоки ждут друг друга, а не один поток ждет остальных
- Поддерживает callback при достижении барьера[^22][^23][^24][^25].


### 6.3 Semaphore

`Semaphore` ограничивает количество потоков, которые могут одновременно обращаться к ресурсу[^22][^24][^25][^26].

```java
Semaphore semaphore = new Semaphore(3); // Максимум 3 потока

for (int i = 0; i < 10; i++) {
    new Thread(() -> {
        try {
            semaphore.acquire(); // Запросить разрешение
            System.out.println(Thread.currentThread().getName() + " accessing resource");
            Thread.sleep(2000); // Использование ресурса
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // Освободить разрешение
            System.out.println(Thread.currentThread().getName() + " released resource");
        }
    }).start();
}
```

**Применение:**

- Ограничение подключений к базе данных
- Ограничение одновременных HTTP запросов
- Контроль доступа к пулу ресурсов[^22][^24][^25][^26].


## 7. Concurrent Collections

### 7.1 ConcurrentHashMap

Потокобезопасная реализация HashMap с высокой производительностью без полной блокировки карты.

```java
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

// Атомарные операции
map.putIfAbsent("key", 1);
map.computeIfAbsent("key", k -> expensiveComputation(k));
map.merge("key", 1, Integer::sum); // Атомарно increment

// Bulk операции
map.forEach((k, v) -> System.out.println(k + "=" + v));
```


### 7.2 BlockingQueue

Очередь с блокировкой для реализации Producer-Consumer паттерна.

```java
BlockingQueue<Task> queue = new ArrayBlockingQueue<>(10);

// Producer
new Thread(() -> {
    try {
        queue.put(new Task()); // Блокируется если очередь полна
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}).start();

// Consumer
new Thread(() -> {
    try {
        Task task = queue.take(); // Блокируется если очередь пуста
        task.process();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}).start();
```

**Типы BlockingQueue:**

- `ArrayBlockingQueue` — ограниченная очередь на массиве
- `LinkedBlockingQueue` — очередь на связном списке
- `PriorityBlockingQueue` — приоритетная очередь
- `DelayQueue` — элементы доступны после задержки.


### 7.3 CopyOnWriteArrayList

Идеально для ситуаций с частым чтением и редкой записью.

```java
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

// Итерация безопасна даже при модификации из другого потока
for (String item : list) {
    // Никогда не получим ConcurrentModificationException
    System.out.println(item);
}
```

**Принцип работы:**

- При каждой модификации создается новая копия массива
- Итераторы работают со снимком (snapshot)
- Подходит для listeners, observers[^2][^27].


## 8. Fork/Join Framework

### 8.1 ForkJoinPool и Work-Stealing

`ForkJoinPool` использует алгоритм **work-stealing** для эффективного распределения задач[^28][^29][^30][^31][^32].

**Как работает Work-Stealing:**

- Каждый поток имеет свою локальную очередь задач
- Поток берет задачи из головы своей очереди
- Если очередь пуста, поток "крадет" задачи из хвоста очереди другого потока
- Минимизирует конкуренцию за задачи[^28][^29][^30][^31].


### 8.2 RecursiveTask — задачи с результатом

```java
class SumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10_000;
    private final long[] array;
    private final int start, end;
    
    public SumTask(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Long compute() {
        int length = end - start;
        
        if (length <= THRESHOLD) {
            // Базовый случай: вычисляем напрямую
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // Рекурсивный случай: делим задачу
            int mid = start + length / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);
            
            leftTask.fork(); // Асинхронный запуск
            long rightResult = rightTask.compute(); // Синхронное выполнение
            long leftResult = leftTask.join(); // Ожидание результата
            
            return leftResult + rightResult;
        }
    }
}

// Использование
long[] array = new long[100_000];
ForkJoinPool pool = ForkJoinPool.commonPool();
long sum = pool.invoke(new SumTask(array, 0, array.length));
```


### 8.3 RecursiveAction — задачи без результата

```java
class ParallelArraySort extends RecursiveAction {
    private static final int THRESHOLD = 1000;
    private final int[] array;
    private final int start, end;
    
    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            Arrays.sort(array, start, end);
        } else {
            int mid = start + (end - start) / 2;
            ParallelArraySort left = new ParallelArraySort(array, start, mid);
            ParallelArraySort right = new ParallelArraySort(array, mid, end);
            
            invokeAll(left, right); // Запуск обеих задач
            merge(array, start, mid, end);
        }
    }
}
```


## 9. CompletableFuture — асинхронное программирование

`CompletableFuture` — мощный инструмент для построения асинхронных цепочек операций[^33][^34][^35][^36][^37].

### 9.1 Базовое использование

```java
// Простая асинхронная задача
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    // Выполняется в отдельном потоке
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    return "Hello";
});

// Неблокирующая обработка результата
future.thenAccept(result -> System.out.println(result));
```


### 9.2 Цепочки операций (Chaining)

```java
CompletableFuture.supplyAsync(() -> {
    // Шаг 1: Загружаем пользователя
    return getUserById(userId);
})
.thenApply(user -> {
    // Шаг 2: Трансформируем данные
    return user.getEmail();
})
.thenCompose(email -> {
    // Шаг 3: Еще одна асинхронная операция
    return sendEmailAsync(email);
})
.thenAccept(result -> {
    // Шаг 4: Обработка результата
    System.out.println("Email sent: " + result);
})
.exceptionally(ex -> {
    // Обработка ошибок
    System.err.println("Error: " + ex.getMessage());
    return null;
});
```

**Основные методы:**

- `thenApply()` — синхронная трансформация результата
- `thenApplyAsync()` — асинхронная трансформация
- `thenCompose()` — композиция с другим CompletableFuture
- `thenCombine()` — комбинация двух CompletableFuture
- `thenAccept()` — обработка результата без возврата значения[^33][^34][^35][^36].


### 9.3 Комбинирование множественных операций

```java
CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> getUser());
CompletableFuture<Order> orderFuture = CompletableFuture.supplyAsync(() -> getOrder());

// Комбинирование двух результатов
CompletableFuture<String> combined = userFuture.thenCombine(orderFuture, (user, order) -> {
    return "User: " + user.getName() + ", Order: " + order.getId();
});

// Ожидание завершения всех
CompletableFuture<Void> all = CompletableFuture.allOf(
    future1, future2, future3
);
all.thenRun(() -> System.out.println("All completed"));

// Ожидание первого завершившегося
CompletableFuture<Object> any = CompletableFuture.anyOf(
    future1, future2, future3
);
```


### 9.4 Обработка ошибок

```java
CompletableFuture.supplyAsync(() -> {
    if (Math.random() > 0.5) {
        throw new RuntimeException("Error!");
    }
    return "Success";
})
.exceptionally(ex -> {
    // Обработка исключения и возврат fallback значения
    return "Default value";
})
.handle((result, ex) -> {
    // Обработка и результата и исключения
    if (ex != null) {
        return "Error: " + ex.getMessage();
    }
    return result;
})
.whenComplete((result, ex) -> {
    // Выполняется всегда (как finally)
    System.out.println("Completed with: " + result);
});
```


## 10. Проблемы многопоточности

### 10.1 Deadlock (Взаимоблокировка)

**Deadlock** возникает, когда два или более потока ждут друг друга, и ни один не может продолжить выполнение[^9][^27][^38][^39][^40].

**Пример проблемы:**

```java
Object lock1 = new Object();
Object lock2 = new Object();

Thread t1 = new Thread(() -> {
    synchronized (lock1) {
        System.out.println("Thread 1: Holding lock 1");
        Thread.sleep(100);
        synchronized (lock2) { // Ждет lock2
            System.out.println("Thread 1: Holding lock 1 & 2");
        }
    }
});

Thread t2 = new Thread(() -> {
    synchronized (lock2) {
        System.out.println("Thread 2: Holding lock 2");
        Thread.sleep(100);
        synchronized (lock1) { // Ждет lock1
            System.out.println("Thread 2: Holding lock 2 & 1");
        }
    }
});
```

**Решения:**

1. Захватывать блокировки в одном и том же порядке
2. Использовать `tryLock()` с таймаутом
3. Избегать вложенных блокировок
4. Использовать высокоуровневые утилиты (например, CountDownLatch)[^9][^27][^38][^39].

### 10.2 Livelock (Активная блокировка)

**Livelock** — потоки продолжают работу, но не могут завершить задачи, постоянно реагируя на действия друг друга[^27][^41][^38][^39][^40].

**Аналогия**: два человека в коридоре одновременно отходят в одну сторону, пытаясь пропустить друг друга[^39].

### 10.3 Starvation (Голодание)

**Starvation** — поток не может получить доступ к ресурсам из-за того, что другие потоки монополизируют ресурсы[^27][^41][^38][^39].

**Причины:**

- Неправильные приоритеты потоков
- Несправедливые алгоритмы планирования
- Потоки с высоким приоритетом блокируют потоки с низким приоритетом[^27][^41][^38][^39].

**Решение:**

- Использовать fair locks: `new ReentrantLock(true)`
- Избегать установки приоритетов вручную
- Использовать тайм-ауты.


## 11. Best Practices и рекомендации

### 11.1 Принципы безопасной многопоточности

**1. Избегайте изменяемого состояния**

Неизменяемые объекты (immutable) потокобезопасны по определению:

```java
public final class ImmutablePoint {
    private final int x;
    private final int y;
    
    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
}
```

**2. Минимизируйте область блокировки**

```java
// Плохо: блокируем весь метод
public synchronized void process() {
    doSomethingNotRequiringSync();
    criticalSection();
    moreNonCriticalCode();
}

// Хорошо: блокируем только критическую секцию
public void process() {
    doSomethingNotRequiringSync();
    synchronized(this) {
        criticalSection();
    }
    moreNonCriticalCode();
}
```

**3. Используйте высокоуровневые утилиты**

Предпочитайте concurrent collections и Executor Framework вместо низкоуровневых примитивов:

```java
// Вместо
synchronized(map) {
    map.put(key, value);
}

// Используйте
ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();
map.put(key, value);
```

**4. Проектируйте для потокобезопасности**

- Делайте классы stateless где возможно
- Используйте ThreadLocal для thread-specific данных
- Документируйте thread-safety в JavaDoc[^2][^8][^9].


### 11.2 Выбор правильного инструмента

| Задача | Инструмент | Обоснование |
| :-- | :-- | :-- |
| Простой счетчик | AtomicInteger | Lock-free, высокая производительность |
| Защита критической секции | synchronized или ReentrantLock | synchronized проще, ReentrantLock гибче |
| Producer-Consumer | BlockingQueue | Встроенная блокировка и ожидание |
| Кэш с частым чтением | ConcurrentHashMap + ReadWriteLock | Оптимизация для чтения |
| Ожидание завершения N задач | CountDownLatch | Простая координация |
| Управление пулом потоков | ExecutorService | Высокоуровневое управление |
| Асинхронные цепочки | CompletableFuture | Композиция операций |
| Рекурсивные параллельные задачи | ForkJoinPool | Work-stealing эффективность |

### 11.3 Тестирование многопоточного кода

**Основные подходы:**

1. **Stress-тестирование**: запуск множества потоков для выявления race conditions
2. **Использование инструментов**:
    - Java Flight Recorder
    - VisualVM
    - ThreadSanitizer
    - FindBugs/SpotBugs
3. **Code review**: поиск типичных ошибок синхронизации
4. **Модульное тестирование с CountDownLatch** для координации потоков в тестах[^2][^27].

### 11.4 Производительность и оптимизация

**Рекомендации:**

1. **Избегайте преждевременной оптимизации**: сначала сделайте код правильным и потокобезопасным
2. **Измеряйте производительность**: используйте JMH (Java Microbenchmark Harness) для замеров
3. **Минимизируйте количество потоков**: слишком много потоков = context switching overhead
4. **Используйте пулы потоков**: переиспользование потоков вместо создания новых
5. **Избегайте блокировок на hot path**: используйте lock-free структуры данных где возможно[^2][^18][^19][^27].

## Заключение

Многопоточность в Java — это обширная и сложная тема, требующая глубокого понимания. Основные выводы:

✅ **Начинайте с основ**: Thread, Runnable, synchronized, volatile
✅ **Используйте высокоуровневые утилиты**: Executor Framework, Concurrent Collections
✅ **Изучайте паттерны**: Producer-Consumer, Fork-Join, Async composition
✅ **Знайте проблемы**: Deadlock, Race Conditions, Memory Consistency
✅ **Практикуйтесь**: многопоточность невозможно освоить только в теории

Многопоточное программирование — это путь от простых Thread и synchronized к продвинутым CompletableFuture и ForkJoinPool. Каждый уровень добавляет новые инструменты в ваш арсенал для построения высокопроизводительных, отзывчивых и масштабируемых Java приложений[^1][^2][^3][^8][^9][^33][^27].

**Практические рекомендации для изучения:**

1. **Неделя 1-2**: Thread, Runnable, synchronized, volatile — базовые концепции
2. **Неделя 3-4**: ExecutorService, Callable/Future, атомарные классы
3. **Неделя 5-6**: Синхронизаторы (CountDownLatch, Semaphore, CyclicBarrier)
4. **Неделя 7-8**: Concurrent Collections, Lock Framework (ReentrantLock, Condition)
5. **Неделя 9-10**: ForkJoinPool, RecursiveTask, work-stealing
6. **Неделя 11-12**: CompletableFuture, асинхронное программирование, композиция задач

Многопоточность — это не просто технический навык, это способ мышления о параллельном выполнении задач. Освоив эти концепции, вы сможете создавать приложения, которые эффективно используют современное многоядерное оборудование и обеспечивают отличную производительность для пользователей[^2][^3][^8][^9][^27].
<span style="display:none">[^42][^43][^44][^45][^46][^47][^48][^49][^50][^51][^52][^53][^54][^55][^56][^57][^58]</span>

<div align="center">⁂</div>

[^1]: https://javarush.com/quests/lectures/questservlets.level19.lecture00

[^2]: https://scand.com/ru/company/blog/java-concurrency-multithreading-practical-guide/

[^3]: https://struchkov.dev/blog/ru/threads-in-java-thread-and-runnable/

[^4]: https://habr.com/ru/sandbox/167189/

[^5]: https://younglinux.info/java/runnable

[^6]: https://foxminded.ua/ru/potoki-v-java-thread-i-runnable/

[^7]: https://habr.com/ru/articles/776914/

[^8]: https://habr.com/ru/companies/otus/articles/830356/

[^9]: https://habr.com/ru/companies/otus/articles/755316/

[^10]: https://proselyte.net/how-volatile-works-in-java/

[^11]: https://java-ru-blog.blogspot.com/2020/10/volatile-java.html

[^12]: https://habr.com/ru/companies/bercut/articles/822253/

[^13]: https://javarush.com/quests/lectures/questmultithreading.level06.lecture04

[^14]: https://go-mother.com/lock-reentrantlock-conditions-lock/

[^15]: https://javarush.com/quests/lectures/questservlets.level19.lecture07

[^16]: https://learn.microsoft.com/ru-ru/dotnet/api/java.util.concurrent.locks.reentrantlock.newcondition?view=net-android-35.0

[^17]: http://www.berkut.mk.ua/download/pdf/trackensure/concurrency.pdf

[^18]: https://proselyte.net/java-executor-services/

[^19]: https://arenda-server.cloud/blog/threadpoolexecutor-v-java-primer-pula-potokov-s-executorservice/

[^20]: https://javarush.com/quests/lectures/questmultithreading.level08.lecture07

[^21]: https://javarush.com/quests/lectures/jru.module2.lecture22

[^22]: https://stackoverflow.com/questions/184147/countdownlatch-vs-semaphore

[^23]: https://www.geeksforgeeks.org/java/difference-between-countdownlatch-and-cyclicbarrier-in-java/

[^24]: https://www.linkedin.com/pulse/mastering-multithreading-java-part-14-understanding-thread-crowley-xzzff

[^25]: https://habr.com/ru/articles/277669/

[^26]: https://javarush.com/quests/lectures/questservlets.level19.lecture04

[^27]: https://habr.com/ru/companies/simbirsoft/articles/812097/

[^28]: https://www.youtube.com/watch?v=6SU1EEOeAGA

[^29]: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.html

[^30]: https://www.linkedin.com/pulse/mastering-multithreading-java-part-16-forkjoin-allan-crowley-45ede

[^31]: https://stackoverflow.com/questions/69871747/whats-the-benefit-to-use-wrokstealing-from-forkjoin-rather-than-just-ordinary-th

[^32]: https://javarush.com/quests/lectures/jru.module2.lecture24

[^33]: https://www.codingshuttle.com/blogs/a-comprehensive-guide-to-java-completable-future/

[^34]: https://metadesignsolutions.com/mastering-asynchronous-programming-in-java-a-deep-dive-into-completablefuture/

[^35]: https://pwrteams.com/content-hub/blog/async-programming-and-completablefuture-in-java

[^36]: https://www.baeldung.com/java-completablefuture

[^37]: https://spring.io/guides/gs/async-method

[^38]: https://rsdn.org/forum/java/5119496.all

[^39]: https://www.guru99.com/ru/what-is-livelock-example.html

[^40]: https://javarush.com/groups/posts/2060-threadom-java-ne-isportishjh--chastjh-iii---vzaimodeystvie

[^41]: https://studbooks.net/2196696/informatika/starvation

[^42]: https://javarush.com/ua/quests/lectures/ua.questservlets.level19.lecture00

[^43]: https://habr.com/ru/articles/802113/

[^44]: https://javarush.com/groups/posts/6462-kofe-breyk-254-demistifikacija-mnogopotochnosti-v-java-prakticheskoe-rukovodstvo-s-primerami

[^45]: https://senior.ua/articles/mnogopotochnost-v-java-lekciya-3-blokirovki-i-klassy-sinhronizacii-potokov

[^46]: https://itvdn.com/ru/video/asynchronous-programming-in-java/threadpool-executorservice

[^47]: https://www.senior.ua/articles/mnogopotochnost-v-java-lekciya-4-puly-potokov

[^48]: https://ru.stackoverflow.com/questions/511532/Синхронизация-потоков-java

[^49]: https://ru.stackoverflow.com/questions/631107/Применение-и-значение-ключевого-слова-volatile

[^50]: https://www.youtube.com/watch?v=CI_rOvL-OTE

[^51]: https://www.baeldung.com/java-countdownlatch-vs-semaphore

[^52]: https://javarush.com/groups/posts/1998-upravlenie-potokami-metodih-volatile-i-yield

[^53]: https://akuzmin.hashnode.dev/java-concurrency-sinhronizatory-v-java

[^54]: https://learn.microsoft.com/ru-ru/dotnet/api/java.util.concurrent.forkjoinpool?view=net-android-34.0

[^55]: https://www.reddit.com/r/java/comments/16rbqzz/examples_of_completablefuturebased_apis_state_of/

[^56]: https://nurlandroid.com/?p=760

[^57]: https://codesignal.com/learn/courses/mastering-java-concurrency-with-practice/lessons/asynchronous-file-processing-with-completablefuture

[^58]: https://www.baeldung.com/java-work-stealing

