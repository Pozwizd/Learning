package org.example.Java._8_Concurrency.Threads.Task;

public class Example2 {

    public static void main(String[] args) {
        MyThreadExample2 thread = new MyThreadExample2();
        thread.start();

        thread.stopThread();
    }
}


class MyThreadExample2 extends Thread {
    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println();
        }
    }

    public void stopThread() {
        running = false;
    }
}

class MyThreadExample extends Thread {
    public void run() {
        while (!isInterrupted()) {
            System.out.println();
        }
    }
}
