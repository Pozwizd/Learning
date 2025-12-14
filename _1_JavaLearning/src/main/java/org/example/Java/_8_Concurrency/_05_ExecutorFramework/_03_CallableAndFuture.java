package org.example.Java._8_Concurrency._05_ExecutorFramework;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class _03_CallableAndFuture {

    public static void main(String[] args) {
        System.out.println("=== Примеры использования Callable и Future ===");

        // Создаем пул из одного потока
        try (ExecutorService executor = Executors.newSingleThreadExecutor()) {

            // 1. Создание Callable задачи
            // Callable<T> - это интерфейс, похожий на Runnable, но он может возвращать результат и бросать исключение.
            Callable<String> callableTask = () -> {
                System.out.println("Callable: Задача начала выполняться в потоке: " + Thread.currentThread().getName());
                // Имитируем долгую операцию
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Результат из Callable";
            };

            // 2. Отправка Callable на выполнение
            // Метод submit() отправляет задачу в пул потоков и немедленно возвращает объект Future.
            System.out.println("\n1. Отправка задачи на выполнение...");
            Future<String> future = executor.submit(callableTask);

            // 3. Проверка состояния задачи
            // Метод isDone() позволяет проверить, завершилась ли задача.
            System.out.println("2. Проверка, завершена ли задача (isDone): " + future.isDone());

            // В это время основной поток может выполнять другую работу
            System.out.println("Основной поток продолжает работу, пока задача выполняется асинхронно...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 4. Получение результата выполнения
            // Метод get() блокирует текущий поток до тех пор, пока задача не будет выполнена и результат не будет доступен.
            try {
                System.out.println("\n3. Попытка получить результат с помощью get()...");
                String result = future.get(); // Блокирующий вызов
                System.out.println("   Результат получен: \"" + result + "\"");
            } catch (InterruptedException | ExecutionException e) {
                // InterruptedException - если поток был прерван во время ожидания
                // ExecutionException - если задача бросила исключение во время выполнения
                System.err.println("   Ошибка при получении результата: " + e.getMessage());
            }

            System.out.println("4. Проверка, завершена ли задача после get() (isDone): " + future.isDone());


            // 5. Получение результата с таймаутом
            System.out.println("\n5. Пример с get() и таймаутом");
            Future<Integer> timeoutFuture = executor.submit(() -> {
                try {
                    Thread.sleep(3000); // Задача выполняется дольше таймаута
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 123;
            });

            try {
                System.out.println("   Ожидаем результат не более 1 секунды...");
                Integer result = timeoutFuture.get(1, TimeUnit.SECONDS);
                System.out.println("   Результат (с таймаутом): " + result);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                System.err.println("   Не удалось получить результат вовремя: " + e.getClass().getSimpleName());
            }

            // 6. Отмена задачи
            System.out.println("\n6. Пример с отменой задачи (cancel)");
            Future<String> cancellableFuture = executor.submit(() -> {
                System.out.println("   (Отменяемая задача) Начала выполняться...");
                try {
                    Thread.sleep(5000); // Длинная задача
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("   (Отменяемая задача) Завершилась (это сообщение не должно появиться, если отмена сработала)");
                return "Никогда не вернется";
            });

            try {
                Thread.sleep(500); // Даем задаче немного поработать
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("   Попытка отменить задачу...");
            // true - прервать поток, если он уже выполняется
            boolean cancelled = cancellableFuture.cancel(true);
            System.out.println("   Задача отменена? " + cancelled);
            System.out.println("   Проверка isCancelled(): " + cancellableFuture.isCancelled());
            System.out.println("   Проверка isDone() после отмены: " + cancellableFuture.isDone());


            // Важно завершить работу ExecutorService, иначе программа не остановится
            System.out.println("\n=== Завершение работы ExecutorService ===");
            executor.shutdown();
            try {
                // Ожидаем завершения всех задач в пуле
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.err.println("   Пул потоков не завершился вовремя, принудительное завершение.");
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }

        System.out.println("\n=== Все примеры завершены ===");
    }
}
