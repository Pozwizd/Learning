package org.example.Java._8_Concurrency.Threads.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Task2 {
    private static List<String> users = new ArrayList<>();

    public static void main(String[] args) {
        users.add("Аліса");
        users.add("Олександр");
        users.add("Юрій");
        users.add("Віталій");
        users.add("Михаил");
        users.add("Марія");
        users.add("Іван");
        users.add("Ірина");
        users.add("Марк");
        users.add("Світлана");


        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер пользователя: ");
        int userNumber = scanner.nextInt();

        Callable<String> getUserTask = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return users.get(userNumber - 1);
            }
        };
        FutureTask<String> userTask = new FutureTask<>(getUserTask);
        Thread thread1 = new Thread(userTask);

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!userTask.isDone()) {
                        System.out.print(".");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.setDaemon(true);

        thread1.start();
        thread2.start();

        try {
            String username = userTask.get();
            System.out.println("\nПолученное имя пользователя: " + username);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}