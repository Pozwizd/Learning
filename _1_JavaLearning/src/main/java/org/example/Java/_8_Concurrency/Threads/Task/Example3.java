package org.example.Java._8_Concurrency.Threads.Task;

public class Example3 {
    public static void main(String[] args) throws InterruptedException {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Фоновый поток выполняется...");
                    while (true) {
                        System.out.println(".");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        daemonThread.setDaemon(true);
        daemonThread.start();
        Thread.sleep(3000);
        System.out.println("Основной поток завершился.");
    }
}