package org.example.Java._8_Concurrency.ForkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Демонстрация различных способов отправки задач в ForkJoinPool и обработки исключений.
 *
 * Что показывает пример:
 * - pool.invoke(task): синхронный вызов, блокирует до получения результата
 * - pool.submit(task): асинхронный вызов, возвращает ForkJoinTask/Future
 * - ForkJoinTask.invokeAll(...): массовый запуск набора задач
 * - Корректная обработка исключений и отмена задач (cancel)
 *
 * Полезно помнить:
 * - Исключения в подзадачах упаковываются и пробрасываются при get()/join()
 * - Отмена (cancel) помечает задачу, но если она уже исполняется, она может завершиться
 * - join() может бросить RuntimeException, если подзадача упала
 *
 * Как запустить:
 * - Запустите метод main и наблюдайте вывод для каждого сценария.
 */
public class InvokeAllAndExceptions {

    /**
     * Задача, которая по желанию может бросить исключение (для демонстрации).
     * Делит диапазон на подзадачи до порога THRESHOLD.
     */
    static class PossiblyFailingTask extends RecursiveTask<Integer> {
        private static final int THRESHOLD = 50_000; // Порог — ниже него считаем последовательно
        private final int[] data;
        private final int start; // включительно
        private final int end;   // исключительно
        private final boolean failOnThisTask; // если true — эта задача (или её часть) бросит исключение

        PossiblyFailingTask(int[] data, int start, int end, boolean failOnThisTask) {
            this.data = data;
            this.start = start;
            this.end = end;
            this.failOnThisTask = failOnThisTask;
        }

        @Override
        protected Integer compute() {
            int len = end - start;
            if (len <= THRESHOLD) {
                if (failOnThisTask) {
                    // Намеренно генерируем исключение, чтобы показать механику его распространения
                    throw new IllegalStateException("Умышленная ошибка на диапазоне [" + start + "," + end + ")");
                }
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += data[i];
                }
                return sum;
            }
            // Делим задачу пополам. Флаг "ошибка" определяем для одной из половин
            int mid = start + len / 2;
            boolean leftFail = failOnThisTask && (mid - start) > (end - mid);
            boolean rightFail = failOnThisTask && (end - mid) > (mid - start);

            PossiblyFailingTask left = new PossiblyFailingTask(data, start, mid, leftFail);
            PossiblyFailingTask right = new PossiblyFailingTask(data, mid, end, rightFail);
            left.fork();             // левую часть — асинхронно
            int r = right.compute(); // правую — синхронно
            return left.join() + r;  // join может пробросить исключение из левой части
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        int size = 1_000_000;
        int[] arr = new Random(2024).ints(size, 0, 10).toArray();

        System.out.println("== invoke ==");
        try {
            // invoke блокирует текущий поток до завершения задачи
            int res = pool.invoke(new PossiblyFailingTask(arr, 0, arr.length, false));
            System.out.println("invoke result: " + res);
        } catch (RuntimeException ex) {
            // Если задача бросит unchecked исключение — оно «всплывёт» здесь
            System.out.println("invoke threw: " + ex);
        }

        System.out.println("\n== submit ==");
        // submit возвращает ForkJoinTask (который также является Future)
        ForkJoinTask<Integer> task = pool.submit(new PossiblyFailingTask(arr, 0, arr.length, false));
        try {
            // get() может бросить ExecutionException, внутри которого лежит причина
            System.out.println("submit get: " + task.get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("submit get threw: " + e.getCause());
        }

        System.out.println("\n== invokeAll (bulk) с одной падающей задачей ==");
        List<ForkJoinTask<Integer>> tasks = new ArrayList<>();
        int parts = 8;
        int chunk = size / parts;
        for (int i = 0; i < parts; i++) {
            int s = i * chunk;
            int e = (i == parts - 1) ? size : (i + 1) * chunk;
            boolean fail = (i == 3); // одну из задач заставим упасть
            tasks.add(new PossiblyFailingTask(arr, s, e, fail));
        }
        // invokeAll запускает все задачи и возвращает, когда все завершены (успешно или с ошибкой)
        ForkJoinTask.invokeAll(tasks);
        int total = 0;
        for (ForkJoinTask<Integer> t : tasks) {
            try {
                // join() может пробросить RuntimeException, если задача упала
                total += t.join();
            } catch (RuntimeException ex) {
                System.out.println("Подзадача упала: " + ex);
                // При необходимости можно отменить остальные задачи
                for (ForkJoinTask<Integer> other : tasks) {
                    if (other != t) other.cancel(true);
                }
            }
        }
        System.out.println("Сумма по батчу (если без ошибок, иначе частичная): " + total);

        System.out.println("\n== submit и отмена ==");
        ForkJoinTask<Integer> longTask = pool.submit(new PossiblyFailingTask(arr, 0, arr.length, false));
        boolean cancelled = longTask.cancel(true);
        System.out.println("Отмена запрошена: " + cancelled);
        // Даже после cancel() join() может не бросить, если задача успела завершиться
        if (!longTask.isCancelled()) {
            try {
                System.out.println("Результат после попытки отмены: " + longTask.join());
            } catch (RuntimeException ex) {
                System.out.println("Join бросил после cancel: " + ex);
            }
        } else {
            System.out.println("Задача отменена.");
        }
    }
}