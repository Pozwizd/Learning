# Многопоточность в Java - Структура примеров

Эта директория содержит организованные примеры по многопоточности в Java, соответствующие структуре файла `Многопоточность в Java.md`.

## Структура директорий

### **_01_Basics** - Основы многопоточности
- `_01_CreateThreads.java` - Создание потоков через Thread и Runnable
- `_02_ThreadLifecycle.java` - Жизненный цикл потока (NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED)

### **_02_Synchronization** - Синхронизация и потокобезопасность
- `_01_RaceCondition.java` - Демонстрация проблемы состояния гонки
- `_02_Synchronized.java` - Использование synchronized для защиты критических секций
- `_03_Volatile.java` - Обеспечение видимости изменений между потоками
- `_04_WaitNotify.java` - Механизм wait/notify для координации потоков

### **_03_LockFramework** - Lock Framework
- `_01_ReentrantLock.java` - Использование ReentrantLock
- `_02_Condition.java` - Базовый пример Condition
- `_03_ConditionExample.java` - Продвинутая координация с Condition
- `_04_ConditionsExample.java` - Producer-Consumer с Condition
- `_05_ReadWriteLock.java` - ReadWriteLock для оптимизации чтения

### **_04_Atomic** - Атомарные операции
- `_01_AtomicInteger.java` - Lock-free операции с AtomicInteger
- `_02_CompareAndSwap.java` - CAS операции и lock-free структуры данных

### **_05_ExecutorFramework** - Executor Framework
- `_01_ExecutorService.java` - Различные типы пулов потоков
- `_03_CallableAndFuture.java` - Callable и Future для задач с результатом
- `TaskExecutorBasics.java` - Базовые примеры ExecutorService
- `TaskExecutorCompletionService.java` - CompletionService для получения результатов
- `TaskExecutorInvokeAll.java` - Пакетное выполнение задач
- `TaskExecutorRejectionPolicy.java` - Политики отклонения задач
- `TaskExecutorScheduling.java` - ScheduledExecutorService для отложенных задач
- `TaskExecutorShutdownHooks.java` - Корректное завершение executor
- `TaskExecutorThreadFactory.java` - Кастомные фабрики потоков

### **_06_Synchronizers** - Синхронизаторы
- `_01_CountDownLatch.java` - Ожидание завершения N операций
- `_02_CyclicBarrier.java` - Синхронизация потоков в определенной точке
- `_03_Semaphore.java` - Ограничение количества одновременных доступов
- `_04_Exchanger.java` - Обмен данными между двумя потоками

### **_07_ConcurrentCollections** - Concurrent Collections
- `_01_ConcurrentHashMap.java` - Потокобезопасная HashMap с атомарными операциями
- `_02_BlockingQueue.java` - Producer-Consumer с BlockingQueue
- `_03_CopyOnWriteArrayList.java` - Оптимизация для частого чтения

### **_08_ForkJoin** - Fork/Join Framework
- `ForkJoinPoolBasics.java` - Базовое использование ForkJoinPool
- `CustomPoolAndManagedBlocker.java` - Кастомные пулы и ManagedBlocker
- `InvokeAllAndExceptions.java` - Обработка исключений в ForkJoinPool
- `ParallelFileSize.java` - Параллельный подсчет размера файлов
- `ParallelMergeSort.java` - Параллельная сортировка слиянием

### **_09_CompletableFuture** - Асинхронное программирование
- `_01_CompletableFutureExample.java` - CompletableFuture для асинхронных операций и композиции

### **_10_Problems** - Проблемы многопоточности
- `_01_Deadlock.java` - Демонстрация взаимоблокировки
- `_02_Livelock.java` - Демонстрация активной блокировки

### **_11_BestPractices** - Best Practices
- `_01_ImmutableObjects.java` - Использование неизменяемых объектов
- `_02_MinimizeLocking.java` - Минимизация области блокировки
- `_03_ThreadLocal.java` - ThreadLocal для thread-specific данных

## Соответствие разделам из Markdown

| Раздел MD | Директория | Описание |
|-----------|-----------|----------|
| §1. Основы многопоточности | `_01_Basics/` | Thread, Runnable, жизненный цикл |
| §2. Синхронизация и потокобезопасность | `_02_Synchronization/` | synchronized, volatile, wait/notify |
| §3. Lock Framework | `_03_LockFramework/` | ReentrantLock, Condition, ReadWriteLock |
| §4. Атомарные операции | `_04_Atomic/` | AtomicInteger, CAS |
| §5. Executor Framework | `_05_ExecutorFramework/` | ExecutorService, ThreadPoolExecutor, Callable/Future |
| §6. Синхронизаторы | `_06_Synchronizers/` | CountDownLatch, CyclicBarrier, Semaphore, Exchanger |
| §7. Concurrent Collections | `_07_ConcurrentCollections/` | ConcurrentHashMap, BlockingQueue, CopyOnWriteArrayList |
| §8. Fork/Join Framework | `_08_ForkJoin/` | ForkJoinPool, RecursiveTask, RecursiveAction |
| §9. CompletableFuture | `_09_CompletableFuture/` | Асинхронные цепочки операций |
| §10. Проблемы многопоточности | `_10_Problems/` | Deadlock, Livelock, Starvation |
| §11. Best Practices | `_11_BestPractices/` | Рекомендации и паттерны |

## Как использовать

1. **Последовательное изучение**: Проходите директории по порядку от `_01_Basics` до `_11_BestPractices`
2. **Практика**: Каждый файл содержит рабочий пример с методом `main()` - запускайте и экспериментируйте
3. **Теория**: Используйте `Многопоточность в Java.md` как справочник по концепциям
4. **Эксперименты**: Модифицируйте примеры, чтобы лучше понять поведение

## Ключевые концепции по уровням сложности

### Начальный уровень (Недели 1-2)
- Thread и Runnable
- synchronized и volatile
- Жизненный цикл потока
- Race Condition

### Средний уровень (Недели 3-6)
- ExecutorService
- Callable и Future
- Атомарные классы
- CountDownLatch, Semaphore, CyclicBarrier
- Concurrent Collections

### Продвинутый уровень (Недели 7-12)
- Lock Framework (ReentrantLock, Condition, ReadWriteLock)
- ForkJoinPool и work-stealing
- CompletableFuture
- Проблемы (Deadlock, Livelock)
- Best Practices и оптимизация

## Полезные ссылки

- [Java Concurrency Tutorial (Oracle)](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
- [java.util.concurrent package](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/package-summary.html)
- Книга: "Java Concurrency in Practice" by Brian Goetz

---

**Организация структуры**: Ноябрь 2025  
**Последнее обновление**: 05.11.2025
