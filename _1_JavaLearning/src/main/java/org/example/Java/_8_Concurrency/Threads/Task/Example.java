package org.example.Java._8_Concurrency.Threads.Task;

public class Example {
    public static void main(String[] args) {

        MyThread threadExtend = new MyThread();
        threadExtend.start();

        Thread threadImpl = new Thread(new MyRunnable());
        threadImpl.start();

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Этот код выполняется в отдельном потоке.");
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            System.out.println("Этот код выполняется в отдельном потоке.");
        });
        thread2.start();
    }
}

class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Этот код выполняется в отдельном потоке.");
    }
}

class MyThread extends Thread {
    public void run() {
        System.out.println("Этот код выполняется в отдельном потоке.");
    }
}