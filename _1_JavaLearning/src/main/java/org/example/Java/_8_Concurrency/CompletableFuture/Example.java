package org.example.Java._8_Concurrency.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Example {
    private static final Random random = new Random();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("=== Примеры использования CompletableFuture ===");

        // 1. supplyAsync - создание асинхронной задачи с возвращаемым значением
        System.out.println("\n1. supplyAsync:");
        CompletableFuture<String> supply = CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return "Результат из supplyAsync";
        });

        // 2. runAsync - создание асинхронной задачи без возвращаемого значения
        System.out.println("2. runAsync:");
        CompletableFuture<Void> run = CompletableFuture.runAsync(() -> {
            sleep(500);
            System.out.println("runAsync: Задача выполнена");
        });

        // 3. thenApply - преобразование результата
        System.out.println("3. thenApply:");
        CompletableFuture<String> applied = supply.thenApply(result -> {
            System.out.println("thenApply: Получен результат - " + result);
            return result.toUpperCase();
        });

        // 4. thenAccept - обработка результата без возврата значения
        System.out.println("4. thenAccept:");
        CompletableFuture<Void> accepted = applied.thenAccept(result -> {
            System.out.println("thenAccept: Финальный результат - " + result);
        });

        // 5. thenRun - выполнение действия после завершения
        System.out.println("5. thenRun:");
        CompletableFuture<Void> thenRunned = accepted.thenRun(() -> {
            System.out.println("thenRun: Цепочка завершена");
        });

        // Ждем завершения первой цепочки
        thenRunned.join();

        // 6. thenCombine - комбинирование двух CompletableFuture
        System.out.println("\n6. thenCombine:");
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            sleep(800);
            return "Первый";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            sleep(600);
            return "Второй";
        });

        CompletableFuture<String> combined = future1.thenCombine(future2, (first, second) -> {
            String result = first + " + " + second;
            System.out.println("thenCombine: " + result);
            return result;
        });

        // 7. allOf - ожидание завершения всех задач
        System.out.println("7. allOf:");
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            sleep(300);
            System.out.println("allOf: Задача 1 завершена");
            return "Задача1";
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            sleep(400);
            System.out.println("allOf: Задача 2 завершена");
            return "Задача2";
        });

        CompletableFuture<String> task3 = CompletableFuture.supplyAsync(() -> {
            sleep(200);
            System.out.println("allOf: Задача 3 завершена");
            return "Задача3";
        });

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);
        allTasks.thenRun(() -> System.out.println("allOf: Все задачи завершены"));

        // 8. anyOf - завершение при первой завершившейся задаче
        System.out.println("8. anyOf:");
        CompletableFuture<String> slowTask = CompletableFuture.supplyAsync(() -> {
            sleep(1500);
            return "Медленная задача";
        });

        CompletableFuture<String> fastTask = CompletableFuture.supplyAsync(() -> {
            sleep(200);
            return "Быстрая задача";
        });

        CompletableFuture<Object> firstCompleted = CompletableFuture.anyOf(slowTask, fastTask);
        firstCompleted.thenAccept(result ->
                System.out.println("anyOf: Первая завершенная - " + result));

        // 9. exceptionally - обработка исключений
        System.out.println("9. exceptionally:");
        CompletableFuture<String> withException = CompletableFuture.supplyAsync(() -> {
            if (random.nextBoolean()) {
                throw new RuntimeException("Случайная ошибка");
            }
            return "Успешный результат";
        }).exceptionally(throwable -> {
            System.out.println("exceptionally: Обработка ошибки - " + throwable.getMessage());
            return "Значение по умолчанию";
        });

        // 10. handle - универсальная обработка результата и исключений
        System.out.println("10. handle:");
        CompletableFuture<String> handled = CompletableFuture.supplyAsync(() -> {
            if (random.nextBoolean()) {
                throw new RuntimeException("Ошибка в handle");
            }
            return "Данные для handle";
        }).handle((result, throwable) -> {
            if (throwable != null) {
                System.out.println("handle: Обработка исключения - " + throwable.getMessage());
                return "Обработанная ошибка";
            } else {
                System.out.println("handle: Успешный результат - " + result);
                return "Обработанный " + result;
            }
        });

        // 11. whenComplete - выполнение действия при завершении
        System.out.println("11. whenComplete:");
        CompletableFuture<String> withComplete = CompletableFuture.supplyAsync(() -> {
            sleep(300);
            return "Данные для whenComplete";
        }).whenComplete((result, throwable) -> {
            if (throwable != null) {
                System.out.println("whenComplete: Завершено с ошибкой - " + throwable.getMessage());
            } else {
                System.out.println("whenComplete: Завершено успешно - " + result);
            }
        });

        // 12. thenCompose - создание цепочки зависимых операций
        System.out.println("12. thenCompose:");
        CompletableFuture<String> composed = CompletableFuture.supplyAsync(() -> {
            return "Входные данные";
        }).thenCompose(input -> {
            return CompletableFuture.supplyAsync(() -> {
                sleep(300);
                return "Обработанные: " + input;
            });
        }).thenApply(result -> {
            System.out.println("thenCompose: " + result);
            return result;
        });

        // 13. completedFuture - уже завершенный CompletableFuture
        System.out.println("13. completedFuture:");
        CompletableFuture<String> completed = CompletableFuture.completedFuture("Готовый результат");
        completed.thenAccept(result -> System.out.println("completedFuture: " + result));

        // Ждем завершения всех операций
        System.out.println("\n=== Ожидание завершения всех операций ===");

        // 14. Использование get() и join()
        try {
            System.out.println("get(): " + combined.get());
            System.out.println("join(): " + withException.join());
            System.out.println("handled result: " + handled.join());
            System.out.println("composed result: " + composed.join());

            // Ждем завершения остальных задач
            allTasks.get();
            firstCompleted.get();
            withComplete.get();

        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Ошибка при получении результата: " + e.getMessage());
        }

        System.out.println("\n=== Все примеры завершены ===");
    }

    // Вспомогательный метод для имитации задержки
    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
