package org.example.Java._8_Concurrency._08_ForkJoin;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Параллельный подсчёт общего размера каталога с использованием ForkJoin.
 *
 * Что показывает пример:
 * - Как рекурсивно обходить директории, создавая подзадачи для вложенных каталогов
 * - Как избегать проблем с симлинками (циклы) и необрабатываемыми файлами
 * - Как агрегировать результаты от подзадач через join()
 *
 * Как запустить:
 * - Передайте путь к директории как первый аргумент, либо запустите без аргументов — возьмётся текущая директория.
 */
public class ParallelFileSize {

    /**
     * Задача вычисления размера каталога.
     * Для каждого вложенного каталога создаёт подзадачу (fork), для файлов — суммирует размер.
     */
    static class DirSizeTask extends RecursiveTask<Long> {
        private final Path dir;

        DirSizeTask(Path dir) {
            this.dir = dir;
        }

        @Override
        protected Long compute() {
            List<DirSizeTask> subTasks = new ArrayList<>();
            long sum = 0L;

            // Перебираем содержимое каталога
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                for (Path p : stream) {
                    try {
                        // Читаем базовые атрибуты файла/каталога, не следуя по симлинкам
                        BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
                        if (attrs.isSymbolicLink()) {
                            // Симлинки пропускаем, чтобы не попасть в цикл
                            continue;
                        }
                        if (attrs.isRegularFile()) {
                            // Обычный файл — добавляем его размер
                            sum += attrs.size();
                        } else if (attrs.isDirectory()) {
                            // Для подкаталога создаём подзадачу и форкаем
                            DirSizeTask sub = new DirSizeTask(p);
                            sub.fork();
                            subTasks.add(sub);
                        }
                    } catch (IOException e) {
                        // Не удалось прочитать элемент — в учебных целях игнорируем (в реальном коде логируйте)
                    }
                }
            } catch (IOException e) {
                // Сам каталог недоступен — считаем размер 0
                return 0L;
            }

            // Собираем результаты от всех подзадач
            for (DirSizeTask t : subTasks) {
                sum += t.join();
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        // Определяем стартовый путь: из аргумента или текущая директория
        Path start = args.length > 0 ? Paths.get(args[0]) : Paths.get(".").toAbsolutePath().normalize();
        if (!Files.isDirectory(start)) {
            System.err.println("Путь не является директорией: " + start);
            return;
        }

        ForkJoinPool pool = ForkJoinPool.commonPool();
        long t1 = System.currentTimeMillis();
        long bytes = pool.invoke(new DirSizeTask(start));
        long t2 = System.currentTimeMillis();

        System.out.println("Директория: " + start);
        System.out.println("Общий размер (байты): " + bytes);
        System.out.println("Время: " + (t2 - t1) + " ms, parallelism=" + pool.getParallelism());
    }
}
