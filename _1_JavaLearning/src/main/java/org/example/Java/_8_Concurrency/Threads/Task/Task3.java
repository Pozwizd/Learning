package org.example.Java._8_Concurrency.Threads.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Task3 {
    private static List<Integer> numbers = new ArrayList<>();
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable task1 = () -> {
            addElement();
        };

        Runnable task2 = () -> {
            addElement();
        };

        executorService.submit(task1);
        executorService.submit(task2);

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Размер коллекции: " + numbers.size());

    }

    public static synchronized void addElement(){
        for (int i = 1; i <= 10000; i++) {

            numbers.add(atomicInteger.incrementAndGet());
            System.out.println(Thread.currentThread().getName() + " поток добавил " + atomicInteger);
        }
    }

}